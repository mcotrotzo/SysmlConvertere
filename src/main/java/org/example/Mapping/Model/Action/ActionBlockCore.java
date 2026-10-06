package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Role;
import lombok.Getter;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import org.example.Mapping.Model.Slots;
import org.omg.sysml.lang.sysml.Feature;

import java.util.List;

import org.omg.sysml.lang.sysml.Type;

public class ActionBlockCore extends ActionBodyCore {
	@Getter private List<TwinAttributeUsage<?, ?>> inputs = List.of();
	@Getter private List<TwinAttributeUsage<?, ?>> outputs = List.of();
	@Getter private List<TwinAttributeUsage<?, ?>> localAttributes = List.of();

	public ActionBlockCore(Type sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public void fillSlots(AbstractModel<?> owner) {
		super.fillSlots(owner);
		Class<TwinAttributeUsage<?, ?>> attributeClass = Slots.rawClassOf(TwinAttributeUsage.class);
		List<Feature> in = sysmlElement.getInput();
		List<Feature> out = sysmlElement.getOutput();
		inputs = Slots.mapAll(mapper, owner, in, attributeClass);
		outputs = Slots.mapAll(mapper, owner, out, attributeClass);
		localAttributes = mapper.mapSlot("local_Attributes", owner, attributeClass);
		inputs.forEach(attribute -> attribute.addRole(Role.ACTION));
		outputs.forEach(attribute -> attribute.addRole(Role.ACTION));
		localAttributes.forEach(attribute -> attribute.addRole(Role.LOCAL));
	}
}
