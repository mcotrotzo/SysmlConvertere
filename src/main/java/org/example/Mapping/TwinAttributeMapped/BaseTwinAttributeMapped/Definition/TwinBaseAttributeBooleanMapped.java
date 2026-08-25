package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseBoolean;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.TWIN_BOOLEAN)
@ToString(callSuper = true)
public class TwinBaseAttributeBooleanMapped<T extends TypeKind> extends TwinBaseAttributeMapped<T> implements TwinBaseBoolean<T> {

	public TwinBaseAttributeBooleanMapped(Type sysmlElement) {
		super(sysmlElement);
	}
}
