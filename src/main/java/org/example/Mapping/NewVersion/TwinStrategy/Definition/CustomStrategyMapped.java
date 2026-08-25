package org.example.Mapping.NewVersion.TwinStrategy.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinStrategy.Definition.CustomStrategyDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

@MappedElementType(LibraryNameSpaces.CUSTOM_STRATEGY)
@ToString(callSuper = true)
public class CustomStrategyMapped extends TwinStrategyMapped implements CustomStrategyDefinition {
	public CustomStrategyMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
