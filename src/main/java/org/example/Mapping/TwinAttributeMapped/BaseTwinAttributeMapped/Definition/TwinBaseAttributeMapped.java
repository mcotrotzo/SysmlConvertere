package org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Type;

@ToString(callSuper = true)
@MappedElementType(LibraryNameSpaces.TWIN_ATTRIBUTE)
public class TwinBaseAttributeMapped<T extends TypeKind> extends TwinAttributeMapped<T> implements TwinBaseType<T> {
	public TwinBaseAttributeMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

	}
}
