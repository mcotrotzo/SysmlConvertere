package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.Strategy;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString(callSuper = true)
@MappedMetaclass
public abstract class CustomStrategyMapped extends MappedElement<Type> implements Strategy {

	private Set<TwinStringMapped> lambdaPath = new HashSet<>();
	private Set<TwinAttributeMapped> inputs = new HashSet<>();
	private Set<TwinAttributeMapped> outputs = new HashSet<>();

	public CustomStrategyMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinAttribute> getInputs() {
		return new ArrayList<>(inputs);
	}

	@Override
	public List<TwinAttribute> getOutputs() {
		return new ArrayList<>(outputs);
	}

	@Override
	public List<TwinStringMapped> getLambdaPath() {
		return new ArrayList<>(lambdaPath);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		lambdaPath = new HashSet<>(context.mapSlot(this, "lambdaPath", TwinStringMapped.class));
		inputs = new HashSet<>(context.mapSlot(this, "inputs", TwinAttributeMapped.class));
		outputs = new HashSet<>(context.mapSlot(this, "outputs", TwinAttributeMapped.class));
	}
}
