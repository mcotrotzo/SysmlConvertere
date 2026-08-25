package org.example.Mapping.NewVersion.Abstract;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.BaseTaxonomy.Taxonomy;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.NameSpace.NameSpacePackage.MappedNamespaceElement;
import org.example.Util.LibraryNameSpaces;
import org.example.Util.TwinLibraryNamespace;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.util.TypeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ToString(callSuper = true)
public abstract class MappedElement<T extends Type, Z extends TypeKind> extends MappedNamespaceElement<T, Z> implements org.example.Mapping.Interfaces.Base.Type<Z> {

	private final List<MappedReference<? extends MappedElement<?, Definition>>> superTypeOfDefinitions = new ArrayList<>();
	private MappedReference<? extends MappedElement<?, Definition>> definitionOfUsage;
	private Optional<Reference<? extends Taxonomy<Definition>>> taxonomyDefinition = Optional.empty();

	public MappedElement(T sysmlElement) {
		super(sysmlElement);
	}

	@SuppressWarnings("unchecked")
	private static Class<MappedElement<?, Definition>> getRawDefinitionClass() {
		return (Class<MappedElement<?, Definition>>) (Class<?>) MappedElement.class;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		parseTypeRelations(context);
		parseTaxonomy(context);
	}

	protected final void parseTypeRelations(MappingContext context) throws MappingException {
		if (getSysmlElement() instanceof Feature feature) {
			List<Classifier> definitions = feature.getType().stream().peek(x -> System.out.println(" Type of: " + feature.getQualifiedName() + x.getQualifiedName())).filter(Classifier.class::isInstance).map(Classifier.class::cast).filter(x -> !context.getUtils().isFromStandardLibrary(x)).toList();

			if (!definitions.isEmpty()) {
				Classifier definition = mostSpecificDefinition(definitions);
				if (isTwinLibraryRoot(definition)) {
					return;
				}
				definitionOfUsage = context.mapReference(definition, getRawDefinitionClass());
			}
		}

		if (getSysmlElement() instanceof Classifier classifier) {
			for (var subclassification : classifier.getOwnedSubclassification()) {
				var superClassifier = subclassification.getSuperclassifier();
				System.out.println(" Subclassification of " + classifier.getQualifiedName() + " -> " + (superClassifier != null ? superClassifier.getQualifiedName() : "null"));
				if (!(superClassifier instanceof Classifier definition)) continue;
				if (context.getUtils().isFromStandardLibrary(definition)) continue;
				if (isTwinLibraryRoot(definition)) {
					return;
				}
				superTypeOfDefinitions.add(context.mapReference(definition, getRawDefinitionClass()));
			}
		}
	}

	private Classifier mostSpecificDefinition(List<Classifier> definitions) throws MappingException {
		List<Classifier> mostSpecific = definitions.stream().filter(candidate -> definitions.stream().filter(other -> other != candidate).noneMatch(other -> TypeUtil.getSupertypesOf(other, true).contains(candidate))).toList();

		if (mostSpecific.size() == 1) {
			return mostSpecific.getFirst();
		}

		if (mostSpecific.isEmpty()) {
			throw new MappingException("No most specific definition found.");
		}

		throw new MappingException("Multiple unrelated definitions found: " + mostSpecific.stream().map(Classifier::getQualifiedName).toList());
	}

	@Override
	public Optional<? extends Reference<? extends org.example.Mapping.Interfaces.Base.Type<Definition>>> getDefinitionOfUsage() {
		return Optional.ofNullable(definitionOfUsage);
	}

	@Override
	public List<? extends Reference<? extends org.example.Mapping.Interfaces.Base.Type<Definition>>> getSuperTypeOfDefinitions() {
		return List.copyOf(superTypeOfDefinitions);
	}

	private void parseTaxonomy(MappingContext context) {
		System.out.println("\n=== TAXONOMY DEBUG ===");
		System.out.println("element      = " + getSysmlElement().path());
		System.out.println("java class   = " + getClass().getName());
		System.out.println("isTaxonomy   = " + (this instanceof Taxonomy<?>));

		System.out.println(
				"parent       = " +
						getParent()
								.map(Model::getName)
								.orElse("NONE")
		);

		System.out.println(
				"parent tax   = " +
						getParent()
								.flatMap(Model::getTaxonomy)
								.map(x -> x.getReferent().getName())
								.orElse("NONE")
		);

		System.out.println(
				"definition   = " +
						getDefinitionOfUsage()
								.map(x -> x.getReferent().getName())
								.orElse("NONE")
		);

		System.out.println(
				"definition class = " +
						getDefinitionOfUsage()
								.map(x -> x.getReferent().getClass().getName())
								.orElse("NONE")
		);

		System.out.println(
				"definition isTaxonomy = " +
						getDefinitionOfUsage()
								.map(x -> x.getReferent() instanceof Taxonomy<?>)
								.orElse(false)
		);

		System.out.println(
				"supertypes   = " +
						getSuperTypeOfDefinitions().stream()
								.map(x ->
										x.getReferent().getName()
												+ "["
												+ x.getReferent().getClass().getSimpleName()
												+ ", taxonomy="
												+ (x.getReferent() instanceof Taxonomy<?>)
												+ "]"
								)
								.toList()
		);

		// 1. Das Element IST selbst eine Taxonomy
		if (this instanceof Taxonomy<?>) {
			@SuppressWarnings("unchecked")
			Reference<? extends Taxonomy<Definition>> ref =
					(Reference<? extends Taxonomy<Definition>>) (Reference<?>)
							new MappedReference<>(this);

			taxonomyDefinition = Optional.of(ref);
			return;
		}

		// 2. Usage -> Definition ist direkt eine Taxonomy
		var definition = getDefinitionOfUsage();

		if (definition.isPresent()
				&& definition.get().getReferent() instanceof Taxonomy<?>) {

			@SuppressWarnings("unchecked")
			Reference<? extends Taxonomy<Definition>> ref =
					(Reference<? extends Taxonomy<Definition>>) (Reference<?>)
							definition.get();

			taxonomyDefinition = Optional.of(ref);
			return;
		}

		// 3. Definition spezialisiert eine Taxonomy
		for (var superType : getSuperTypeOfDefinitions()) {

			var referent = superType.getReferent();

			if (referent instanceof Taxonomy<?>) {
				@SuppressWarnings("unchecked")
				Reference<? extends Taxonomy<Definition>> ref =
						(Reference<? extends Taxonomy<Definition>>) (Reference<?>)
								superType;

				taxonomyDefinition = Optional.of(ref);
				return;
			}

			// auch transitiv: z.B. Definition -> Definition -> Taxonomy
			var inherited = referent.getTaxonomy();

			if (inherited.isPresent()) {
				taxonomyDefinition =
						(Optional<Reference<? extends Taxonomy<Definition>>>)
								inherited;
				return;
			}
		}

		// 4. Normales enthaltenes Element erbt Taxonomy vom Parent
		taxonomyDefinition = getParent()
				.flatMap(Model::getTaxonomy);
	}

	@Override
	public Optional<Reference<? extends Taxonomy<Definition>>> getTaxonomy() {
		return taxonomyDefinition;
	}

	public boolean isTwinLibraryRoot(Type type) {
		String qn = type.getQualifiedName();

		return Arrays.stream(TwinLibraryNamespace.values())
				.anyMatch(root -> root.toString().equals(qn));
	}

}