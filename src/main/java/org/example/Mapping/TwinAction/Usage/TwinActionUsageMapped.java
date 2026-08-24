package org.example.Mapping.TwinAction.Usage;

import org.example.Mapping.Interfaces.TwinAction.Usage.ActionUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementUsage;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.Feature;

@MappedMetaclass
public abstract class TwinActionUsageMapped <T extends Feature> extends MappedElementUsage<T> implements ActionUsage {
	public TwinActionUsageMapped(T sysmlElement) {
		super(sysmlElement);
	}

	public static Class<TwinActionUsageMapped<?>> getRawClass() {
		return (Class<TwinActionUsageMapped<?>>) (Class<?>) TwinActionUsageMapped.class;

	}
}
