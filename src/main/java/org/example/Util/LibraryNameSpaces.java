package org.example.Util;

public enum LibraryNameSpaces {
	TWIN("TwinDefLibrary::Twin"),
	PHYSICAL_TWIN("TwinDefLibrary::PhysicalTwin"),DESCRIPTIVE_MODEL("TwinDefLibrary::DescriptiveModel"), PREDICTIVE_MODEL("TwinDefLibrary::PredictiveModel"), PRESCRIPTIVE_MODEL("TwinDefLibrary::PrescriptiveModel"), SHADOW("TwinDefLibrary::Shadow"),

	ACTUATOR("PhysicalTwinLibrary::Actuator"), SENSOR("PhysicalTwinLibrary::Sensor"), TWIN_PORT("PhysicalTwinLibrary::TwinPort"), CONTROL_UNIT("PhysicalTwinLibrary::ControlUnit"), CONTROL_UNIT_STATE("PhysicalTwinLibrary::ControlUnitState"), COMMUNICATION_PROTOCOL("PhysicalTwinLibrary::CommunicationProtocol"), HTTP_PROTOCOL("PhysicalTwinLibrary::HTTP_Protocol"), MQTT_PROTOCOL("PhysicalTwinLibrary::MQTT_Protocol"), TWIN_TYPE_SYSTEM("TwinTypeSystem"),

	STATE("TwinStateMachineLibrary::State"),

	TRIGGER_CONFIGURATION("TwinTriggerConfigurationLibrary::TriggerConfiguration"), TIME_BASED_CONFIGURATION("TwinTriggerConfigurationLibrary::TimeBasedConfiguration"), EVENT_BASED_CONFIGURATION("TwinTriggerConfigurationLibrary::EventBasedConfiguration"),

	CUSTOM_CALCULATION("TwinImp::CustomCalculationAction"),

	QUERY_HISTORY("DescriptiveModelLibrary::QueryHistory"), GROUPED_HISTORY_QUERY("DescriptiveModelLibrary::GroupedHistoryQuery"), QUERY_RESULT("DescriptiveModelLibrary::QueryResult"), DESCRIPTIVE_STATE_MACHINE("DescriptiveModelLibrary::DescriptiveStateMachine"), DESCRIPTIVE_STATE("DescriptiveModelLibrary::DescriptiveState"), DESCRIPTIVE_STRATEGY("DescriptiveModelLibrary::DescriptiveStrategy"),

	PREDICTIVE_STRATEGY("PredicitiveLibrary::PredictiveStrategy"),

	PRESCRIPTIVE_STRATEGY("PrescriptiveModelLibrary::PrescriptiveStrategy"), CUSTOM_PRESCRIPTIVE_STRATEGY("PrescriptiveModelLibrary::CustomPrescriptiveStrategy"),

	DATABASE("ShadowLibrary::Database"), RELATIONAL_DATABASE("ShadowLibrary::RelationalDatabase"), KEY_VALUE_DATABASE("ShadowLibrary::KeyValueDatabase"),

	TWIN_CUSTOM_TYPE("UserLibrary::TwinCustomType"),

	ORDER_BY("TwinEnumLibrary::ORDER_BY"),
	TIME_UNIT("TwinEnumLibrary::TimeUnit"),

	TWIN_ATTRIBUTE("Base::DataValue"), TWIN_REAL("ScalarValues::Real"), TWIN_INTEGER("ScalarValues::Integer"), TWIN_BOOLEAN("ScalarValues::Boolean"), TWIN_STRING("ScalarValues::String");


	private final String namespace;

	LibraryNameSpaces(String namespace) {
		this.namespace = namespace;
	}

	@Override
	public String toString() {
		return namespace;
	}
}