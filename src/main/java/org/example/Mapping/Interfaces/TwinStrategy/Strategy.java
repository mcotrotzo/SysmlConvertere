package org.example.Mapping.Interfaces.TwinStrategy;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.TwinAction.Block;

public interface Strategy<T extends TypeKind> extends Block<T> {

}
