package org.example.Mapping.NewVersion.TwinStrategy.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.TwinStrategy.CustomStrategy;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

@MappedElementType(LibraryNameSpaces.CUSTOM_STRATEGY)
@ToString(callSuper = true)
public class CustomStrategyMapped<T extends TypeKind> extends TwinStrategyMapped<T> implements CustomStrategy<T> {
	public CustomStrategyMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
