package org.example.Mapping.NewVersion.Abstract;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.KIND;
import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.NewVersion.NameSpace.NameSpacePackage.MappedNamespaceElement;
import org.omg.sysml.lang.sysml.*;

import java.util.List;
import java.util.Optional;

@ToString(callSuper = true)
public abstract class MappedElement<T extends Type,Z extends TypeKind> extends MappedNamespaceElement<T,Z> implements org.example.Mapping.Interfaces.Base.Type<Z> {


	public MappedElement(T sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public Optional<? extends Reference<? extends org.example.Mapping.Interfaces.Base.Type<Definition>>> getDefinitionOfUsage() {
		return Optional.empty();
	}

	@Override
	public List<? extends Reference<? extends org.example.Mapping.Interfaces.Base.Type<Definition>>> getSuperTypeOfDefinitions() {
		return List.of();
	}
}
