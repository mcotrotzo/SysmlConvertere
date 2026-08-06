package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.TriggerConfiguration;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.TRIGGER_CONFIGURATION)
@ToString(callSuper = true)
public class TriggerConfigurationMapped extends MappedElement<Type> implements TriggerConfiguration {
	public TriggerConfigurationMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

	}

}
