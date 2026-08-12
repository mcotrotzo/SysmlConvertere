package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.CustomTypeDefinition;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Definition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString(callSuper = true)
@MappedElementType(LibraryNameSpaces.TWIN_CUSTOM_TYPE)
public class CustomTypeMappedDefintion
		extends TwinAttributeMapped
		implements CustomTypeDefinition {

	protected List<TwinAttributeMapped> fields = new ArrayList<>();
	private List<MappedReference<? extends CustomTypeDefinition>> parents =
			new ArrayList<>();

	public CustomTypeMappedDefintion(Definition sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<Reference<? extends CustomTypeDefinition>> getParents() {
		return new ArrayList<>(parents);
	}

	@Override
	public List<TwinAttribute> getFields() {
		return new ArrayList<>(fields);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		fields = context.mapSlot(
						this,
						"fields",
						TwinAttributeMapped.class
		);

		Classifier classifier =
				(Classifier) getSysmlElement();

		for (var subclassification :
				classifier.getOwnedSubclassification()) {

			var superClassifier =
					subclassification.getSuperclassifier();

			if (!(superClassifier instanceof Definition definition)) {
				continue;
			}

			if (context.getUtils().isFromStandardOrDTLibrary(definition)) {
				continue;
			}

			parents.add(
					context.mapReference(
							definition,
							CustomTypeMappedDefintion.class
					)
			);
		}
	}
}