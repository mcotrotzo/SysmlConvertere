package org.example.Mapping.Interfaces.TwinAction;

import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;

/**
 * Represents an abstract action in the model.
 */
public interface Action<T extends TypeKind> extends Type<T> {
}
