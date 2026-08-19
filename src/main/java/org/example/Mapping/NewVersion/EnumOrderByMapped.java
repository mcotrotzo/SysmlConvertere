package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.EnumOrderBy;
import org.example.Mapping.Interfaces.EnumTimeUnit;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

@MappedElementType(LibraryNameSpaces.ORDER_BY)
@ToString(callSuper = true)
public class EnumOrderByMapped extends EnumAttribute<EnumOrderBy> {
	public EnumOrderByMapped(Feature sysmlElement) {
		super(sysmlElement);
	}

	@Override
	protected Class<EnumOrderBy> getEnumClass() {
		return EnumOrderBy.class;
	}
}
