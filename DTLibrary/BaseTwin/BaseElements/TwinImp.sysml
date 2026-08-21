library package TwinImp{

    private import TwinTypeSystem::*;
    private import TwinLibraryPackage::*;
    private import TwinActionLibrary::*;

    
    calc def CustomCalculationAction :> TwinAction {
        in attribute inputs[0..*] : TwinAttribute :>>inputs;
        return out attribute outputs[0..*]:TwinAttribute :>>outputs;        
    }

}