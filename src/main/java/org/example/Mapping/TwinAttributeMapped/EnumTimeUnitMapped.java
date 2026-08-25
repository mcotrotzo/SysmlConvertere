package org.example.Mapping.TwinAttributeMapped;

import lombok.ToString;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumTimeUnit;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.TIME_UNIT)
@ToString(callSuper = true)
public class EnumTimeUnitMapped<Z extends TypeKind>
		extends EnumAttribute<EnumTimeUnit,Z> {


	protected EnumTimeUnitMapped(Feature sysmlElement) {
		super(sysmlElement);
	}

	@Override
	protected Class<EnumTimeUnit> getEnumClass() {
		return EnumTimeUnit.class;
	}
}