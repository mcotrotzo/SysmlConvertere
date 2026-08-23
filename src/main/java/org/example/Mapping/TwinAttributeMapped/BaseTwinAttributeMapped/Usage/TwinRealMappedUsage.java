package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseRealUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.TWIN_REAL)
@ToString(callSuper = true)
public class TwinRealMappedUsage extends TwinAttributeUsageMapped implements TwinBaseRealUsage {
	public TwinRealMappedUsage(Feature sysmlElement) {
		super(sysmlElement);
	}


}
