package org.example.Mapping.NewVersion.FullTwinMapped;



import lombok.ToString;
import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.*;
import org.example.Mapping.Interfaces.FullTwin.Twin;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinFlow.QueryFlow;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
import org.example.Mapping.NewVersion.Abstract.CompartmentMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TaxonomyMapped.*;
import org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy.CloudTwinTaxonomyMapped;
import org.example.Mapping.NewVersion.TwinFlow.Definition.*;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.Optional;

@MappedElementType(LibraryNameSpaces.TWIN)
@ToString(callSuper = true)
public class TwinMapped<Z extends TypeKind> extends CloudTwinTaxonomyMapped<Z> implements Twin<Z> {

	private Optional<CompartmentMapped<PhysicalTwinMapped<Usage>>> physicalTwin = Optional.empty();

	private Optional<CompartmentMapped<ShadowMapped<Usage>>> shadowTwin = Optional.empty();

	private Optional<CompartmentMapped<DescriptiveModelMapped<Usage>>> descriptiveModel = Optional.empty();

	private Optional<CompartmentMapped<PrescriptiveModelMapped<Usage>>> prescriptiveModel = Optional.empty();

	private Optional<CompartmentMapped<PredictiveModelMapped<Usage>>> predictiveModel = Optional.empty();


	private CompartmentContainerMapped<QueryFlowMapped<Usage>> queryFlows;

	private CompartmentContainerMapped<DescriptiveToPredictiveFlowMapped<Usage>> descriptiveToPredictiveFlows;

	private CompartmentContainerMapped<DescriptiveToPrescriptiveFlowMapped<Usage>> descriptiveToPrescriptiveFlows;

	private CompartmentContainerMapped<PredictiveToPrescriptiveFlowMapped<Usage>> predictiveToPrescriptiveFlows;

	private CompartmentContainerMapped<PrescriptiveToPhysicalFlowMapped<Usage>> prescriptiveToPhysicalFlows;


	public TwinMapped(Type sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		queryFlows = context.mapSlot(this, "queryFlows", rawClassOf(QueryFlowMapped.class));

		descriptiveToPredictiveFlows = context.mapSlot(this, "descriptiveToPredictiveFlows", rawClassOf(DescriptiveToPredictiveFlowMapped.class));

		descriptiveToPrescriptiveFlows = context.mapSlot(this, "descriptiveToPrescriptiveFlows", rawClassOf(DescriptiveToPrescriptiveFlowMapped.class));

		predictiveToPrescriptiveFlows = context.mapSlot(this, "predictiveToPrescriptiveFlows", rawClassOf(PredictiveToPrescriptiveFlowMapped.class));

		prescriptiveToPhysicalFlows = context.mapSlot(this, "prescriptiveToPhysicalFlows", rawClassOf(PrescriptiveToPhysicalFlowMapped.class));


		Class<PhysicalTwinMapped<Usage>> physicalTwinClass = rawClassOf(PhysicalTwinMapped.class);

		Class<ShadowMapped<Usage>> shadowTwinClass = rawClassOf(ShadowMapped.class);

		Class<DescriptiveModelMapped<Usage>> descriptiveModelClass = rawClassOf(DescriptiveModelMapped.class);

		Class<PredictiveModelMapped<Usage>> predictiveModelClass = rawClassOf(PredictiveModelMapped.class);

		Class<PrescriptiveModelMapped<Usage>> prescriptiveModelClass = rawClassOf(PrescriptiveModelMapped.class);


		physicalTwin = context.mapSlot(this, "physicalTwin", physicalTwinClass).getCompartment().stream().findFirst();

		shadowTwin = context.mapSlot(this, "shadow", shadowTwinClass).getCompartment().stream().findFirst();

		descriptiveModel = context.mapSlot(this, "descriptiveModel", descriptiveModelClass).getCompartment().stream().findFirst();

		predictiveModel = context.mapSlot(this, "predictiveModel", predictiveModelClass).getCompartment().stream().findFirst();

		prescriptiveModel = context.mapSlot(this, "prescriptiveModel", prescriptiveModelClass).getCompartment().stream().findFirst();
	}


	@Override
	public Optional<? extends Compartment<? extends PhysicalTwin<Usage>>> getPhysicalTwin() {
		return physicalTwin;
	}

	@Override
	public Optional<? extends Compartment<? extends Shadow<Usage>>> getShadow() {
		return shadowTwin;
	}

	@Override
	public Optional<? extends Compartment<? extends DescriptiveModel<Usage>>> getDescriptiveModel() {
		return descriptiveModel;
	}

	@Override
	public Optional<? extends Compartment<? extends PredictiveModel<Usage>>> getPredictiveModel() {
		return predictiveModel;
	}

	@Override
	public Optional<? extends Compartment<? extends PrescriptiveModel<Usage>>> getPrescriptiveModel() {
		return prescriptiveModel;
	}


	@Override
	public CompartmentContainerMapped<QueryFlowMapped<Usage>> getQueryFlows() {
		return queryFlows;
	}

	@Override
	public CompartmentContainerMapped<DescriptiveToPredictiveFlowMapped<Usage>> getDescriptiveToPredictiveFlows() {
		return descriptiveToPredictiveFlows;
	}

	@Override
	public CompartmentContainerMapped<DescriptiveToPrescriptiveFlowMapped<Usage>> getDescriptiveToPrescriptiveFlows() {
		return descriptiveToPrescriptiveFlows;
	}

	@Override
	public CompartmentContainerMapped<PredictiveToPrescriptiveFlowMapped<Usage>> getPredictiveToPrescriptiveFlows() {
		return predictiveToPrescriptiveFlows;
	}

	@Override
	public CompartmentContainerMapped<PrescriptiveToPhysicalFlowMapped<Usage>> getPrescriptiveToPhysicalFlows() {
		return prescriptiveToPhysicalFlows;
	}
}