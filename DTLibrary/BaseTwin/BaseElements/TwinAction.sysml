library package TwinActionLibrary{

    private import TwinTypeSystem::*;
    private import TwinTaxonomyLibrary::*;
    private import TwinEnumLibrary::*;

    action def TwinAction{
        in attribute inputs[0..*] : TwinAttribute;
        out attribute outputs[0..*]:TwinAttribute;
        attribute local_Attributes[0..*] : TwinAttribute;
    }

    private abstract flow def TwinFlow {
        part tax1:TwinTaxonomy;
        part tax2:TwinTaxonomy;
    }

    abstract flow def PhysicalFlow:>TwinFlow {
        private part :>>tax1:PhysicalTaxonomy;
        private part :>>tax2:PhysicalTaxonomy;
    }

    abstract flow def DescriptiveFlow:>TwinFlow {
        private part :>>tax1:DescriptiveTaxonomy;
        private part :>>tax2:DescriptiveTaxonomy;
    }

    abstract flow def PredictiveFlow:>TwinFlow {
        private part :>>tax1:PredictiveTaxonomy;
        private part :>>tax2:PredictiveTaxonomy;
    }

    abstract flow def PrescriptiveFlow:>TwinFlow {
        private part :>>tax1:PrescriptiveTaxonomy;
        private part :>>tax2:PrescriptiveTaxonomy;
    }
      abstract flow def QueryFlow :> TwinFlow {
        private part :>>tax1:PhysicalTaxonomy;
        private part :>>tax2:CloudTwinTaxonomy;

        attribute since[0..1] : TwinInteger;
        attribute sinceUnit[0..1] : TimeUnit;
        attribute orderBy[0..1] : ORDER_BY;
        attribute limit[0..1] : TwinInteger;
    }

   
    abstract flow def DescriptiveToPredictiveFlow:>TwinFlow {
        private part :>>tax1:DescriptiveTaxonomy;
        private part :>>tax2:PredictiveTaxonomy;
    }

    abstract flow def DescriptiveToPrescriptiveFlow:>TwinFlow {
        private part :>>tax1:DescriptiveTaxonomy;
        private part :>>tax2:PrescriptiveTaxonomy;
    }

    abstract flow def PredictiveToPrescriptiveFlow:>TwinFlow {
        private part :>>tax1:PredictiveTaxonomy;
        private part :>>tax2:PrescriptiveTaxonomy;
    }

    abstract flow def PrescriptiveToPhysicalFlow:>TwinFlow {
        private part :>>tax1:PrescriptiveTaxonomy;
        private part :>>tax2:PhysicalTaxonomy;
    }



}