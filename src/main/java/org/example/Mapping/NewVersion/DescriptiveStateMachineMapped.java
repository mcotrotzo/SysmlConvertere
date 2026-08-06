package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.DescriptiveStateMachine;
import org.example.Mapping.Interfaces.TriggerConfiguration;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.StateUsage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MappedElementType(LibraryNameSpaces.DESCRIPTIVE_STATE_MACHINE)
@ToString(callSuper = true)
public class DescriptiveStateMachineMapped extends StateMachineMapped implements DescriptiveStateMachine {
	private Set<TriggerConfigurationMapped> triggerConfiguration = new HashSet<>();

	public DescriptiveStateMachineMapped(StateUsage sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TriggerConfiguration> getTriggerConfiguration() {
		return new ArrayList<>(triggerConfiguration);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		triggerConfiguration = new HashSet<>(context.mapSlot(this, "triggerConfiguration", TriggerConfigurationMapped.class));
	}
}
