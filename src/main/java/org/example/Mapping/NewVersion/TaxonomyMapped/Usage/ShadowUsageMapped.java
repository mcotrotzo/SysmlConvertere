package org.example.Mapping.NewVersion.TaxonomyMapped.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.ShadowUsage;
import org.example.Mapping.NewVersion.TaxonomyMapped.ShadowMapped;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Usage;
@ToString(callSuper = true)
public class ShadowUsageMapped extends ShadowMapped<Feature> implements ShadowUsage {
	public ShadowUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
