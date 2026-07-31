package org.example.Util;

public enum LibraryNameSpaces {

    TWIN("TwinDefLibrary::Twin"),
    CLOUD_TWIN("TwinDefLibrary::CloudTwin"),

    ACTUATOR("PhysicalTwinLibrary::Actuator"),
    SENSOR("PhysicalTwinLibrary::Sensor"),
    TWIN_PORT("PhysicalTwinLibrary::TwinPort"),
    CONTROL_UNIT("PhysicalTwinLibrary::ControlUnit"),
    CONTROL_UNIT_STATE("PhysicalTwinLibrary::ControlUnitState"),
    COMMUNICATION_PROTOCOL("PhysicalTwinLibrary::CommunicationProtocol"),
    HTTP_PROTOCOL("PhysicalTwinLibrary::HTTP_Protocol"),
    MQTT_PROTOCOL("PhysicalTwinLibrary::MQTT_Protocol"),

    STATE("TwinStateMachineLibrary::State"),

    TRIGGER_CONFIGURATION("TwinTriggerConfigurationLibrary::TriggerConfiguration"),
    TIME_BASED_CONFIGURATION("TwinTriggerConfigurationLibrary::TimeBasedConfiguration"),
    EVENT_BASED_CONFIGURATION("TwinTriggerConfigurationLibrary::EventBasedConfiguration"),

    CUSTOM_CALCULATION("TwinImp::CustomCalculationAction"),

    QUERY_HISTORY("DescriptiveModelLibrary::QueryHistory"),
    DESCRIPTIVE_STATE_MACHINE("DescriptiveModelLibrary::DescriptiveStateMachine"),
    DESCRIPTIVE_STATE("DescriptiveModelLibrary::DescriptiveState"),
    DESCRIPTIVE_STRATEGY("DescriptiveModelLibrary::DescriptiveStrategy"),

    PREDICTIVE_STRATEGY("PredicitiveLibrary::PredictiveStrategy"),
    PREDICTIVE_MODEL("PredicitiveLibrary::PredictiveModel"),

    PRESCRIPTIVE_STRATEGY("PrescriptiveModelLibrary::PrescriptiveStrategy"),
    CUSTOM_PRESCRIPTIVE_STRATEGY("PrescriptiveModelLibrary::CustomPrescriptiveStrategy"),

    DATABASE("ShadowLibrary::Database"),
    SHADOW("ShadowLibrary::Shadow"),
    RELATIONAL_DATABASE("ShadowLibrary::RelationalDatabase"),
    KEY_VALUE_DATABASE("ShadowLibrary::KeyValueDatabase"),

    TWIN_CUSTOM_TYPE("UserLibrary::TwinCustomType"),

    TWIN_ATTRIBUTE("Base::DataValue"),
    TWIN_REAL("ScalarValues::Real"),
    TWIN_INTEGER("ScalarValues::Integer"),
    TWIN_BOOLEAN("ScalarValues::Boolean"),
    TWIN_STRING("ScalarValues::String");

    private final String namespace;

    LibraryNameSpaces(String namespace) {
        this.namespace = namespace;
    }
}