package org.example.Mapping.TwinAction.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinAction.Block;
import org.example.Mapping.Interfaces.TwinAction.Succession;
import org.example.Mapping.Interfaces.TwinAction.Usage.BlockUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.example.Mapping.TwinAction.TwinActionBlockMapped;
import org.example.Mapping.TwinAction.TwinSuccessionAction;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.SuccessionAsUsage;

import java.util.ArrayList;
import java.util.List;


@ToString(callSuper = true)
public class TwinActionBlockUsage<T extends Feature> extends TwinActionBlockMapped<T> implements BlockUsage {
	public TwinActionBlockUsage(T sysmlElement) {
		super(sysmlElement);
	}
}