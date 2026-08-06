package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.TimeBasedConfiguration;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.Interfaces.TwinIntegerAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MappedElementType(LibraryNameSpaces.TIME_BASED_CONFIGURATION)
@ToString(callSuper = true)
public class TimeBasedConfigurationMapped extends TriggerConfigurationMapped implements TimeBasedConfiguration {
	private Set<TwinIntegerMapped> triggerInterval = new HashSet<>();
	private Set<TwinAttributeMapped> triggerIntervalUnit = new HashSet<>();

	public TimeBasedConfigurationMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinIntegerAttribute> getTriggerInterval() {
		return new ArrayList<>(triggerInterval);
	}

	@Override
	public List<TwinAttribute> getTriggerIntervalUnit() {
		return new ArrayList<>(triggerIntervalUnit);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		triggerInterval = new HashSet<>(context.mapSlot(this, "triggerInterval_", TwinIntegerMapped.class));
		triggerIntervalUnit = new HashSet<>(context.mapSlot(this, "triggerIntervalUnit_", TwinAttributeMapped.class));
	}
}