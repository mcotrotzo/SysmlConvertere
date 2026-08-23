package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Definition.TwinBaseTypeDefinition;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

@ToString(callSuper = true)
@MappedElementType(LibraryNameSpaces.TWIN_ATTRIBUTE)
public class TwinBaseAttributeDefinitionMapped extends TwinAttributeDefinitionMapped implements TwinBaseTypeDefinition {
	public TwinBaseAttributeDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

	}
}
