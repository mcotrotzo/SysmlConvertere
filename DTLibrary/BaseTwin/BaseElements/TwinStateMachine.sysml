library package TwinStateMachineLibrary {
    private import TwinTypeSystem::*;
    private import TwinLibraryPackage::*;
    private import TwinActionLibrary::*;
    state def State:> TwinAction{
        state states:State[0..*]:>>execution;
    }

}


