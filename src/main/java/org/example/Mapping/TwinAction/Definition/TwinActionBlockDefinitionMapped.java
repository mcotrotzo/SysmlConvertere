package org.example.Mapping.TwinAction.Definition;


import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAction.Definition.BlockDefinition;
import org.example.Mapping.Interfaces.TwinAction.Succession;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinAction.TwinSuccessionAction;
import org.example.Mapping.TwinAction.Usage.TwinActionUsageMapped;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinAttributeUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.SuccessionAsUsage;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.TWIN_ACTION)
public abstract class TwinActionBlockDefinitionMapped<T extends Classifier> extends TwinActionDefinitionMapped<T> implements BlockDefinition {
	public TwinActionBlockDefinitionMapped(T sysmlElement) {
		super(sysmlElement);

	}

	protected List<TwinActionUsageMapped<?>> twinActionBlockUsages = new ArrayList<>();
	protected List<TwinSuccessionAction> twinSuccessionActions = new ArrayList<>();
	protected List<TwinAttributeUsageMapped> inputs = new ArrayList<>();
	protected List<TwinAttributeUsageMapped> outputs = new ArrayList<>();
	protected List<TwinAttributeUsageMapped> localAttributes = new ArrayList<>();

	public static Class<TwinActionUsageMapped<?>> getRawClass() {
		return (Class<TwinActionUsageMapped<?>>) (Class<?>) TwinActionUsageMapped.class;

	}
	@Override
	public void parse(MappingContext context) throws MappingException {
		inputs = context.mapAttributes(
				this,
				"inputs",
				TwinAttributeUsageMapped.class,
				Role.ACTION
		);

		outputs = context.mapAttributes(
				this,
				"outputs",
				TwinAttributeUsageMapped.class,
				Role.ACTION
		);

		localAttributes = context.mapAttributes(
				this,
				"local_Attributes",
				TwinAttributeUsageMapped.class,
				Role.LOCAL
		);



		twinActionBlockUsages = context.mapOwned(
				this,
				ActionUsage.class,
				getRawClass()
		);

		twinSuccessionActions = context.mapOwned(
				this,
				SuccessionAsUsage.class,
				TwinSuccessionAction.class
		);
	}


	@Override
	public List<Action> getActions() {
		return new ArrayList<>(twinActionBlockUsages);
	}

	@Override
	public List<Succession> getSuccessions() {
		return new ArrayList<>(twinSuccessionActions);
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
	public List<TwinAttributeUsage> localAttributes() {
		return new ArrayList<>(localAttributes);
	}
}
