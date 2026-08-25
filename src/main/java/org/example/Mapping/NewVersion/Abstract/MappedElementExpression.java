package org.example.Mapping.NewVersion.Abstract;

import lombok.ToString;
import org.example.Mapping.NewVersion.NameSpace.NameSpacePackage.MappedNamespaceElement;
import org.omg.sysml.lang.sysml.Expression;

@ToString(callSuper = true)
public abstract class MappedElementExpression<T extends Expression> extends MappedNamespaceElement<T, org.example.Mapping.Interfaces.Base.TypeKind.Expression> {
	public MappedElementExpression(T sysmlElement) {
		super(sysmlElement);
	}
}
