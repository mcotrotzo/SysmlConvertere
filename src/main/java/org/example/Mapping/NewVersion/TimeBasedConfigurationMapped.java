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
	private EnumTimeUnit triggerIntervalUnit;

	public TimeBasedConfigurationMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinIntegerAttribute> getTriggerInterval() {
		return new ArrayList<>(triggerInterval);
	}

	@Override
	public EnumTimeUnit getTriggerIntervalUnit() {
		return triggerIntervalUnit;
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		triggerInterval = context.mapSlot(this, "triggerInterval_", TwinIntegerMapped.class);
		Set<TwinAttributeMapped> triggerIntervalUnitSet = new HashSet<>(context.mapSlot(this, "triggerIntervalUnit_", TwinAttributeMapped.class));
		if(!triggerIntervalUnitSet.isEmpty()){
			triggerIntervalUnit = context.extractEnum(triggerIntervalUnitSet.iterator().next(), EnumTimeUnit.class);
		}
	}
}