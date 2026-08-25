package org.example.Mapping.TwinAttributeMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumOrderBy;
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
