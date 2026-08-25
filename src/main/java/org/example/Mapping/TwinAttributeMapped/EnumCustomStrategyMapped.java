package org.example.Mapping.TwinAttributeMapped;

import lombok.ToString;

import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.TwinEnumPackage.CustomStrategyType;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;
@MappedElementType(LibraryNameSpaces.CUSTOM_STRATEGY_TYPE)
@ToString(callSuper = true)
public class EnumCustomStrategyMapped<Z extends TypeKind> extends EnumAttribute<CustomStrategyType,Z> {
	protected EnumCustomStrategyMapped(Feature sysmlElement) {
		super(sysmlElement);
	}

	@Override
	protected Class<CustomStrategyType> getEnumClass() {
		return CustomStrategyType.class;
	}
}
