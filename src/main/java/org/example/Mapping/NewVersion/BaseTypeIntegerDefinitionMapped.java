package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseIntegerDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Definition;
@MappedElementType(LibraryNameSpaces.TWIN_INTEGER)
@ToString(callSuper = true)
public class BaseTypeIntegerDefinitionMapped extends BaseTypeDefinitionMapped implements BaseIntegerDefinition {

	public BaseTypeIntegerDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
