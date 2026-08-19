package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.Model;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.example.Util.LibraryNameSpaces;
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