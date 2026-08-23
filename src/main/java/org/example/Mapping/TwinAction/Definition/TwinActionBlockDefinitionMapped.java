package org.example.Mapping.TwinAction.Definition;


import org.example.Mapping.Interfaces.TwinAction.Definition.BlockDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinAction.TwinActionBlockMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

@MappedElementType(LibraryNameSpaces.TWIN_ACTION)
@MappedMetaclass
public abstract class TwinActionBlockDefinitionMapped<T extends Classifier> extends TwinActionBlockMapped<T> implements BlockDefinition {
	public TwinActionBlockDefinitionMapped(T sysmlElement) {
		super(sysmlElement);
	}
}
