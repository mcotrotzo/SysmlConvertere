library package TwinTaxonomyLibrary {
    private import TwinLibraryPackage::*;    
   
    part def TwinTaxonomy:>TwinLibraryElement;

    
    part def PhysicalTaxonomy:>TwinTaxonomy;
    part def CloudTwinTaxonomy:>TwinTaxonomy;
    
    part def DescriptiveTaxonomy:>TwinTaxonomy;
    part def PredictiveTaxonomy:>TwinTaxonomy;
    part def PrescriptiveTaxonomy:>TwinTaxonomy;
    part def ShadowTaxonomy:>TwinTaxonomy;
}