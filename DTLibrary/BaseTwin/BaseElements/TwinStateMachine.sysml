library package TwinStateMachineLibrary {
    private import TwinTypeSystem::*;
    private import TwinLibraryPackage::*;
    
    state def State:>TwinLibraryElementStateDef {
        attribute local_Attributes :TwinAttribute[0..*];
        state states:State[0..*];
    }

}


