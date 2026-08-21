package org.example.Mapping.NewVersion.FullTwinMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.*;
import org.example.Mapping.Interfaces.FullTwin.Twin;
import org.example.Mapping.NewVersion.*;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TaxonomyMapped.Usage.*;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.List;
import java.util.Optional;

@MappedElementType(value = LibraryNameSpaces.TWIN)
@ToString(callSuper = true)
public class TwinMapped<T extends Type> extends MappedElement<T> implements Twin {

	private Optional<PhysicalTwinUsageMapped> physicalTwin = Optional.empty();
	private Optional<ShadowUsageMapped> shadowTwin = Optional.empty();
	private Optional<DescriptiveModelUsageMapped> descriptiveModel = Optional.empty();
	private Optional<PrescriptiveModelUsageMapped> prescriptiveModel = Optional.empty();
	private Optional<PredictiveModelUsageMapped> predictiveModel = Optional.empty();

	public TwinMapped(T sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		physicalTwin = first(
				context.mapSlot(
						this,
						"physicalTwin",
						PhysicalTwinUsageMapped.class
				)
		);

		shadowTwin = first(
				context.mapSlot(
						this,
						"shadow",
						ShadowUsageMapped.class
				)
		);

		descriptiveModel = first(
				context.mapSlot(
						this,
						"descriptiveModel",
						DescriptiveModelUsageMapped.class
				)
		);

		predictiveModel = first(
				context.mapSlot(
						this,
						"predictiveModel",
						PredictiveModelUsageMapped.class
				)
		);

		prescriptiveModel = first(
				context.mapSlot(
						this,
						"prescriptiveModel",
						PrescriptiveModelUsageMapped.class
				)
		);
	}

	private static <T> Optional<T> first(List<T> values) {
		return values.stream().findFirst();
	}

	@Override
	public Optional<PhysicalTwinUsage> getPhysicalTwin() {
		return physicalTwin.map(x -> x);
	}

	@Override
	public Optional<ShadowUsage> getShadow() {
		return shadowTwin.map(x -> x);
	}

	@Override
	public Optional<DescriptiveModelUsage> getDescriptiveModel() {
		return descriptiveModel.map(x -> x);
	}

	@Override
	public Optional<PredictiveModelUsage> getPredictiveModel() {
		return predictiveModel.map(x -> x);
	}

	@Override
	public Optional<PrescriptiveModelUsage> getPrescriptiveModel() {
		return prescriptiveModel.map(x -> x);
	}
}