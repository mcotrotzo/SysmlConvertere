package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseBooleanDefinition;
import org.example.Mapping.Interfaces.BaseTypeDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Definition;

@MappedElementType(LibraryNameSpaces.TWIN_BOOLEAN)
@ToString(callSuper = true)
public class BaseTypeBooleanDefinitionMapped extends BaseTypeDefinitionMapped implements BaseBooleanDefinition {

	public BaseTypeBooleanDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
