package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.Strategy;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.TwinStringAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeUsageMapped;
import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@MappedMetaclass
public abstract class CustomStrategyMapped extends MappedElement<Type> implements Strategy {

	private TwinStringMappedUsage lambdaPath;
	private List<TwinAttributeUsageMapped> inputs = new ArrayList<>();
	private List<TwinAttributeUsageMapped> outputs = new ArrayList<>();

	public CustomStrategyMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinAttributeUsage> getInputs() {
		return new ArrayList<>(inputs);
	}

	@Override
	public List<TwinAttributeUsage> getOutputs() {
		return new ArrayList<>(outputs);
	}

	@Override
	public TwinStringAttributeUsage getLambdaPath() {

		return lambdaPath;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		List<TwinStringMappedUsage> lambdaPathSet = context.mapSlot(this, "lambdaPath", TwinStringMappedUsage.class);
		lambdaPath = lambdaPathSet.stream().findFirst().orElseThrow(() -> new MappingException("Lambda path is empty %s".formatted(getName())));
		inputs = context.mapSlot(this, "inputs", TwinAttributeUsageMapped.class);
		outputs = context.mapSlot(this, "outputs", TwinAttributeUsageMapped.class);
	}
}
