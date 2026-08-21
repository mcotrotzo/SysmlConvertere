package org.example.Mapping.NewVersion.Abstract;

import lombok.ToString;
import org.example.Mapping.Interfaces.KIND;
import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.NewVersion.NameSpace.NameSpacePackage.MappedNamespaceElement;
import org.omg.sysml.lang.sysml.*;

@ToString(callSuper = true)
public abstract class MappedElement<T extends Type> extends MappedNamespaceElement<T> implements Model {


	public MappedElement(T sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public KIND getKind() {
		return sysmlElement instanceof Definition ? KIND.DEFINITION : KIND.USAGE;
	}
}
