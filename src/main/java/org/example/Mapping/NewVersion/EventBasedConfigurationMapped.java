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

	private List<TwinAttributeMapped> triggeringAttributes = new ArrayList<>();
	private List<TwinBooleanMapped> onChange = new ArrayList<>();

	public EventBasedConfigurationMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinAttribute> getTriggeringAttributes() {
		return new ArrayList<>(triggeringAttributes);
	}

	@Override
	public TwinBooleanAttribute getOnChange() {
		return onChange.getFirst();
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		triggeringAttributes = context.mapSlot(this, "triggeringAtributes_", TwinAttributeMapped.class);
		onChange = context.mapSlot(this, "onChange_", TwinBooleanMapped.class);
	}
}