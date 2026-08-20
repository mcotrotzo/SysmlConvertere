library package TwinDefLibrary {
    
    private import PublicDescriptiveElements::*;
    private import PrescriptivePublicElements::*;
    private import PredictivePublicElements::*;
    private import ShadowPublicElements::*;
    private import PhysicalElementsLibrary::*;
    private import TwinTypeSystem::*;
    private import TwinLibraryPackage::*;

    part def PhysicalTwin{
        out port sensors [0..*] : Sensor;
        state controlUnit:ControlUnit[0..*];
        in port actuators [0..*] : Actuator;
        attribute constAttributes [0..*]: TwinAttribute;
    }

    part def DescriptiveModel:>TwinLibraryElement{
        attribute derivedAttributes[0..*] : TwinAttribute;
        state descriptiveStateMachine_ : DescriptiveStateMachine[0..*];
        part descriptiveStrategies[0..*] : DescriptiveStrategy;
    }

    part def Shadow{
        part databases : Database[0..*];
    }

    part def PrescriptiveModel:>TwinLibraryElement{
        part prescriptiveStrategies : CustomPrescriptiveStrategy[0..*];
    }
    part def PredictiveModel:>TwinLibraryElement{
        part predictiveStrategies : PredictiveStrategy[0..*];
    }


    part def Twin{
        part physicalTwin : PhysicalTwin[0..1];
        part shadow : Shadow[0..1];
        part descriptiveModel : DescriptiveModel[0..1];
        part prescriptiveModel : PrescriptiveModel[0..1];
        part predictiveModel : PredictiveModel[0..1];
    }
}