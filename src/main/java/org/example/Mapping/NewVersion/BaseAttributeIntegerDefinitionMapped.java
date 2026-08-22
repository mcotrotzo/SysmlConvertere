package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseIntegerDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

@MappedElementType(LibraryNameSpaces.TWIN_INTEGER)
@ToString(callSuper = true)
public class BaseAttributeIntegerDefinitionMapped extends BaseAttributeDefinitionMapped implements BaseIntegerDefinition {

	public BaseAttributeIntegerDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
