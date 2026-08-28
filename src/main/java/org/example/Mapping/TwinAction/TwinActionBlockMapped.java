package org.example.Mapping.TwinAction;

import org.example.Mapping.AdditionalRoles;
import org.example.Mapping.Interfaces.Base.TypeKind.RoleClass;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAction.Block;
import org.example.Mapping.Interfaces.TwinAction.Succession;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
import org.example.Mapping.NewVersion.Abstract.CompartmentMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.*;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.TWIN_ACTION)
public class TwinActionBlockMapped<T extends Type, Z extends TypeKind>
		extends TwinActionMapped<T, Z>
		implements Block<Z> {

	protected List<TwinActionMapped<ActionUsage, Usage>> twinActionBlockUsages =
			new ArrayList<>();

	private List<TwinSuccessionAction> twinSuccessionActions =
			new ArrayList<>();

	private CompartmentContainerMapped<TwinAttributeMapped<Usage>> inputs =
			new CompartmentContainerMapped<>();

	private CompartmentContainerMapped<TwinAttributeMapped<Usage>> outputs =
			new CompartmentContainerMapped<>();

	private CompartmentContainerMapped<TwinAttributeMapped<Usage>> localAttributes =
			new CompartmentContainerMapped<>();


	@SuppressWarnings("unchecked")
	public TwinActionBlockMapped(ActionUsage sysmlElement) {
		super((T) sysmlElement);
	}

	@SuppressWarnings("unchecked")
	public TwinActionBlockMapped(ActionDefinition sysmlElement) {
		super((T) sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		inputs = context.mapSlot(
				this,
				"inputs",
				TwinAttributeMapped.getRawUsageClass()
		);

		outputs = context.mapSlot(
				this,
				"outputs",
				TwinAttributeMapped.getRawUsageClass()
		);

		localAttributes = context.mapSlot(
				this,
				"local_Attributes",
				TwinAttributeMapped.getRawUsageClass()
		);

		twinActionBlockUsages = context.mapOwned(
				this,
				ActionUsage.class,
				rawClassOf(TwinActionMapped.class)
		);

		twinSuccessionActions = context.mapOwned(
				this,
				SuccessionAsUsage.class,
				TwinSuccessionAction.class
		);
	}


	@Override
	public CompartmentContainerMapped<TwinAttributeMapped<Usage>> getInputs() {
		return inputs;
	}

	@Override
	public CompartmentContainerMapped<TwinAttributeMapped<Usage>> getOutputs() {
		return outputs;
	}

	@Override
	public CompartmentContainerMapped<TwinAttributeMapped<Usage>> localAttributes() {
		return localAttributes;
	}

	@Override
	public List<TwinActionMapped<ActionUsage, Usage>> getActions() {
		return new ArrayList<>(twinActionBlockUsages);
	}

	@Override
	public List<TwinSuccessionAction> getSuccessions() {
		return new ArrayList<>(twinSuccessionActions);
	}


	@Override
	protected List<AdditionalRoles> addAdditionalRoles() {
		return List.of(
				new AdditionalRoles(
						inputs.getCompartment()
								.stream()
								.map(compartment -> compartment.getElement())
								.toList(),
						List.of(
								new RoleClass(
										rawClassOf(Action.class),
										Role.ACTION
								)
						)
				),
				new AdditionalRoles(
						outputs.getCompartment()
								.stream()
								.map(compartment -> compartment.getElement())
								.toList(),
						List.of(
								new RoleClass(
										rawClassOf(Action.class),
										Role.ACTION
								)
						)
				),
				new AdditionalRoles(
						localAttributes.getCompartment()
								.stream()
								.map(CompartmentMapped::getElement)
								.toList(),
						List.of(
								new RoleClass(
										rawClassOf(Action.class),
										Role.LOCAL
								)
						)
				)
		);
	}
}