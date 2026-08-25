package org.example.Mapping.Interfaces.DataBase;

import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseInteger;

public interface Database<T extends TypeKind> extends Type<T> {
	TwinBaseInteger<Usage> getDurationInDays();
}
