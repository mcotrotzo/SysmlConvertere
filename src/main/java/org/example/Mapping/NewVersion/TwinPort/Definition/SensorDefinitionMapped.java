package org.example.Mapping.NewVersion.TwinPort.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinPort.Definition.SensorDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinPort.SensorMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Definition;
@MappedElementType(LibraryNameSpaces.SENSOR)
@ToString(callSuper = true)
public class SensorDefinitionMapped extends SensorMapped<Definition> implements SensorDefinition {
	public SensorDefinitionMapped(Definition sysmlElement) {
		super(sysmlElement);
	}
}
