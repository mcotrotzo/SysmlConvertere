library package DescriptiveModelLibrary {
    private import ShadowLibrary::*;
    private import PhysicalTwinLibrary::*;
    private import TwinTypeSystem::*;
    private import TwinStateMachineLibrary::*; 
    private import TwinLibraryPackage::*;
    private import TwinImp::*;
    private import TwinEnumLibrary::*;
    private import TwinTriggerConfigurationLibrary::*;
    private import UserLibrary::*;
    private import PhysicalTwinLibrary::*;

    abstract part def QueryHistory {

        attribute twinAttribute[1] : TwinAttribute;
        attribute since[0..1] : TwinInteger;
        attribute sinceUnit[0..1] : TimeUnit;
        attribute orderBy[0..1] : ORDER_BY;
        attribute limit[0..1]:TwinInteger;
        attribute filterExpression[0..1] : TwinBoolean;
        attribute result[0..*]:TwinAttribute;
    }

    abstract attribute def QueryResult :>TwinCustomType {
        attribute result[0..*]:>>fields:TwinAttribute;
    }

    abstract part def GroupedHistoryQuery{

        attribute twinAttribute[1] : TwinAttribute;
        attribute groupBy[1] : TwinString;
        attribute since[0..1] : TwinInteger;
        attribute sinceUnit[0..1] : TimeUnit;
        attribute orderBy[0..1] : ORDER_BY;
        attribute limit[0..1]:TwinInteger;
        attribute filterExpression[0..1] : TwinBoolean;
        attribute result[0..*]:QueryResult;
    }



    state def DescriptiveStateMachine :> State {
        
        part triggerConfiguration : TriggerConfiguration[1];
        abstract state states:>>states:DescriptiveState[0..*];
    }

    state def DescriptiveState :> State {
        state states:DescriptiveState[0..*]:>>states;
    }

    part def DescriptiveStrategy {
        part triggerConfiguration : TriggerConfiguration[1];
        in attribute lambdaPath : TwinString;
        attribute inputs[0..*] : TwinAttribute;
        attribute outputs[0..*] : TwinAttribute;
    }

}
