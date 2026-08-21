package org.example.Util;

public enum LibraryNameSpaces {

	// ========================================================================
	// Twin
	// ========================================================================

	TWIN("TwinDefLibrary::Twin"),

	PHYSICAL_TWIN("TwinDefLibrary::PhysicalTwin"),
	DESCRIPTIVE_MODEL("TwinDefLibrary::DescriptiveModel"),
	PREDICTIVE_MODEL("TwinDefLibrary::PredictiveModel"),
	PRESCRIPTIVE_MODEL("TwinDefLibrary::PrescriptiveModel"),
	SHADOW("TwinDefLibrary::Shadow"),


	// ========================================================================
	// Taxonomy
	// ========================================================================

	TWIN_TAXONOMY("TwinTaxonomyLibrary::TwinTaxonomy"),
	PHYSICAL_TAXONOMY("TwinTaxonomyLibrary::PhysicalTaxonomy"),
	CLOUD_TWIN_TAXONOMY("TwinTaxonomyLibrary::CloudTwinTaxonomy"),

	DESCRIPTIVE_TAXONOMY("TwinTaxonomyLibrary::DescriptiveTaxonomy"),
	PREDICTIVE_TAXONOMY("TwinTaxonomyLibrary::PredictiveTaxonomy"),
	PRESCRIPTIVE_TAXONOMY("TwinTaxonomyLibrary::PrescriptiveTaxonomy"),
	SHADOW_TAXONOMY("TwinTaxonomyLibrary::ShadowTaxonomy"),


	// ========================================================================
	// Twin Actions
	// ========================================================================

	TWIN_ACTION("TwinActionLibrary::TwinAction"),

	PHYSICAL_FLOW("TwinActionLibrary::PhysicalFlow"),
	DESCRIPTIVE_FLOW("TwinActionLibrary::DescriptiveFlow"),
	PREDICTIVE_FLOW("TwinActionLibrary::PredictiveFlow"),
	PRESCRIPTIVE_FLOW("TwinActionLibrary::PrescriptiveFlow"),

	QUERY_FLOW("TwinActionLibrary::QueryFlow"),

	DESCRIPTIVE_TO_PREDICTIVE_FLOW(
			"TwinActionLibrary::DescriptiveToPredictiveFlow"
	),

	DESCRIPTIVE_TO_PRESCRIPTIVE_FLOW(
			"TwinActionLibrary::DescriptiveToPrescriptiveFlow"
	),

	PREDICTIVE_TO_PRESCRIPTIVE_FLOW(
			"TwinActionLibrary::PredictiveToPrescriptiveFlow"
	),

	PRESCRIPTIVE_TO_PHYSICAL_FLOW(
			"TwinActionLibrary::PrescriptiveToPhysicalFlow"
	),


	// ========================================================================
	// Strategies
	// ========================================================================

	STRATEGY("TwinStrategyLibrary::Strategy"),
	CUSTOM_STRATEGY("TwinStrategyLibrary::CustomStrategy"),
	EXTERNAL_STRATEGY("TwinStrategyLibrary::ExternalStrategy"),


	// ========================================================================
	// State Machines
	// ========================================================================

	STATE("TwinStateMachineLibrary::State"),

	DESCRIPTIVE_STATE_MACHINE(
			"DescriptiveModelLibrary::DescriptiveStateMachine"
	),

	DESCRIPTIVE_STATE(
			"DescriptiveModelLibrary::DescriptiveState"
	),


	// ========================================================================
	// Physical Twin
	// ========================================================================

	ACTUATOR("PhysicalTwinLibrary::Actuator"),
	SENSOR("PhysicalTwinLibrary::Sensor"),
	TWIN_PORT("PhysicalTwinLibrary::TwinPort"),

	CONTROL_UNIT("PhysicalTwinLibrary::ControlUnit"),
	CONTROL_UNIT_STATE("PhysicalTwinLibrary::ControlUnitState"),

	COMMUNICATION_PROTOCOL(
			"PhysicalTwinLibrary::CommunicationProtocol"
	),

	HTTP_PROTOCOL(
			"PhysicalTwinLibrary::HTTP_Protocol"
	),

	MQTT_PROTOCOL(
			"PhysicalTwinLibrary::MQTT_Protocol"
	),


	// ========================================================================
	// Shadow
	// ========================================================================

	DATABASE("ShadowLibrary::Database"),

	RELATIONAL_DATABASE(
			"ShadowLibrary::RelationalDatabase"
	),

	KEY_VALUE_DATABASE(
			"ShadowLibrary::KeyValueDatabase"
	),


	// ========================================================================
	// Calculations
	// ========================================================================

	CUSTOM_CALCULATION(
			"TwinImp::CustomCalculationAction"
	),


	// ========================================================================
	// User Types
	// ========================================================================

	TWIN_CUSTOM_TYPE(
			"UserLibrary::TwinCustomType"
	),


	// ========================================================================
	// Enums
	// ========================================================================

	ORDER_BY("TwinEnumLibrary::ORDER_BY"),
	TIME_UNIT("TwinEnumLibrary::TimeUnit"),
	CUSTOM_STRATEGY_TYPE(
			"TwinEnumLibrary::CustomStrategyType"
	),


	// ========================================================================
	// Twin Type System
	// ========================================================================

	TWIN_TYPE_SYSTEM("TwinTypeSystem"),

	TWIN_ATTRIBUTE("Base::DataValue"),

	TWIN_REAL("ScalarValues::Real"),
	TWIN_INTEGER("ScalarValues::Integer"),
	TWIN_BOOLEAN("ScalarValues::Boolean"),
	TWIN_STRING("ScalarValues::String");


	private final String namespace;

	LibraryNameSpaces(String namespace) {
		this.namespace = namespace;
	}

	@Override
	public String toString() {
		return namespace;
	}
}