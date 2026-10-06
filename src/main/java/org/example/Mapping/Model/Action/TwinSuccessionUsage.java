package org.example.Mapping.Model.Action;

import lombok.Getter;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Type.EmptyCore;
import org.example.Mapping.Model.Type.Definition;
import org.example.Mapping.Model.Type.Usage;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.SuccessionAsUsage;

import java.util.ArrayList;
import java.util.List;

public class TwinSuccessionUsage extends Usage<EmptyCore, SuccessionAsUsage, Definition> {

	@Getter private List<TwinActionUsage<?, ?, ?>> targets = new ArrayList<>();
	public TwinSuccessionUsage(SuccessionAsUsage sysmlElement, Mapper mapper) {
		super(sysmlElement, () -> new EmptyCore(sysmlElement, mapper), mapper, Definition.class);
	}


	@Override
	public void fillSlots() {
		super.fillSlots();
		targets.add(instance.mapChain(List.of(sysmlElement.getSourceFeature()), this, TwinActionUsage.class));
		for (Feature target : sysmlElement.getTargetFeature()) {
			targets.add(instance.mapChain(List.of(target), this, TwinActionUsage.class));
		}
	}
}
