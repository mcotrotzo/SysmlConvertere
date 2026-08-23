library package DescriptiveModelLibrary {
    private import ShadowLibrary::*;
    private import PhysicalTwinLibrary::*;
    private import TwinTypeSystem::*;
    private import TwinStateMachineLibrary::*; 
    private import TwinLibraryPackage::*;
    private import TwinImp::*;
    private import TwinEnumLibrary::*;
   
    private import UserLibrary::*;
    private import PhysicalTwinLibrary::*;
    private import TwinActionLibrary::*;
    

  

    state def DescriptiveStateMachine :> State {
        
        abstract state states:>>states:DescriptiveState[0..*];
    }
    state def DescriptiveState :> State {
        state states:DescriptiveState[0..*]:>>states;
    }
}
