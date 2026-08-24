package org.example.Mapping.NewVersion.TwinPort.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinPort.Usage.ActuatorUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinPort.ActuatorMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;
@MappedElementType(LibraryNameSpaces.ACTUATOR)
@ToString(callSuper = true)
public class ActuatorUsageMapped extends ActuatorMapped<Feature> implements ActuatorUsage {
	public ActuatorUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
