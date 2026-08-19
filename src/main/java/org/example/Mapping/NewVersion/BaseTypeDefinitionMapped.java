package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTypeDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Definition;

@ToString(callSuper = true)
@MappedElementType(LibraryNameSpaces.TWIN_ATTRIBUTE)
public class BaseTypeDefinitionMapped extends TwinTypeDefinitionMapped implements BaseTypeDefinition {
	public BaseTypeDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

	}
}
