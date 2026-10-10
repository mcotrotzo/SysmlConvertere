package org.example.Mapping.Model.Action;

import Mapper.Mapper;
import Model.AbstractType;
import Model.Predefined.MetaClasses.Action.ActionCore;
import Model.Slots;
import lombok.Getter;
import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import org.example.Mapping.Role;
import org.omg.sysml.lang.sysml.Type;

import java.util.List;

public class ActionBlockCore extends ActionCore {

	private static final Class<TwinAttributeUsage<?, ?>> ATTRIBUTE = Slots.rawClassOf(TwinAttributeUsage.class);

	@Getter private List<TwinAttributeUsage<?, ?>> localAttributes = List.of();

	public ActionBlockCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractType<?, ?> owner) {
		super.fillSlots(owner);
		localAttributes = mapper.mapSlot("local_Attributes", owner, ATTRIBUTE);
		localAttributes.forEach(attribute -> attribute.addRole(Role.LOCAL));
	}

	@Override
	protected void mapInputs(AbstractType<?, ?> owner) {
		List<TwinAttributeUsage<?, ?>> mapped = Slots.mapAll(mapper, owner, sysmlElement.getInput(), ATTRIBUTE);
		mapped.forEach(attribute -> attribute.addRole(Role.ACTION));
		inputs = mapped;
	}

	@Override
	protected void mapOutputs(AbstractType<?, ?> owner) {
		List<TwinAttributeUsage<?, ?>> mapped = Slots.mapAll(mapper, owner, sysmlElement.getOutput(), ATTRIBUTE);
		mapped.forEach(attribute -> attribute.addRole(Role.ACTION));
		outputs = mapped;
	}


	@Override
	public List<TwinAttributeUsage<?, ?>> getInputs() {
		return inputs.stream().<TwinAttributeUsage<?, ?>>map(ATTRIBUTE::cast).toList();
	}

	@Override
	public List<TwinAttributeUsage<?, ?>> getOutputs() {
		return outputs.stream().<TwinAttributeUsage<?, ?>>map(ATTRIBUTE::cast).toList();
	}
}
