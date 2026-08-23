package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Definition.TwinBaseStringDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

@MappedElementType(LibraryNameSpaces.TWIN_STRING)
@ToString(callSuper = true)
public class TwinBaseAttributeStringDefinitionMapped extends TwinBaseAttributeDefinitionMapped implements TwinBaseStringDefinition {

	public TwinBaseAttributeStringDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
