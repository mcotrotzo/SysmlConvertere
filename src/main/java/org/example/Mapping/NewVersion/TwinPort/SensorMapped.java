package org.example.Mapping.NewVersion.TwinPort;


import lombok.ToString;
import org.example.Mapping.Interfaces.Sensors;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TwinAttributeMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MappedElementType(LibraryNameSpaces.SENSOR)
@ToString(callSuper = true)
public class SensorMapped extends TwinPortMapped implements Sensors {

	private Set<TwinAttributeMapped> attributes = new HashSet<>();

	public SensorMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinAttribute> getAttributes() {
		return new ArrayList<>(attributes);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		attributes = new HashSet<>(context.mapSlot(this, "measurements", TwinAttributeMapped.class));
	}
}