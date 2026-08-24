package org.example.Mapping.NewVersion.FullTwinMapped.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.FullTwin.TwinUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.FullTwinMapped.TwinMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(value = LibraryNameSpaces.TWIN)
@ToString(callSuper = true)
public class TwinUsageMapped extends TwinMapped<Feature> implements TwinUsage {
	public TwinUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
