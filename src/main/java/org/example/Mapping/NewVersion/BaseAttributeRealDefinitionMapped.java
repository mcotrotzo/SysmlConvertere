package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseRealDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

@MappedElementType(LibraryNameSpaces.TWIN_REAL)
@ToString(callSuper = true)
public class BaseAttributeRealDefinitionMapped extends BaseAttributeDefinitionMapped implements BaseRealDefinition {
	public BaseAttributeRealDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}

}

