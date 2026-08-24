package org.example.Mapping.NewVersion.TaxonomyMapped.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.PhysicalTwinUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.TaxonomyMapped.PhysicalTwinMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Usage;
@MappedElementType(LibraryNameSpaces.PHYSICAL_TWIN)
@ToString(callSuper = true)
public class PhysicalTwinUsageMapped extends PhysicalTwinMapped<Feature> implements PhysicalTwinUsage {
	public PhysicalTwinUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
