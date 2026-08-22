package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseStringDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

@MappedElementType(LibraryNameSpaces.TWIN_STRING)
@ToString(callSuper = true)
public class BaseAttributeStringDefinitionMapped extends BaseAttributeDefinitionMapped implements BaseStringDefinition {

	public BaseAttributeStringDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
