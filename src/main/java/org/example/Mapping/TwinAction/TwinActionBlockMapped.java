package org.example.Mapping.TwinAction;


import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAction.Block;
import org.example.Mapping.Interfaces.TwinAction.Succession;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.SuccessionAsUsage;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.TWIN_ACTION)
public abstract class TwinActionBlockMapped<T extends Type,Z extends TypeKind> extends TwinActionMapped<T,Z> implements Block<Z> {
	public TwinActionBlockMapped(T sysmlElement) {
		super(sysmlElement);

	}
	private List<TwinActionMapped<?, Usage>> twinActionBlockUsages = new ArrayList<>();
	private List<TwinSuccessionAction> twinSuccessionActions = new ArrayList<>();
	private List<TwinAttributeMapped<Usage>> inputs = new ArrayList<>();
	private List<TwinAttributeMapped<Usage>> outputs = new ArrayList<>();
	private List<TwinAttributeMapped<Usage>> localAttributes = new ArrayList<>();

	@Override
	public void parse(MappingContext context) throws MappingException {
		inputs = context.mapAttributes(
				this,
				"inputs",
				TwinAttributeMapped.getRawUsageClass(),
				Role.ACTION
		);

		outputs = context.mapAttributes(
				this,
				"outputs",
				TwinAttributeMapped.getRawUsageClass(),
				Role.ACTION
		);

		localAttributes = context.mapAttributes(
				this,
				"local_Attributes",
				TwinAttributeMapped.getRawUsageClass(),
				Role.LOCAL
		);



		twinActionBlockUsages = context.mapOwned(
				this,
				ActionUsage.class,
				getActionMappedUsageClass()
		);

		twinSuccessionActions = context.mapOwned(
				this,
				SuccessionAsUsage.class,
				TwinSuccessionAction.class
		);
	}


	@Override
	public List<Action<Usage>> getActions() {
		return new ArrayList<>(twinActionBlockUsages);
	}

	@Override
	public List<Succession> getSuccessions() {
		return new ArrayList<>(twinSuccessionActions);
	}


	@Override
	public List<TwinAttribute<Usage>> getInputs() {
		return new ArrayList<>(inputs);
	}

	@Override
	public List<TwinAttribute<Usage>> getOutputs() {
		return new ArrayList<>(outputs);
	}

	@Override
	public List<TwinAttribute<Usage>> localAttributes() {
		return new ArrayList<>(localAttributes);
	}
}
