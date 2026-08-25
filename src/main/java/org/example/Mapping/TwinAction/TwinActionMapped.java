package org.example.Mapping.TwinAction;

import lombok.ToString;

import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;


@MappedMetaclass
public abstract class TwinActionMapped<T extends Type,Z extends TypeKind> extends MappedElement<T,Z> implements Action<Z> {
	public TwinActionMapped(T sysmlElement) {
		super(sysmlElement);
	}


	@SuppressWarnings("unchecked")
	public static Class<TwinActionMapped<?, Definition>> getActionMappedDefinitionClass() {
		return (Class<TwinActionMapped<?, Definition>>) (Class<?>) TwinActionMapped.class;
	}

	@SuppressWarnings("unchecked")
	public static Class<TwinActionMapped<?, Usage>> getActionMappedUsageClass() {
		return (Class<TwinActionMapped<?, Usage>>) (Class<?>) TwinActionMapped.class;
	}

}
