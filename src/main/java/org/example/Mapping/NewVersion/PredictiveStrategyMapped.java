package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.PredictiveStrategy;
import org.example.Mapping.Interfaces.TriggerConfiguration;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString(callSuper = true)
@MappedElementType(LibraryNameSpaces.PREDICTIVE_STRATEGY)
public class PredictiveStrategyMapped extends CustomStrategyMapped implements PredictiveStrategy {

	private List<TriggerConfigurationMapped> triggerConfiguration = new ArrayList<>();

	public PredictiveStrategyMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		triggerConfiguration = context.mapSlot(this, "triggerConfiguration", TriggerConfigurationMapped.class);
	}

	@Override
	public List<TriggerConfiguration> getTriggerConfiguration() {
		return new ArrayList<>(triggerConfiguration);
	}
}
