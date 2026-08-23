package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseBooleanUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.TWIN_BOOLEAN)
@ToString(callSuper = true)
public class TwinBooleanMappedUsage extends TwinAttributeUsageMapped implements TwinBaseBooleanUsage {
	public TwinBooleanMappedUsage(Feature sysmlElement) {
		super(sysmlElement);
	}
}
