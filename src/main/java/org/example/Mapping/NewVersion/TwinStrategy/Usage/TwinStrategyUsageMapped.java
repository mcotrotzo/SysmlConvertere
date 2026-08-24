package org.example.Mapping.NewVersion.TwinStrategy.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinStrategy.Usage.StrategyUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.TwinAction.Usage.TwinActionBlockUsage;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.STRATEGY)
@ToString(callSuper = true)
public class TwinStrategyUsageMapped extends TwinActionBlockUsage<Feature> implements StrategyUsage {
	public TwinStrategyUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
