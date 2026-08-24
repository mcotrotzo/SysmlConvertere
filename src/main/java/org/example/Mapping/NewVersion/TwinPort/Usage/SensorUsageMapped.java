package org.example.Mapping.NewVersion.TwinPort.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinPort.Usage.SensorUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinPort.SensorMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;
@MappedElementType(LibraryNameSpaces.SENSOR)
@ToString(callSuper = true)
public class SensorUsageMapped extends SensorMapped<Feature> implements SensorUsage {
	public SensorUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
