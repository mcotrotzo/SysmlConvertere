package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.*;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.List;
import java.util.Optional;

@MappedElementType(value = LibraryNameSpaces.TWIN)
@ToString(callSuper = true)
public class MappedTwin extends MappedElement<Type> implements Twin {

	private Optional<PhysicalTwinMapped> physicalTwin = Optional.empty();
	private Optional<ShadowMapped> shadowTwin = Optional.empty();
	private Optional<DescriptiveModelMapped> descriptiveModel = Optional.empty();
	private Optional<PrescriptiveModelMapped> prescriptiveModel = Optional.empty();
	private Optional<PredictiveModelMapped> predictiveModel = Optional.empty();

	public MappedTwin(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		physicalTwin = first(
				context.mapSlot(
						this,
						"physicalTwin",
						PhysicalTwinMapped.class
				)
		);

		shadowTwin = first(
				context.mapSlot(
						this,
						"shadow",
						ShadowMapped.class
				)
		);

		descriptiveModel = first(
				context.mapSlot(
						this,
						"descriptiveModel",
						DescriptiveModelMapped.class
				)
		);

		predictiveModel = first(
				context.mapSlot(
						this,
						"predictiveModel",
						PredictiveModelMapped.class
				)
		);

		prescriptiveModel = first(
				context.mapSlot(
						this,
						"prescriptiveModel",
						PrescriptiveModelMapped.class
				)
		);
	}

	private static <T> Optional<T> first(List<T> values) {
		return values.stream().findFirst();
	}

	@Override
	public Optional<PhysicalTwin> getPhysicalTwin() {
		return physicalTwin.map(x -> x);
	}

	@Override
	public Optional<Shadow> getShadow() {
		return shadowTwin.map(x -> x);
	}

	@Override
	public Optional<DescriptiveModel> getDescriptiveModel() {
		return descriptiveModel.map(x -> x);
	}

	@Override
	public Optional<PredictiveModel> getPredictiveModel() {
		return predictiveModel.map(x -> x);
	}

	@Override
	public Optional<PrescriptiveModel> getPrescriptiveModel() {
		return prescriptiveModel.map(x -> x);
	}
}