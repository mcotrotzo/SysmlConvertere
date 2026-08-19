package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.DescriptiveStrategy;
import org.example.Mapping.Interfaces.TriggerConfiguration;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.DESCRIPTIVE_STRATEGY)
@ToString(callSuper = true)
public class DescriptiveStrategyMapped extends CustomStrategyMapped implements DescriptiveStrategy {
	public DescriptiveStrategyMapped(Type sysmlElement) {
		super(sysmlElement);
	}
	private List<TriggerConfigurationMapped> triggerConfiguration = new ArrayList<>();

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
