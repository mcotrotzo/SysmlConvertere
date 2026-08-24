package org.example.Mapping.NewVersion.TwinPort.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinPort.Definition.ActuatorDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinPort.ActuatorMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Definition;
@MappedElementType(LibraryNameSpaces.ACTUATOR)
@ToString(callSuper = true)
public class ActuatorDefinitionMapped extends ActuatorMapped<Definition> implements ActuatorDefinition {
	public ActuatorDefinitionMapped(Definition sysmlElement) {
		super(sysmlElement);
	}
}
