package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.Strategy;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.Interfaces.TwinStringAttribute;
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

	private TwinStringMapped lambdaPath;
	private List<TwinAttributeMapped> inputs = new ArrayList<>();
	private List<TwinAttributeMapped> outputs = new ArrayList<>();

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
	public TwinStringAttribute getLambdaPath() {

		return lambdaPath;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		List<TwinStringMapped> lambdaPathSet = context.mapSlot(this, "lambdaPath", TwinStringMapped.class);
		lambdaPath = lambdaPathSet.stream().findFirst().orElseThrow(() -> new MappingException("Lambda path is empty %s".formatted(getName())));
		inputs = context.mapSlot(this, "inputs", TwinAttributeMapped.class);
		outputs = context.mapSlot(this, "outputs", TwinAttributeMapped.class);
	}
}
