package org.example.Mapping.Model.Action;

import Mapper.Mapper;
import Model.Annotation.MappedMetaClass;
import Model.Predefined.MetaClasses.Action.EmptyActionCore;
import Model.Predefined.MetaClasses.Action.ForLoopMapUsage;
import Model.Slots;
import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import org.example.Mapping.Role;
import org.omg.sysml.lang.sysml.ForLoopActionUsage;

import java.util.Optional;
import java.util.stream.Stream;

@MappedMetaClass(value = ForLoopActionUsage.class, core = EmptyActionCore.class)
public class TwinForLoopUsage extends ForLoopMapUsage {

	private static final Class<TwinAttributeUsage<?, ?>> ATTRIBUTE = Slots.rawClassOf(TwinAttributeUsage.class);

	public TwinForLoopUsage(ForLoopActionUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	// the loop variable is a twin attribute with the FOR_LOOP_VARIABLE role
	@Override
	protected void mapLoopVariable() {
		Optional<TwinAttributeUsage<?, ?>> mapped = Slots.mapAll(instance, this, Stream.ofNullable(sysmlElement.getLoopVariable()).toList(), ATTRIBUTE).stream().findFirst();
		mapped.ifPresent(variable -> variable.addRole(Role.FOR_LOOP_VARIABLE));
		loopVariable = mapped;
	}

	@Override
	public Optional<TwinAttributeUsage<?, ?>> getLoopVariable() {
		return loopVariable.map(ATTRIBUTE::cast);
	}
}
