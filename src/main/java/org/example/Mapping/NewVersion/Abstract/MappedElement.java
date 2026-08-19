package org.example.Mapping.NewVersion.Abstract;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.example.ElemWithMult;
import org.example.Mapping.Interfaces.KIND;
import org.example.Mapping.Interfaces.Model;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.Packages.MappedNamespaceElement;
import org.omg.sysml.lang.sysml.*;
import org.omg.sysml.util.FeatureUtil;
import org.omg.sysml.util.TypeUtil;

import java.nio.charset.StandardCharsets;
import java.util.*;

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
