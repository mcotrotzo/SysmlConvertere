package org.example.Mapping.NewVersion.TwinStrategy.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinStrategy.Usage.CustomStrategyUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.CUSTOM_STRATEGY)
@ToString(callSuper = true)
public class CustomStrategyUsageMapped extends TwinStrategyUsageMapped implements CustomStrategyUsage {
	public CustomStrategyUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
