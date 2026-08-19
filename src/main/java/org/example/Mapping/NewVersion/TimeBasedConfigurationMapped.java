package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.*;
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
	private List<TwinIntegerMapped> triggerInterval = new ArrayList<>();
	private EnumTimeUnitMapped triggerIntervalUnit;

	public TimeBasedConfigurationMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinIntegerAttribute> getTriggerInterval() {
		return new ArrayList<>(triggerInterval);
	}

	@Override
	public EnumTimeUnit getTriggerIntervalUnit() {
		return triggerIntervalUnit.getValue();
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		triggerInterval = context.mapSlot(this, "triggerInterval_", TwinIntegerMapped.class);
		triggerIntervalUnit = new HashSet<>(context.mapSlot(this, "triggerIntervalUnit_", EnumTimeUnitMapped.class)).stream().findFirst().orElseThrow(() -> new MappingException("No triggerIntervalUnit_ found for TimeBasedConfigurationMapped"));

	}
}