package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinIntegerAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.TWIN_INTEGER)
@ToString(callSuper = true)
public class TwinIntegerMappedUsage extends TwinAttributeUsageMapped implements TwinIntegerAttributeUsage {
	public TwinIntegerMappedUsage(Feature sysmlElement) {
		super(sysmlElement);
	}
}
