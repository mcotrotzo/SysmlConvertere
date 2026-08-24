package org.example.Mapping.NewVersion.TwinPort.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinPort.Usage.ConstPortUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinPort.ConstPortMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.CONST_PORT)
@ToString(callSuper = true)
public class ConstPortUsageMapped extends ConstPortMapped<Feature> implements ConstPortUsage {
	public ConstPortUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
