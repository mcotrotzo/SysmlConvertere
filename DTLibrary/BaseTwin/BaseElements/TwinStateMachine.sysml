library package TwinStateMachineLibrary {
    private import TwinTypeSystem::*;
    private import TwinActionLibrary::*;
    state def State:> TwinAction{
        state states:State[0..*];
    }

}


