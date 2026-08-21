package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.PrescriptiveStrategy;
import org.example.Mapping.Interfaces.TriggerConfiguration;
import org.example.Mapping.Interfaces.TwinBooleanAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.*;

@MappedElementType(LibraryNameSpaces.CUSTOM_PRESCRIPTIVE_STRATEGY)
@ToString(callSuper = true)
public class PrescriptiveStrategyMapped extends CustomStrategyMapped implements PrescriptiveStrategy {
	private List<TriggerConfigurationMapped> triggerConfiguration = new ArrayList<>();
	private List<TwinBooleanMappedUsage> condition = new ArrayList<>();

	public PrescriptiveStrategyMapped(Type sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		triggerConfiguration = context.mapSlot(this, "triggerConfig_", TriggerConfigurationMapped.class);
		condition = context.mapSlot(this, "condition_", TwinBooleanMappedUsage.class);
	}

	@Override
	public List<TriggerConfiguration> getTriggerConfiguration() {
		return new ArrayList<>(triggerConfiguration);
	}

	@Override
	public Optional<TwinBooleanAttributeUsage> getCondition() {
		return Optional.ofNullable(condition.getFirst());
	}


}
