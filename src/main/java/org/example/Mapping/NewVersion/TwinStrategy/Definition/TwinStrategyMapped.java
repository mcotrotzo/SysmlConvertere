package org.example.Mapping.NewVersion.TwinStrategy.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinStrategy.Definition.StrategyDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.TwinAction.TwinActionBlockMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

@MappedElementType(LibraryNameSpaces.STRATEGY)
@ToString(callSuper = true)
public class TwinStrategyMapped extends TwinActionBlockMapped<Classifier> implements StrategyDefinition {
	public TwinStrategyMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
