package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Definition.TwinBaseIntegerDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

@MappedElementType(LibraryNameSpaces.TWIN_INTEGER)
@ToString(callSuper = true)
public class TwinBaseAttributeIntegerDefinitionMapped extends TwinBaseAttributeDefinitionMapped implements TwinBaseIntegerDefinition {

	public TwinBaseAttributeIntegerDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
