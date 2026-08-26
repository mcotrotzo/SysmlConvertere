package org.example.Mapping.NewVersion.TwinStrategy.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.TwinStrategy.Strategy;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.TwinAction.TwinActionBlockMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.ActionDefinition;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.STRATEGY)
@ToString(callSuper = true)
public class TwinStrategyMapped<T extends TypeKind> extends TwinActionBlockMapped<Type,T> implements Strategy<T> {


	public TwinStrategyMapped(ActionUsage sysmlElement) {
		super(sysmlElement);
	}

	public TwinStrategyMapped(ActionDefinition sysmlElement) {
		super(sysmlElement);
	}
}
