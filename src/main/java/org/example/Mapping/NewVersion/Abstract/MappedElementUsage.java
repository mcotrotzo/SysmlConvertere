package org.example.Mapping.NewVersion.Abstract;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.Usage;
import org.omg.sysml.lang.sysml.Feature;

@ToString(callSuper = true)
public abstract class MappedElementUsage<T extends Feature> extends MappedElement<T> implements Usage {
	public MappedElementUsage(T sysmlElement) {
		super(sysmlElement);
	}
}
