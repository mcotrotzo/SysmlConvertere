package org.example.Mapping.NewVersion.TwinPort.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinPort.Usage.TwinPortUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinPort.TwinPortMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;
@MappedElementType(LibraryNameSpaces.TWIN_PORT)
@ToString(callSuper = true)
public abstract class TwinPortUsageMapped extends TwinPortMapped<Feature> implements TwinPortUsage {
	public TwinPortUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
