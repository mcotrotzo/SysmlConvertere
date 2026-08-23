package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseStringUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.TWIN_STRING)
@ToString(callSuper = true)
public class TwinStringMappedUsage extends TwinAttributeUsageMapped implements TwinBaseStringUsage {
	public TwinStringMappedUsage(Feature sysmlElement) {
		super(sysmlElement);
	}
}
