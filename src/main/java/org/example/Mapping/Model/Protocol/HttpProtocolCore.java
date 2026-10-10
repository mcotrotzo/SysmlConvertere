package org.example.Mapping.Model.Protocol;


import Mapper.Mapper;
import Model.AbstractType;
import Model.Slots;
import lombok.Getter;
import org.example.Mapping.Model.Attribute.TwinAttributeStringUsage;
import org.omg.sysml.lang.sysml.Type;

public class HttpProtocolCore extends ProtocolCore {
	@Getter private TwinAttributeStringUsage url;

	public HttpProtocolCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractType owner) {
		url = Slots.exactlyOne(owner, "url", mapper.mapSlot("url", owner, TwinAttributeStringUsage.class));
	}
}
