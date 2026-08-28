package org.example.Mapping.NewVersion.Abstract;

import lombok.ToString;
import org.eclipse.ocl.util.Tuple;
import org.example.Mapping.AdditionalRoles;
import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.RoleClass;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.BaseTaxonomy.Taxonomy;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
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

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		parseTypeRelations(context);
	}

	protected final void parseTypeRelations(MappingContext context) throws MappingException {
		if (getSysmlElement() instanceof Feature feature) {
			List<Classifier> definitions = feature.getType().stream().peek(x -> System.out.println(" Type of: " + feature.getQualifiedName() + x.getQualifiedName())).filter(Classifier.class::isInstance).map(Classifier.class::cast).filter(x -> !context.getUtils().isFromStandardLibrary(x)).toList();

			if (!definitions.isEmpty()) {
				Classifier definition = mostSpecificDefinition(definitions);
				if (isTwinLibraryRoot(definition)) {
					return;
				}
				definitionOfUsage = context.mapReference(definition, rawClassOf(MappedElement.class));
			}
		}

		if (getSysmlElement() instanceof Classifier classifier) {
			for (var subclassification : classifier.getOwnedSubclassification()) {
				var superClassifier = subclassification.getSuperclassifier();
				System.out.println(" Subclassification of " + classifier.getQualifiedName() + " -> " + (superClassifier != null ? superClassifier.getQualifiedName() : "null"));
				if (!(superClassifier instanceof Classifier definition)) continue;


				if (context.getUtils().isFromStandardLibrary(definition)) continue;
				if (isTwinLibraryRoot(definition)) {
					continue;
				}
				superTypeOfDefinitions.add(context.mapReference(definition, rawClassOf(MappedElement.class)));
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



	public boolean isTwinLibraryRoot(Type type) {
		String qn = type.getQualifiedName();

		return Arrays.stream(TwinLibraryNamespace.values())
				.anyMatch(root -> root.toString().equals(qn));
	}


	@Override
	public boolean resolveRole() {
		boolean changed = super.resolveRole();

		if (getDefinitionOfUsage().isPresent()) {
			var definition = getDefinitionOfUsage().get().getReferent();

			for (var role : definition.getRoleClasses()) {
				boolean added = addRole(role);

				if (added) {
					System.out.println(
							"  DEFINITION ROLE -> " + getName()
									+ " gets " + role
									+ " from " + definition.getName()
					);
				}

				changed |= added;
			}
		}

		for (var superTypeReference : getSuperTypeOfDefinitions()) {
			var superType = superTypeReference.getReferent();

			for (var role : superType.getRoleClasses()) {
				boolean added = addRole(role);

				if (added) {
					System.out.println(
							"  SUPERTYPE ROLE -> " + getName()
									+ " gets " + role
									+ " from " + superType.getName()
					);
				}

				changed |= added;
			}
		}

		for (AdditionalRoles additionalRole : addAdditionalRoles()) {
			for (var model : additionalRole.models()) {
				for (var role : additionalRole.roles()) {

					boolean added = model.addRole(role);

					if (added) {
						System.out.println(
								"  ADDITIONAL ROLE -> " + model.getName()
										+ " gets " + role
										+ " from " + getName()
						);
					}

					changed |= added;
				}
			}
		}

		return changed;
	}

	protected List<AdditionalRoles> addAdditionalRoles() {
		return new ArrayList<>();
	}

}

