package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.CustomTypeDefinition;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.TwinAttribute.TwinAttributeUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Definition;

import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@MappedElementType(LibraryNameSpaces.TWIN_CUSTOM_TYPE)
public class CustomTypeMappedDefintion
		extends TwinTypeDefinitionMapped
		implements CustomTypeDefinition {

	protected List<TwinAttributeUsageMapped> fields = new ArrayList<>();
	private List<MappedReference<? extends CustomTypeDefinition>> parents =
			new ArrayList<>();

	public CustomTypeMappedDefintion(Classifier sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<Reference<? extends CustomTypeDefinition>> getParents() {
		return new ArrayList<>(parents);
	}

	@Override
	public List<TwinAttributeUsage> getFields() {
		return new ArrayList<>(fields);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		fields = context.mapSlot(
						this,
						"fields",
						TwinAttributeUsageMapped.class
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