package org.example.Mapping.NewVersion.FullTwinMapped.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.FullTwin.TwinDef;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.FullTwinMapped.TwinMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
@MappedElementType(value = LibraryNameSpaces.TWIN)
@ToString(callSuper = true)
public class TwinDefinitionMapped extends TwinMapped<Classifier> implements TwinDef {
	public TwinDefinitionMapped(Classifier sysmlElement) {
		super(sysmlElement);
	}
}
