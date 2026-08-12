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


	private List<TwinIntegerMapped> durationInDays = new ArrayList<>();

	public DatabaseMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public TwinIntegerAttribute getDurationInDays() {
		return durationInDays.stream().findFirst().orElseThrow(() -> new RuntimeException("No durationInDays found"));
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		durationInDays = context.mapSlot(this, "durationInDays", TwinIntegerMapped.class);
	}
}
