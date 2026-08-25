package org.example.Mapping.NewVersion.Database;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.DataBase.Database;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseInteger;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseIntegerUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinIntegerMappedUsage;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.DATABASE)
@ToString(callSuper = true)
public class DatabaseMapped<T extends Type,Z extends TypeKind> extends MappedElement<T,Z> implements Database<Z> {


	private TwinIntegerMappedUsage durationInDays;

	public DatabaseMapped(T sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public TwinBaseInteger<Usage> getDurationInDays() {
		return durationInDays;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		durationInDays = context.mapAttributes(this, "durationInDays", TwinIntegerMappedUsage.class, Role.CONFIG).stream().findFirst().orElseThrow(()-> new MappingException("durationInDays slot not found in DatabaseMapped"));
	}
}
