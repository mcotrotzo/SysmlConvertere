package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseStringDefinition;
import org.example.Mapping.Interfaces.BaseTypeDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Definition;

@MappedElementType(LibraryNameSpaces.TWIN_STRING)
@ToString(callSuper = true)
public class BaseTypeStringDefinitionMapped extends BaseTypeDefinitionMapped implements BaseStringDefinition {

	public BaseTypeStringDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
