package org.example.Mapping.NewVersion.TwinPort;

import lombok.ToString;
import org.example.Mapping.Interfaces.Actuators;
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

@MappedElementType(LibraryNameSpaces.ACTUATOR)
@ToString(callSuper = true)
public class ActuatorMapped extends TwinPortMapped implements Actuators {
	private List<TwinAttributeMapped> attributes = new ArrayList<>();

	public ActuatorMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinAttribute> getAttributes() {
		return new ArrayList<>(attributes);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		attributes = context.mapSlot(this, "commands", TwinAttributeMapped.class);
	}
}
