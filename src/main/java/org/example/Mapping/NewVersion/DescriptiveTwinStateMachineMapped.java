package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.DescriptiveTwinStateMachine;
import org.example.Mapping.Interfaces.TriggerConfiguration;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TwinStateMachine.TwinStateMachineMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.StateUsage;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.DESCRIPTIVE_STATE_MACHINE)
@ToString(callSuper = true)
public class DescriptiveTwinStateMachineMapped extends TwinStateMachineMapped implements DescriptiveTwinStateMachine {
	private List<TriggerConfigurationMapped> triggerConfiguration = new ArrayList<>();

	public DescriptiveTwinStateMachineMapped(StateUsage sysmlElement) {
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
}
