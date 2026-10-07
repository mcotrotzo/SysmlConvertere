library package DescriptiveModelLibrary {
    private import ShadowLibrary::*;
    private import PhysicalTwinLibrary::*;
    private import TwinTypeSystem::*;
    private import TwinStateMachineLibrary::*; 
    private import TwinImp::*;
    private import TwinEnumLibrary::*;
   
    private import TwinActionLibrary::*;
    private import PhysicalTwinLibrary::*;
    private import TwinActionLibrary::*;
    

  

    state def DescriptiveStateMachine :> TwinStateMachine {
        
        abstract state states:>>states:DescriptiveState[0..*];
    }
    state def DescriptiveState :> State {
        state states:DescriptiveState[0..*]:>>states;
    }
}
