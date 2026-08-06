package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.PrescriptiveStrategy;
import org.example.Mapping.Interfaces.TriggerConfiguration;
import org.example.Mapping.Interfaces.TwinBooleanAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MappedElementType(LibraryNameSpaces.CUSTOM_PRESCRIPTIVE_STRATEGY)
@ToString(callSuper = true)
public class PrescriptiveStrategyMapped extends CustomStrategyMapped implements PrescriptiveStrategy {
	private Set<TriggerConfigurationMapped> triggerConfiguration = new HashSet<>();
	private Set<TwinBooleanMapped> condition = new HashSet<>();

	public PrescriptiveStrategyMapped(Type sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		triggerConfiguration = new HashSet<>(context.mapSlot(this, "triggerConfig_", TriggerConfigurationMapped.class));
		condition = new HashSet<>(context.mapSlot(this, "condition_", TwinBooleanMapped.class));
	}

	@Override
	public List<TriggerConfiguration> getTriggerConfiguration() {
		return new ArrayList<>(triggerConfiguration);
	}

	@Override
	public List<TwinBooleanAttribute> getCondition() {
		return new ArrayList<>(condition);
	}


}
