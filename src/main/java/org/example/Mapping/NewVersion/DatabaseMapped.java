package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.Database;
import org.example.Mapping.Interfaces.TwinIntegerAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MappedElementType(LibraryNameSpaces.DATABASE)
@ToString(callSuper = true)
public class DatabaseMapped extends MappedElement<Type> implements Database {


	private Set<TwinIntegerMapped> durationInDays = new HashSet<>();

	public DatabaseMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinIntegerAttribute> getDurationInDays() {
		return new ArrayList<>(durationInDays);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		durationInDays = new HashSet<>(context.mapSlot(this, "durationInDays", TwinIntegerMapped.class));
	}
}
