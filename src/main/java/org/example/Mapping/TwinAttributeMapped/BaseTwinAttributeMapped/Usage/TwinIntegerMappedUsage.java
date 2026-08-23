package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseIntegerUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.TWIN_INTEGER)
@ToString(callSuper = true)
public class TwinIntegerMappedUsage extends TwinAttributeUsageMapped implements TwinBaseIntegerUsage {
	public TwinIntegerMappedUsage(Feature sysmlElement) {
		super(sysmlElement);
	}
}
