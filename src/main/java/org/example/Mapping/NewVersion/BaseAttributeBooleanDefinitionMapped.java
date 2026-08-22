package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseBooleanDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

@MappedElementType(LibraryNameSpaces.TWIN_BOOLEAN)
@ToString(callSuper = true)
public class BaseAttributeBooleanDefinitionMapped extends BaseAttributeDefinitionMapped implements BaseBooleanDefinition {

	public BaseAttributeBooleanDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
