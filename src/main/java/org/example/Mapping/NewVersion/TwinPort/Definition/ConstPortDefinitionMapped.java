package org.example.Mapping.NewVersion.TwinPort.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinPort.Definition.ConstPortDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinPort.ConstPortMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
@MappedElementType(LibraryNameSpaces.CONST_PORT)
@ToString(callSuper = true)
public class ConstPortDefinitionMapped extends ConstPortMapped<Classifier> implements ConstPortDefinition {
	public ConstPortDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
