package org.example.Mapping.NewVersion.TwinPort.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinPort.Definition.TwinPortDefinition;
import org.example.Mapping.Interfaces.TwinPort.TwinPort;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinPort.TwinPortMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
@MappedElementType(LibraryNameSpaces.TWIN_PORT)
@ToString(callSuper = true)
public abstract class TwinPortDefinitionMapped extends TwinPortMapped<Classifier> implements TwinPortDefinition {
	public TwinPortDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
