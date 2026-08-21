package org.example.Mapping.NewVersion.TaxonomyMapped.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.BaseTaxonomy.DescriptiveModelUsage;
import org.example.Mapping.NewVersion.TaxonomyMapped.DescriptiveModelMapped;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Usage;
@ToString(callSuper = true)
public class DescriptiveModelUsageMapped extends DescriptiveModelMapped<Feature> implements DescriptiveModelUsage {
	public DescriptiveModelUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}
}
