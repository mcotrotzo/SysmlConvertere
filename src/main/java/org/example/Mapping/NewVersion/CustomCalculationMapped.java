package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.Action;
import org.example.Mapping.Interfaces.CustomCalculation;
import org.example.Mapping.Interfaces.Succession;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.TwinAction.TwinActionBaseUsage;
import org.example.Mapping.TwinAction.TwinSuccessionAction;
import org.example.Util.LibraryNameSpaces;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.*;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.CUSTOM_CALCULATION)
@ToString(callSuper = true)
public class CustomCalculationMapped extends FunctionMapped<Function> implements CustomCalculation {
	private List<TwinAttributeMapped> inputs = new ArrayList<>();
	private List<TwinAttributeMapped> outputs = new ArrayList<>();

	private List<TwinSuccessionAction> successions = new ArrayList<>();

	private List<TwinActionBaseUsage<?>> actions = new ArrayList<>();
	private List<TwinAttributeMapped> localAttributes = new ArrayList<>();


	public CustomCalculationMapped(Function sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		inputs = context.mapSlot(this, "inputs", TwinAttributeMapped.class);
		outputs = context.mapSlot(this, "outputs", TwinAttributeMapped.class);
		actions = context.mapOwned(this, ActionUsage.class, TwinActionBaseUsage.getRawClass());
		successions = context.mapOwned(this, SuccessionAsUsage.class, TwinSuccessionAction.class

		);
		localAttributes = context.mapSlot(this, "local_Attributes", TwinAttributeMapped.class);
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