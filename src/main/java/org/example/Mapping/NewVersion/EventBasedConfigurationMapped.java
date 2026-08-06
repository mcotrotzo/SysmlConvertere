package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.EventBasedConfiguration;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.Interfaces.TwinBooleanAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MappedElementType(LibraryNameSpaces.EVENT_BASED_CONFIGURATION)
@ToString(callSuper = true)
public class EventBasedConfigurationMapped extends TriggerConfigurationMapped implements EventBasedConfiguration {

	private Set<TwinAttributeMapped> triggeringAttributes = new HashSet<>();
	private Set<TwinBooleanMapped> onChange = new HashSet<>();

	public EventBasedConfigurationMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinAttribute> getTriggeringAttributes() {
		return new ArrayList<>(triggeringAttributes);
	}

	@Override
	public List<TwinBooleanAttribute> getOnChange() {
		return new ArrayList<>(onChange);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		triggeringAttributes = new HashSet<>(context.mapSlot(this, "triggeringAtributes_", TwinAttributeMapped.class));
		onChange = new HashSet<>(context.mapSlot(this, "onChange_", TwinBooleanMapped.class));
	}
}