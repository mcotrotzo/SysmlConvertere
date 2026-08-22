package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.Action;
import org.example.Mapping.Interfaces.CustomCalculation;
import org.example.Mapping.Interfaces.Succession;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeUsageMapped;
import org.example.Mapping.TwinAction.TwinActionBaseUsage;
import org.example.Mapping.TwinAction.TwinSuccessionAction;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.*;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.CUSTOM_CALCULATION)
@ToString(callSuper = true)
public class CustomCalculationMapped extends FunctionMapped<Function> implements CustomCalculation {
	private List<TwinAttributeUsageMapped> inputs = new ArrayList<>();
	private List<TwinAttributeUsageMapped> outputs = new ArrayList<>();

	private List<TwinSuccessionAction> successions = new ArrayList<>();

	private List<TwinActionBaseUsage<?>> actions = new ArrayList<>();
	private List<TwinAttributeUsageMapped> localAttributes = new ArrayList<>();


	public CustomCalculationMapped(Function sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		inputs = context.mapSlot(this, "inputs", TwinAttributeUsageMapped.class);
		outputs = context.mapSlot(this, "outputs", TwinAttributeUsageMapped.class);
		actions = context.mapOwned(this, ActionUsage.class, TwinActionBaseUsage.getRawClass());
		successions = context.mapOwned(this, SuccessionAsUsage.class, TwinSuccessionAction.class

		);
		localAttributes = context.mapSlot(this, "local_Attributes", TwinAttributeUsageMapped.class);
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
	public List<Action> getActions() {
		List<Action> result = new ArrayList<>();
		for (TwinActionBaseUsage<?> a : actions) result.add((Action) a);
		return result;
	}

	@Override
	public List<Succession> getSuccessions() {
		return new ArrayList<>(successions);
	}

}