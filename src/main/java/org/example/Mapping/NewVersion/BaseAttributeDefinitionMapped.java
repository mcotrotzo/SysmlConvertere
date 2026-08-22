package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTypeDefinition;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeDefinitionMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

@ToString(callSuper = true)
@MappedElementType(LibraryNameSpaces.TWIN_ATTRIBUTE)
public class BaseAttributeDefinitionMapped extends TwinAttributeDefinitionMapped implements BaseTypeDefinition {
	public BaseAttributeDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

	}
}
