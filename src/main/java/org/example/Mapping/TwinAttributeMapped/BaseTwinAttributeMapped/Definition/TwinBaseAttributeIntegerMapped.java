package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseInteger;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.TWIN_INTEGER)
@ToString(callSuper = true)
public class TwinBaseAttributeIntegerMapped<T extends TypeKind> extends TwinBaseAttributeMapped<T> implements TwinBaseInteger<T> {

	public TwinBaseAttributeIntegerMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@SuppressWarnings("unchecked")
	public static Class<TwinBaseAttributeIntegerMapped<Usage>> getRawIntegerUsageClass() {
		return (Class<TwinBaseAttributeIntegerMapped<Usage>>) (Class<?>) TwinBaseAttributeIntegerMapped.class;
	}

	@SuppressWarnings("unchecked")
	public static Class<TwinBaseAttributeIntegerMapped<Definition>> getRawIntegerDefinitionClass() {
		return (Class<TwinBaseAttributeIntegerMapped<Definition>>) (Class<?>) TwinBaseAttributeIntegerMapped.class;
	}
}
