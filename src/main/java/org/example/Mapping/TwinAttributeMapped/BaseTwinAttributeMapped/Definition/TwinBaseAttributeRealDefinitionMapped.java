package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Definition.TwinBaseRealDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

@MappedElementType(LibraryNameSpaces.TWIN_REAL)
@ToString(callSuper = true)
public class TwinBaseAttributeRealDefinitionMapped extends TwinBaseAttributeDefinitionMapped implements TwinBaseRealDefinition {
	public TwinBaseAttributeRealDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}

}

