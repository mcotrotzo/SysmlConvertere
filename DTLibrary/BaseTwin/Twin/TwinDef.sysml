library package TwinDefLibrary {
    
    private import PublicDescriptiveElements::*;
    private import PrescriptivePublicElements::*;
    private import PredictivePublicElements::*;
    private import ShadowPublicElements::*;
    private import PhysicalElementsLibrary::*;
    private import TwinTypeSystem::*;
    private import TwinLibraryPackage::*;
    private import TwinActionLibrary::*;
    private import TwinStrategyLibrary::*;
    private import TwinTaxonomyLibrary::*;


    part def PhysicalTwin:>PhysicalTaxonomy{
        out port sensors [0..*] : Sensor;
        state controlUnit:ControlUnit[0..*];
        in port actuators [0..*] : Actuator;
        out port constPort: ConstPort[0..1];
        abstract flow physicalFlows:PhysicalFlow[0..*];
    }

    part def DescriptiveModel:>DescriptiveTaxonomy{
        action derivedAttributes[0..*] : TwinAction;
        state descriptiveStateMachine_ : DescriptiveStateMachine[0..*];
        action descriptiveStrategies[0..*] : Strategy;
        abstract flow descriptiveFlows:DescriptiveFlow[0..*];
    }

    part def Shadow:>ShadowTaxonomy{
        part databases : Database[0..*];
    }

    part def PrescriptiveModel:>PrescriptiveTaxonomy{
        action prescriptiveStrategies : Strategy[0..*];
        abstract flow prescriptiveFlows:PrescriptiveFlow[0..*];
    }
    part def PredictiveModel:>PredictiveTaxonomy{
        action predictiveStrategies : Strategy[0..*];
        abstract flow predictiveFlows:PredictiveFlow[0..*];
    }


    part def Twin:>TwinLibraryElement{
        part physicalTwin : PhysicalTwin[0..1];
        part shadow : Shadow[0..1];
        part descriptiveModel : DescriptiveModel[0..1];
        part prescriptiveModel : PrescriptiveModel[0..1];
        part predictiveModel : PredictiveModel[0..1];
        abstract flow queryFlows:QueryFlow[0..*];
        abstract flow descriptiveToPredictiveFlows:DescriptiveToPredictiveFlow[0..*];
        abstract flow descriptiveToPrescriptiveFlows:DescriptiveToPrescriptiveFlow[0..*];
        abstract flow predictiveToPrescriptiveFlows:PredictiveToPrescriptiveFlow[0..*];
        abstract flow prescriptiveToPhysicalFlows:PrescriptiveToPhysicalFlow[0..*];

       
    }
    
}