package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinBooleanAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinAttribute.TwinAttributeUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.TWIN_BOOLEAN)
@ToString(callSuper = true)
public class TwinBooleanMappedUsage extends TwinAttributeUsageMapped implements TwinBooleanAttributeUsage {
	public TwinBooleanMappedUsage(Feature sysmlElement) {
		super(sysmlElement);
	}
}
