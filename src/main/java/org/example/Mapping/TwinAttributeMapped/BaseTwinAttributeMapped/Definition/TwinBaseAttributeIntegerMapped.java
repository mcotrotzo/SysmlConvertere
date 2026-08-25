package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseInteger;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.TWIN_INTEGER)
@ToString(callSuper = true)
public class TwinBaseAttributeIntegerMapped<T extends TypeKind> extends TwinBaseAttributeMapped<T> implements TwinBaseInteger<T> {

	public TwinBaseAttributeIntegerMapped(Type sysmlElement) {
		super(sysmlElement);
	}
}
