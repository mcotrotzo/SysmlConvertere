package org.example.Mapping.NewVersion.FullTwinMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.*;
import org.example.Mapping.Interfaces.FullTwin.Twin;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.example.Mapping.Interfaces.TwinFlow.QueryFlow;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TaxonomyMapped.*;
import org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy.CloudTwinTaxonomyMapped;
import org.example.Mapping.NewVersion.TwinFlow.Definition.*;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@MappedElementType(LibraryNameSpaces.TWIN)
@ToString(callSuper = true)
public class TwinMapped<Z extends TypeKind> extends CloudTwinTaxonomyMapped<Z> implements Twin<Z> {

	private Optional<PhysicalTwinMapped<Usage>> physicalTwin = Optional.empty();
	private Optional<ShadowMapped<Usage>> shadowTwin = Optional.empty();
	private Optional<DescriptiveModelMapped<Usage>> descriptiveModel = Optional.empty();
	private Optional<PrescriptiveModelMapped<Usage>> prescriptiveModel = Optional.empty();
	private Optional<PredictiveModelMapped<Usage>> predictiveModel = Optional.empty();

	private List<QueryFlowMapped<Usage>> queryFlows = new ArrayList<>();
	private List<DescriptiveToPredictiveFlowMapped<Usage>> descriptiveToPredictiveFlows = new ArrayList<>();
	private List<DescriptiveToPrescriptiveFlowMapped<Usage>> descriptiveToPrescriptiveFlows = new ArrayList<>();
	private List<PredictiveToPrescriptiveFlowMapped<Usage>> predictiveToPrescriptiveFlows = new ArrayList<>();
	private List<PrescriptiveToPhysicalFlowMapped<Usage>> prescriptiveToPhysicalFlows = new ArrayList<>();

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

		physicalTwin = context.mapSlot(this, "physicalTwin", physicalTwinClass).stream().findFirst();
		shadowTwin = context.mapSlot(this, "shadow", shadowTwinClass).stream().findFirst();
		descriptiveModel = context.mapSlot(this, "descriptiveModel", descriptiveModelClass).stream().findFirst();
		predictiveModel = context.mapSlot(this, "predictiveModel", predictiveModelClass).stream().findFirst();
		prescriptiveModel = context.mapSlot(this, "prescriptiveModel", prescriptiveModelClass).stream().findFirst();
	}

	@Override
	public Optional<PhysicalTwin<Usage>> getPhysicalTwin() {
		return physicalTwin.map(x -> x);
	}

	@Override
	public Optional<Shadow<Usage>> getShadow() {
		return shadowTwin.map(x -> x);
	}

	@Override
	public Optional<DescriptiveModel<Usage>> getDescriptiveModel() {
		return descriptiveModel.map(x -> x);
	}

	@Override
	public Optional<PredictiveModel<Usage>> getPredictiveModel() {
		return predictiveModel.map(x -> x);
	}

	@Override
	public Optional<PrescriptiveModel<Usage>> getPrescriptiveModel() {
		return prescriptiveModel.map(x -> x);
	}

	@Override
	public List<QueryFlow<Usage>> getQueryFlows() {
		return new ArrayList<>(queryFlows);
	}

	@Override
	public List<Flow<Usage>> getDescriptiveToPredictiveFlows() {
		return new ArrayList<>(descriptiveToPredictiveFlows);
	}

	@Override
	public List<Flow<Usage>> getDescriptiveToPrescriptiveFlows() {
		return new ArrayList<>(descriptiveToPrescriptiveFlows);
	}

	@Override
	public List<Flow<Usage>> getPredictiveToPrescriptiveFlows() {
		return new ArrayList<>(predictiveToPrescriptiveFlows);
	}

	@Override
	public List<Flow<Usage>> getPrescriptiveToPhysicalFlows() {
		return new ArrayList<>(prescriptiveToPhysicalFlows);
	}
}