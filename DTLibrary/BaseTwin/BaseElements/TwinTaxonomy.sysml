library package TwinTaxonomyLibrary {
    private import TwinLibraryPackage::*;    
   
    part def TwinTaxonomy:>TwinLibraryElement;

    
    part def PhysicalTaxonomy:>TwinTaxonomy;
    part def CloudTwinTaxonomy:>TwinTaxonomy;
    
    part def DescriptiveTaxonomy:>CloudTwinTaxonomy;
    part def PredictiveTaxonomy:>CloudTwinTaxonomy;
    part def PrescriptiveTaxonomy:>CloudTwinTaxonomy;
    part def ShadowTaxonomy:>CloudTwinTaxonomy;
}