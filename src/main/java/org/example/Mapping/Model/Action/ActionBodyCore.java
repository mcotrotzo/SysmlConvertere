package org.example.Mapping.Model.Action;

import org.example.Mapping.Model.AbstractModel;
import org.omg.sysml.lang.sysml.SuccessionAsUsage;
import org.omg.sysml.lang.sysml.ActionUsage;
import lombok.Getter;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.Core;
import org.example.Mapping.Model.Slots;

import java.util.List;
import org.omg.sysml.lang.sysml.Type;

public class ActionBodyCore extends Core<Type> {
	@Getter private List<TwinActionUsage<?, ?, ?>> actions = List.of();
	@Getter private List<TwinSuccessionUsage> successions = List.of();

	public ActionBodyCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel<?> owner) {
		actions = mapper.mapOwnedElement(ActionUsage.class, owner, Slots.<TwinActionUsage<?, ?, ?>>rawClassOf(TwinActionUsage.class));
		successions = mapper.mapOwnedElement(SuccessionAsUsage.class, owner, TwinSuccessionUsage.class);
	}
}
