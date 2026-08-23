package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Definition.TwinAttributeDefinition;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.NewVersion.Abstract.MappedElementDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;

import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.TWIN_ATTRIBUTE)
@ToString(callSuper = true)
public class TwinAttributeDefinitionMapped
        extends MappedElementDefinition<Classifier>
        implements TwinAttributeDefinition {

    private final List<Reference<? extends TwinAttributeDefinition>> superTypes = new ArrayList<>();

	public TwinAttributeDefinitionMapped(Classifier sysmlElement) {
        super(sysmlElement);
    }

    @Override
    public List<Reference<? extends TwinAttributeDefinition>> getSuperTypes() {
        return List.copyOf(superTypes);
    }

	@Override
	public void parse(MappingContext context) throws MappingException {

		for (var subclassification :
				getSysmlElement().getOwnedSubclassification()) {

			var superClassifier =
					subclassification.getSuperclassifier();

			if (!(superClassifier instanceof Classifier definition)) {
				continue;
			}

			if (context.getUtils()
					.isFromStandardOrDTLibrary(definition)) {
				continue;
			}

			superTypes.add(
					context.mapReference(
							definition,
							TwinAttributeDefinitionMapped.class
					)
			);
		}
	}
}
