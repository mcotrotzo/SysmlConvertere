package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.DescriptiveStrategy;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.DESCRIPTIVE_STRATEGY)
@ToString(callSuper = true)
public class DescriptiveStrategyMapped extends CustomStrategyMapped implements DescriptiveStrategy {
	public DescriptiveStrategyMapped(Type sysmlElement) {
		super(sysmlElement);
	}
}
