package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.ControlUnit;
import org.example.Mapping.Interfaces.ReadWriteRoles;
import org.example.Mapping.Interfaces.TriggerConfiguration;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.StateUsage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MappedElementType(LibraryNameSpaces.CONTROL_UNIT)
@ToString(callSuper = true)
public class ControlUnitMapped extends StateMachineMapped implements ControlUnit {
	private List<TriggerConfigurationMapped> triggerConfiguration = new ArrayList<>();

	public ControlUnitMapped(StateUsage sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public List<TriggerConfiguration> getTriggerConfiguration() {
		return new ArrayList<>(triggerConfiguration);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		triggerConfiguration = context.mapSlot(this, "triggerConfiguration", TriggerConfigurationMapped.class);
	}

	@Override
	public Set<ReadWriteRoles> getReadPermissions() {
		return Set.of(
				ReadWriteRoles.SENSOR,
				ReadWriteRoles.CONST,
				ReadWriteRoles.LOCAL,
				ReadWriteRoles.ACTUATOR
		);
	}

	@Override
	public Set<ReadWriteRoles> getWritePermissions() {
		return Set.of(ReadWriteRoles.ACTUATOR,
				ReadWriteRoles.LOCAL






		);
	}
}
