package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.EnumTimeUnit;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.TIME_UNIT)
@ToString(callSuper = true)
public class EnumTimeUnitMapped
		extends EnumAttribute<EnumTimeUnit> {


	protected EnumTimeUnitMapped(Feature sysmlElement) {
		super(sysmlElement);
	}

	@Override
	protected Class<EnumTimeUnit> getEnumClass() {
		return EnumTimeUnit.class;
	}
}