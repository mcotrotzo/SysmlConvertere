package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.EnumerationDefinition;

@MappedMetaclass
@ToString(callSuper = true)
public class EnumDefinitionMapped
		extends MappedElement<EnumerationDefinition>
		implements Model {

	public EnumDefinitionMapped(EnumerationDefinition sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
	}
}