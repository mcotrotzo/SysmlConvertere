package org.example.Mapping.NewVersion.FullTwinMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.FullTwin.TwinDef;
import org.example.Mapping.NewVersion.FullTwinMapped.TwinMapped;
import org.omg.sysml.lang.sysml.Classifier;
@ToString(callSuper = true)
public class TwinDefinitionMapped extends TwinMapped<Classifier> implements TwinDef {
	public TwinDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
