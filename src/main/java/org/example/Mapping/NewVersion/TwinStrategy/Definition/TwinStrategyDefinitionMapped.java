package org.example.Mapping.NewVersion.TwinStrategy.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinStrategy.Definition.StrategyDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.TwinAction.Definition.TwinActionBlockDefinitionMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

@MappedElementType(LibraryNameSpaces.STRATEGY)
@ToString(callSuper = true)
public class TwinStrategyDefinitionMapped extends TwinActionBlockDefinitionMapped<Classifier> implements StrategyDefinition {
	public TwinStrategyDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
