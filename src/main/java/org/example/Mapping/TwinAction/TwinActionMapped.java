package org.example.Mapping.TwinAction;

import lombok.ToString;

import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;


@MappedMetaclass
public abstract class TwinActionMapped<T extends Type> extends MappedElement<T> implements Action {
	public TwinActionMapped(T sysmlElement) {
		super(sysmlElement);
	}

}
