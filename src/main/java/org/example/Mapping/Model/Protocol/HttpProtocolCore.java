package org.example.Mapping.Model.Protocol;

import lombok.Getter;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Attribute.TwinAttributeStringUsage;
import org.example.Mapping.Model.Slots;
import org.omg.sysml.lang.sysml.Type;

public class HttpProtocolCore extends ProtocolCore {
	@Getter private TwinAttributeStringUsage url;

	public HttpProtocolCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel owner) {
		url = Slots.exactlyOne(owner, "url", mapper.mapSlot("url", owner, TwinAttributeStringUsage.class));
	}
}
