package org.example.Mapping.NewVersion.Database;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.DataBase.Database;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseInteger;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinBaseAttributeIntegerMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.DATABASE)
@ToString(callSuper = true)
public class DatabaseMapped<Z extends TypeKind> extends MappedElement<Type, Z> implements Database<Z> {


	private TwinBaseAttributeIntegerMapped<Usage> durationInDays;

	public DatabaseMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@SuppressWarnings("unchecked")
	public static Class<DatabaseMapped<Usage>> getRawUsageClass() {
		return (Class<DatabaseMapped<Usage>>) (Class<?>) DatabaseMapped.class;
	}

	@SuppressWarnings("unchecked")
	public static Class<DatabaseMapped<Definition>> getRawDefinitionClass() {
		return (Class<DatabaseMapped<Definition>>) (Class<?>) DatabaseMapped.class;
	}

	@Override
	public TwinBaseInteger<Usage> getDurationInDays() {
		return durationInDays;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		durationInDays = context.mapAttributes(this, "durationInDays", TwinBaseAttributeIntegerMapped.getRawIntegerUsageClass(), Role.CONFIG).stream().findFirst().orElseThrow(() -> new MappingException("durationInDays slot not found in DatabaseMapped"));
	}

}
