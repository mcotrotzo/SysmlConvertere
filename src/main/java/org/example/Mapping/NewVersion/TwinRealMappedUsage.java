package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinRealAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinAttribute.TwinAttributeUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.TWIN_REAL)
@ToString(callSuper = true)
public class TwinRealMappedUsage extends TwinAttributeUsageMapped implements TwinRealAttributeUsage {
	public TwinRealMappedUsage(Feature sysmlElement) {
		super(sysmlElement);
	}


}
