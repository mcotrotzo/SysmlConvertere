library package DescriptiveModelLibrary {
    private import ShadowLibrary::*;
    private import PhysicalTwinLibrary::*;
    private import TwinTypeSystem::*;
    private import TwinStateMachineLibrary::*; 
    private import TwinLibraryPackage::*;
    private import TwinImp::*;
    private import TwinEnumLibrary::*;
    private import TwinTriggerConfigurationLibrary::*;
    private import PhysicalTwinLibrary::*;
    
    abstract calc def QueryHistory {

        in attribute twinAttribute[1] : TwinAttribute;
        in attribute since[0..1] : TwinInteger;
        in attribute sinceUnit[0..1] : TimeUnit;
        in attribute orderBy[0..1] : ORDER_BY;
        in attribute limit[0..1]:TwinInteger;
        in attribute filterExpression[0..1] : TwinBoolean;
        return out result[0..*] : TwinAttribute;


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
