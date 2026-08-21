package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinStringAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinAttribute.TwinAttributeUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.TWIN_STRING)
@ToString(callSuper = true)
public class TwinStringMappedUsage extends TwinAttributeUsageMapped implements TwinStringAttributeUsage {
	public TwinStringMappedUsage(Feature sysmlElement) {
		super(sysmlElement);
	}
}
