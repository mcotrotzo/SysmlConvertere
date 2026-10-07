package org.example.Mapping.Model;

import org.example.Mapping.Model.Action.TwinTriggerActionCore;
import org.example.Mapping.Model.Action.TwinTriggerActionDefinition;
import org.example.Mapping.Model.Action.TwinTriggerActionUsage;
import org.example.Mapping.Model.Action.TwinTriggerDefinition;
import org.example.Mapping.Model.Action.TwinTriggerUsage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.example.Mapping.Model.Action.*;
import org.example.Mapping.Model.Attribute.*;
import org.example.Mapping.Model.Database.*;
import org.example.Mapping.Model.EnumAttribute.*;
import org.example.Mapping.Model.Expression.*;
import org.example.Mapping.Model.Federation.FederationTwinDefinition;
import org.example.Mapping.Model.Federation.FederationTwinUsage;
import org.example.Mapping.Model.Flow.*;
import org.example.Mapping.Model.Function.BaseFunctionDefinition;
import org.example.Mapping.Model.Function.CustomCalculationDefinition;
import org.example.Mapping.Model.Port.*;
import org.example.Mapping.Model.Protocol.*;
import org.example.Mapping.Model.StateMachine.TwinStateDefinition;
import org.example.Mapping.Model.StateMachine.TwinStateMachineDefinition;
import org.example.Mapping.Model.StateMachine.TwinStateMachineUsage;
import org.example.Mapping.Model.StateMachine.TwinStateUsage;
import org.example.Mapping.Model.Strategy.*;
import org.example.Mapping.Model.Taxonomy.Base.*;
import org.example.Mapping.Model.Taxonomy.DescriptiveModel.DescriptiveModelDefinition;
import org.example.Mapping.Model.Taxonomy.DescriptiveModel.DescriptiveModelUsage;
import org.example.Mapping.Model.Taxonomy.PhysicalTwin.PhysicalTwinDefiniton;
import org.example.Mapping.Model.Taxonomy.PhysicalTwin.PhysicalTwinUsage;
import org.example.Mapping.Model.Taxonomy.PredictiveModel.PredictiveModelDefinition;
import org.example.Mapping.Model.Taxonomy.PredictiveModel.PredictiveModelUsage;
import org.example.Mapping.Model.Taxonomy.PrescriptiveModel.PrescriptiveModelDefinition;
import org.example.Mapping.Model.Taxonomy.PrescriptiveModel.PrescriptiveModelUsage;
import org.example.Mapping.Model.Taxonomy.Shadow.ShadowDefinition;
import org.example.Mapping.Model.Taxonomy.Shadow.ShadowUsage;
import org.example.Mapping.Model.Twin.TwinDefinition;
import org.example.Mapping.Model.Twin.TwinUsage;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.AssignmentActionUsage;
import org.omg.sysml.lang.sysml.Behavior;
import org.omg.sysml.lang.sysml.BooleanExpression;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.ConstructorExpression;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.FeatureChainExpression;
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;
import org.omg.sysml.lang.sysml.FlowUsage;
import org.omg.sysml.lang.sysml.ForLoopActionUsage;
import org.omg.sysml.lang.sysml.Function;
import org.omg.sysml.lang.sysml.IfActionUsage;
import org.omg.sysml.lang.sysml.InvocationExpression;
import org.omg.sysml.lang.sysml.LiteralBoolean;
import org.omg.sysml.lang.sysml.LiteralInteger;
import org.omg.sysml.lang.sysml.LiteralRational;
import org.omg.sysml.lang.sysml.LiteralString;
import org.omg.sysml.lang.sysml.SuccessionAsUsage;
import org.omg.sysml.lang.sysml.SysMLPackage;
import org.omg.sysml.lang.sysml.TransitionUsage;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.WhileLoopActionUsage;
import org.omg.sysml.util.TypeUtil;

import java.util.*;
import java.util.function.BiFunction;


public class LibClassRegistry {




	record Entry(BiFunction<Classifier, Mapper, ? extends Model<?>> definition,
				 BiFunction<Feature, Mapper, ? extends Model<?>> usage,
				 BiFunction<Element, Mapper, ? extends Model<?>> plain) {}

	private final Map<LibraryNameSpaces, Entry> entries = new EnumMap<>(LibraryNameSpaces.class);

	private final Map<EClass, Entry> metaEntries = new HashMap<>();

	private final Map<EClass, Entry> byMetaclass = new HashMap<>();
	private final Map<LibraryNameSpaces, Type> libTypes;
	private final Map<LibraryNameSpaces, List<LibraryNameSpaces>> ancestors = new EnumMap<>(LibraryNameSpaces.class);
	private final List<LibraryNameSpaces> bottomUp;

	public LibClassRegistry(Map<LibraryNameSpaces, Type> libTypes) {
		this.libTypes = libTypes;
		registry();
		buildMetaclassTable();

		for (var a : libTypes.keySet()) {
			ancestors.put(a, libTypes.keySet().stream()
					.filter(b -> b != a && TypeUtil.specializes(libTypes.get(a), libTypes.get(b)))
					.toList());
		}
		bottomUp = new ArrayList<>(libTypes.keySet());
		bottomUp.sort(Comparator.comparingInt(a -> -ancestors.get(a).size()));
	}

	private void registry() {
		// ---------------- taxonomy (intermediate classes: core and definition class are given here) ----------------
		both(LibraryNameSpaces.TWIN_TAXONOMY,
				def(Classifier.class, (c, m) -> new TaxonomyDefinition<>(c, () -> new TaxonomyCore(c, m), m)),
				use(Feature.class, (f, m) -> new TaxonomyUsage<>(f, () -> new TaxonomyCore(f, m), m, TaxonomyDefinition.class)));
		both(LibraryNameSpaces.PHYSICAL_TAXONOMY,
				def(Classifier.class, (c, m) -> new PhysicalTaxonomyDefinition<>(c, () -> new TaxonomyCore(c, m), m)),
				use(Feature.class, (f, m) -> new PhysicalTaxonomyUsage<>(f, () -> new TaxonomyCore(f, m), m, PhysicalTaxonomyDefinition.class)));
		both(LibraryNameSpaces.SHADOW_TAXONOMY,
				def(Classifier.class, (c, m) -> new ShadowTaxonomyDefinition<>(c, () -> new TaxonomyCore(c, m), m)),
				use(Feature.class, (f, m) -> new ShadowTaxonomyUsage<>(f, () -> new TaxonomyCore(f, m), m, ShadowTaxonomyDefinition.class)));
		both(LibraryNameSpaces.CLOUD_TWIN_TAXONOMY,
				def(Classifier.class, (c, m) -> new CloudTwinTaxonomyDefinition<>(c, () -> new TaxonomyCore(c, m), m)),
				use(Feature.class, (f, m) -> new CloudTwinTaxonomyUsage<>(f, () -> new TaxonomyCore(f, m), m, CloudTwinTaxonomyDefinition.class)));
		both(LibraryNameSpaces.DESCRIPTIVE_TAXONOMY,
				def(Classifier.class, (c, m) -> new DescriptiveTaxonomyDefinition<>(c, () -> new TaxonomyCore(c, m), m)),
				use(Feature.class, (f, m) -> new DescriptiveTaxonomyUsage<>(f, () -> new TaxonomyCore(f, m), m, DescriptiveTaxonomyDefinition.class)));
		both(LibraryNameSpaces.PREDICTIVE_TAXONOMY,
				def(Classifier.class, (c, m) -> new PredictiveTaxonomyDefinition<>(c, () -> new TaxonomyCore(c, m), m)),
				use(Feature.class, (f, m) -> new PredictiveTaxonomyUsage<>(f, () -> new TaxonomyCore(f, m), m, PredictiveTaxonomyDefinition.class)));
		both(LibraryNameSpaces.PRESCRIPTIVE_TAXONOMY,
				def(Classifier.class, (c, m) -> new PrescriptiveTaxonomyDefinition<>(c, () -> new TaxonomyCore(c, m), m)),
				use(Feature.class, (f, m) -> new PrescriptiveTaxonomyUsage<>(f, () -> new TaxonomyCore(f, m), m, PrescriptiveTaxonomyDefinition.class)));

		// ---------------- twin and its parts ----------------
		both(LibraryNameSpaces.TWIN, TwinDefinition::new, TwinUsage::new);
		both(LibraryNameSpaces.FEDERATION_TWIN, FederationTwinDefinition::new, FederationTwinUsage::new);
		both(LibraryNameSpaces.PHYSICAL_TWIN, PhysicalTwinDefiniton::new, PhysicalTwinUsage::new);
		both(LibraryNameSpaces.DESCRIPTIVE_MODEL, DescriptiveModelDefinition::new, DescriptiveModelUsage::new);
		both(LibraryNameSpaces.PREDICTIVE_MODEL, PredictiveModelDefinition::new, PredictiveModelUsage::new);
		both(LibraryNameSpaces.PRESCRIPTIVE_MODEL, PrescriptiveModelDefinition::new, PrescriptiveModelUsage::new);
		both(LibraryNameSpaces.SHADOW, ShadowDefinition::new, ShadowUsage::new);

		// ---------------- ports and protocols ----------------
		both(LibraryNameSpaces.TWIN_PORT,
				def(Classifier.class, (c, m) -> new TwinPortDefinition<>(c, () -> new TwinPortCore(c, m), m)),
				use(Feature.class, (f, m) -> new TwinPortUsage<>(f, () -> new TwinPortCore(f, m), m, TwinPortDefinition.class)));
		both(LibraryNameSpaces.SENSOR, SensorDefinition::new, SensorUsage::new);
		both(LibraryNameSpaces.ACTUATOR, ActuatorDefinition::new, ActuatorUsage::new);
		both(LibraryNameSpaces.CONST_PORT, ConstPortDefinition::new, ConstPortUsage::new);
		both(LibraryNameSpaces.COMMUNICATION_PROTOCOL,
				def(Classifier.class, (c, m) -> new ProtocolDefinition<>(c, () -> new ProtocolCore(c, m), m)),
				use(Feature.class, (f, m) -> new ProtocolUsage<>(f, () -> new ProtocolCore(f, m), m, ProtocolDefinition.class)));
		both(LibraryNameSpaces.MQTT_PROTOCOL, MqttProtocolDefinition::new, MqttProtocolUsage::new);
		both(LibraryNameSpaces.HTTP_PROTOCOL, HttpProtocolDefinition::new, HttpProtocolUsage::new);

		// ---------------- shadow ----------------
		both(LibraryNameSpaces.DATABASE,
				def(Classifier.class, (c, m) -> new DatabaseDefinition<>(c, () -> new DatabaseCore(c, m), m)),
				use(Feature.class, (f, m) -> new DatabaseUsage<>(f, () -> new DatabaseCore(f, m), m, DatabaseDefinition.class)));
		both(LibraryNameSpaces.RELATIONAL_DATABASE, RelationalDatabaseDefinition::new, RelationalDatabaseUsage::new);
		both(LibraryNameSpaces.KEY_VALUE_DATABASE, KeyValueDatabaseDefinition::new, KeyValueDatabaseUsage::new);

		// ---------------- actions, state machines, strategies, calculations ----------------
		both(LibraryNameSpaces.TWIN_ACTION,
				def(Behavior.class, (b, m) -> new TwinActionBlockDefinition<>(b, () -> new ActionBlockCore(b, m), m)),
				use(ActionUsage.class, (a, m) -> new TwinActionBlockUsage<>(a, () -> new ActionBlockCore(a, m), m, TwinActionBlockDefinition.class)));
		for (LibraryNameSpaces state : List.of(LibraryNameSpaces.STATE, LibraryNameSpaces.CONTROL_UNIT_STATE, LibraryNameSpaces.DESCRIPTIVE_STATE)) {
			both(state, def(Behavior.class, TwinStateDefinition::new), use(ActionUsage.class, TwinStateUsage::new));
		}
		for (LibraryNameSpaces machine : List.of(LibraryNameSpaces.TWIN_STATE_MACHINE, LibraryNameSpaces.CONTROL_UNIT, LibraryNameSpaces.DESCRIPTIVE_STATE_MACHINE)) {
			both(machine, def(Behavior.class, TwinStateMachineDefinition::new), use(ActionUsage.class, TwinStateMachineUsage::new));
		}
		both(LibraryNameSpaces.ABSTRACT_ACTION,
				def(Behavior.class, (b, m) -> new TwinActionBlockDefinition<>(b, () -> new ActionBlockCore(b, m), m)),
				use(ActionUsage.class, (a, m) -> new TwinActionBlockUsage<>(a, () -> new ActionBlockCore(a, m), m, TwinActionBlockDefinition.class)));
		both(LibraryNameSpaces.TRIGGER_ACTION,
				def(Behavior.class, (b, m) -> new TwinTriggerActionDefinition<>(b, () -> new TwinTriggerActionCore(b, m), m)),
				use(ActionUsage.class, (a, m) -> new TwinTriggerActionUsage<>(a, () -> new TwinTriggerActionCore(a, m), m, TwinTriggerActionDefinition.class)));
		both(LibraryNameSpaces.TWIN_TRIGGER, TwinTriggerDefinition::new, TwinTriggerUsage::new);
		both(LibraryNameSpaces.STRATEGY,
				def(Behavior.class, (b, m) -> new TwinStrategyDefinition<>(b, () -> new TwinTriggerActionCore(b, m), m)),
				use(ActionUsage.class, (a, m) -> new TwinStrategyUsage<>(a, () -> new TwinTriggerActionCore(a, m), m, TwinStrategyDefinition.class)));
		both(LibraryNameSpaces.CUSTOM_STRATEGY, def(Behavior.class, CustomStrategyDefinition::new), use(ActionUsage.class, CustomStrategyUsage::new));
		both(LibraryNameSpaces.EXTERNAL_STRATEGY, def(Behavior.class, ExternalStrategyDefinition::new), use(ActionUsage.class, ExternalStrategyUsage::new));
		definitionOnly(LibraryNameSpaces.CUSTOM_CALCULATION, def(Behavior.class, CustomCalculationDefinition::new));

		both(LibraryNameSpaces.TWIN_FLOW, def(Behavior.class, TwinFlowDefinition::new), use(FlowUsage.class, TwinFlowUsage::new));
		both(LibraryNameSpaces.PHYSICAL_FLOW, def(Behavior.class, TwinFlowDefinition::new), use(FlowUsage.class, PhysicalFlowUsage::new));
		both(LibraryNameSpaces.DESCRIPTIVE_FLOW, def(Behavior.class, TwinFlowDefinition::new), use(FlowUsage.class, DescriptiveFlowUsage::new));
		both(LibraryNameSpaces.PREDICTIVE_FLOW, def(Behavior.class, TwinFlowDefinition::new), use(FlowUsage.class, PredictiveFlowUsage::new));
		both(LibraryNameSpaces.PRESCRIPTIVE_FLOW, def(Behavior.class, TwinFlowDefinition::new), use(FlowUsage.class, PrescriptiveFlowUsage::new));
		both(LibraryNameSpaces.QUERY_FLOW, def(Behavior.class, TwinFlowDefinition::new), use(FlowUsage.class, QueryFlowUsage::new));
		both(LibraryNameSpaces.DESCRIPTIVE_TO_PREDICTIVE_FLOW, def(Behavior.class, TwinFlowDefinition::new), use(FlowUsage.class, DescriptiveToPredictiveFlowUsage::new));
		both(LibraryNameSpaces.DESCRIPTIVE_TO_PRESCRIPTIVE_FLOW, def(Behavior.class, TwinFlowDefinition::new), use(FlowUsage.class, DescriptiveToPrescriptiveFlowUsage::new));
		both(LibraryNameSpaces.PREDICTIVE_TO_PRESCRIPTIVE_FLOW, def(Behavior.class, TwinFlowDefinition::new), use(FlowUsage.class, PredictiveToPrescriptiveFlowUsage::new));
		both(LibraryNameSpaces.PRESCRIPTIVE_TO_PHYSICAL_FLOW, def(Behavior.class, TwinFlowDefinition::new), use(FlowUsage.class, PrescriptiveToPhysicalFlowUsage::new));
		both(LibraryNameSpaces.FEDERATION_LINK_FLOW, def(Behavior.class, TwinFlowDefinition::new), use(FlowUsage.class, FederationFlowUsage::new));

		// ---------------- attributes and enums ----------------
		BiFunction<Classifier, Mapper, ? extends Model<?>> attributeDefinition =
				(c, m) -> new TwinAttributeDefinition<>(c, () -> new TwinAttributeCore(c, m), m);
		both(LibraryNameSpaces.TWIN_ATTRIBUTE, attributeDefinition,
				(f, m) -> new TwinAttributeUsage<>(f, () -> new TwinAttributeCore(f, m), m, TwinAttributeDefinition.class));
		both(LibraryNameSpaces.TWIN_REAL, TwinAttributeRealDefinition::new, TwinAttributeRealUsage::new);
		both(LibraryNameSpaces.TWIN_INTEGER, TwinAttributeIntegerDefinition::new, TwinAttributeIntegerUsage::new);
		both(LibraryNameSpaces.TWIN_BOOLEAN, TwinAttributeBooleanDefinition::new, TwinAttributeBooleanUsage::new);
		both(LibraryNameSpaces.TWIN_STRING, TwinAttributeStringDefinition::new, TwinAttributeStringUsage::new);
		both(LibraryNameSpaces.TWIN_CUSTOM_TYPE, CustomTypeDefinition::new, CustomTypeUsage::new);
		both(LibraryNameSpaces.CUSTOM_STRATEGY_TYPE, EnumDefinition::new, EnumCustomStrategyTypeUsage::new);
		both(LibraryNameSpaces.ORDER_BY, EnumDefinition::new, EnumOrderByUsage::new);
		both(LibraryNameSpaces.TIME_UNIT, EnumDefinition::new, EnumTimeUnitUsage::new);
		both(LibraryNameSpaces.FEDERATION_LINK_TYPE, EnumDefinition::new, EnumFederationLinkUsage::new);

		// ---------------- metaclasses (elements without library type) ----------------
		usageOnlyMeta(SysMLPackage.Literals.ACTION_USAGE,      // plain bodies of if / while / for
				use(ActionUsage.class, (a, m) -> new TwinActionBlockUsage<>(a, () -> new ActionBlockCore(a, m), m, TwinActionBlockDefinition.class)));
		usageOnlyMeta(SysMLPackage.Literals.ASSIGNMENT_ACTION_USAGE, use(AssignmentActionUsage.class, TwinAssignmentUsage::new));
		usageOnlyMeta(SysMLPackage.Literals.FOR_LOOP_ACTION_USAGE, use(ForLoopActionUsage.class, TwinForLoopUsage::new));
		usageOnlyMeta(SysMLPackage.Literals.IF_ACTION_USAGE, use(IfActionUsage.class, TwinIfElseUsage::new));
		usageOnlyMeta(SysMLPackage.Literals.WHILE_LOOP_ACTION_USAGE, use(WhileLoopActionUsage.class, TwinWhileUsage::new));
		usageOnlyMeta(SysMLPackage.Literals.TRANSITION_USAGE, use(TransitionUsage.class, TwinTransitionUsage::new));
		usageOnlyMeta(SysMLPackage.Literals.SUCCESSION_AS_USAGE, use(SuccessionAsUsage.class, TwinSuccessionUsage::new));

		usageOnlyMeta(SysMLPackage.Literals.INVOCATION_EXPRESSION, use(InvocationExpression.class, TwinCalculationUsage::new));
		usageOnlyMeta(SysMLPackage.Literals.FEATURE_CHAIN_EXPRESSION, use(FeatureChainExpression.class, TwinFeatureChainUsage::new));
		usageOnlyMeta(SysMLPackage.Literals.CONSTRUCTOR_EXPRESSION, use(ConstructorExpression.class, TwinConstructorUsage::new));
		usageOnlyMeta(SysMLPackage.Literals.FEATURE_REFERENCE_EXPRESSION, use(FeatureReferenceExpression.class, TwinFeatureReferenceUsage::new));
		usageOnlyMeta(SysMLPackage.Literals.BOOLEAN_EXPRESSION, use(BooleanExpression.class, TwinBooleanExpressionUsage::new));
		usageOnlyMeta(SysMLPackage.Literals.LITERAL_BOOLEAN, use(LiteralBoolean.class, TwinLiteralBooleanUsage::new));
		usageOnlyMeta(SysMLPackage.Literals.LITERAL_INTEGER, use(LiteralInteger.class, TwinLiteralIntegerUsage::new));
		usageOnlyMeta(SysMLPackage.Literals.LITERAL_RATIONAL, use(LiteralRational.class, TwinLiteralRealUsage::new));
		usageOnlyMeta(SysMLPackage.Literals.LITERAL_STRING, use(LiteralString.class, TwinLiteralStringUsage::new));


		definitionOnlyMeta(SysMLPackage.Literals.FUNCTION, def(Function.class, BaseFunctionDefinition::new));
	}


	private <S extends Classifier> BiFunction<Classifier, Mapper, Model<?>> def(Class<S> type, BiFunction<S, Mapper, ? extends Model<?>> constructor) {
		return (c, m) -> type.isInstance(c) ? constructor.apply(type.cast(c), m) : null;
	}


	private <S extends Feature> BiFunction<Feature, Mapper, Model<?>> use(Class<S> type, BiFunction<S, Mapper, ? extends Model<?>> constructor) {
		return (f, m) -> type.isInstance(f) ? constructor.apply(type.cast(f), m) : null;
	}

	public Type getLibType(LibraryNameSpaces match) {

		return libTypes.get(match);
	}


	void both(LibraryNameSpaces t,
			  BiFunction<Classifier, Mapper, ? extends Model<?>> d,
			  BiFunction<Feature, Mapper, ? extends Model<?>> u) {
		put(t, new Entry(d, u, null));
	}

	void usageOnly(LibraryNameSpaces t, BiFunction<Feature, Mapper, ? extends Model<?>> u) {
		put(t, new Entry(null, u, null));
	}

	void definitionOnly(LibraryNameSpaces t, BiFunction<Classifier, Mapper, ? extends Model<?>> d) {
		put(t, new Entry(d, null, null));
	}

	void plain(LibraryNameSpaces t, BiFunction<Element, Mapper, ? extends Model<?>> p) {
		put(t, new Entry(null, null, p));
	}

	private void put(LibraryNameSpaces t, Entry e) {
		if (entries.putIfAbsent(t, e) != null) {
			throw new IllegalStateException("Library type registered twice: " + t);
		}
	}

	void bothMeta(EClass c,
				  BiFunction<Classifier, Mapper, ? extends Model<?>> d,
				  BiFunction<Feature, Mapper, ? extends Model<?>> u) {
		putMeta(c, new Entry(d, u, null));
	}

	void usageOnlyMeta(EClass c, BiFunction<Feature, Mapper, ? extends Model<?>> u) {
		putMeta(c, new Entry(null, u, null));
	}

	void definitionOnlyMeta(EClass c, BiFunction<Classifier, Mapper, ? extends Model<?>> d) {
		putMeta(c, new Entry(d, null, null));
	}

	void plainMeta(EClass c, BiFunction<Element, Mapper, ? extends Model<?>> p) {
		putMeta(c, new Entry(null, null, p));
	}

	private void putMeta(EClass c, Entry e) {
		if (metaEntries.putIfAbsent(c, e) != null) {
			throw new IllegalStateException("Metaclass registered twice: " + c.getName());
		}
	}


	private void buildMetaclassTable() {
		for (EClassifier classifier : SysMLPackage.eINSTANCE.getEClassifiers()) {
			if (classifier instanceof EClass c) {
				Entry e = findMostSpecific(c);
				if (e != null) byMetaclass.put(c, e);
			}
		}
	}

	private Entry findMostSpecific(EClass start) {
		List<EClass> level = List.of(start);
		Set<EClass> seen = new HashSet<>();
		while (!level.isEmpty()) {
			List<Entry> hits = new ArrayList<>();
			List<EClass> next = new ArrayList<>();
			for (EClass c : level) {
				if (!seen.add(c)) continue;
				Entry e = metaEntries.get(c);
				if (e != null) hits.add(e);
				next.addAll(c.getESuperTypes());
			}
			if (hits.size() > 1) {
				throw new IllegalStateException("Ambiguous metaclass registration for " + start.getName());
			}
			if (hits.size() == 1) return hits.get(0);
			level = next;
		}
		return null;
	}


	public Model<?> getInstance(LibraryNameSpaces libraryType, Element sysmlElement, Mapper mapper) {
		Entry entry = entries.get(libraryType);
		if (entry == null) {
			throw new IllegalArgumentException("No entry registered for library type " + libraryType);
		}
		Model<?> created = create(entry, sysmlElement, mapper, libraryType.toString());
		if (created == null) created = getMetaInstance(sysmlElement, mapper);
		if (created == null) throw new IllegalArgumentException("No factory for " + libraryType + " / " + sysmlElement.eClass().getName());
		return created;
	}

	public Model<?> getMetaInstance(Element sysmlElement, Mapper mapper) {
		Entry entry = byMetaclass.get(sysmlElement.eClass());
		if (entry == null) return null;
		return create(entry, sysmlElement, mapper, sysmlElement.eClass().getName());
	}

	private Model<?> create(Entry entry, Element sysmlElement, Mapper mapper, String key) {
		if (sysmlElement instanceof Classifier classifier && entry.definition() != null) {
			return entry.definition().apply(classifier, mapper);
		}
		if (sysmlElement instanceof Feature feature && entry.usage() != null) {
			return entry.usage().apply(feature, mapper);
		}
		if (entry.plain() != null) {
			return entry.plain().apply(sysmlElement, mapper);
		}
		return null;
	}


	public LibraryNameSpaces match(Type e) {
		List<LibraryNameSpaces> hits = new ArrayList<>();
		Set<LibraryNameSpaces> covered = new HashSet<>();
		for (LibraryNameSpaces lib : bottomUp) {
			if (covered.contains(lib) || !TypeUtil.specializes(e, libTypes.get(lib))) continue;
			hits.add(lib);
			covered.addAll(ancestors.get(lib));
		}
		if (hits.size() > 1) throw new IllegalStateException("ambiguous library type for " + e.getName() + ": " + hits);
		return hits.isEmpty() ? null : hits.get(0);
	}
}