package org.example.Mapping.Model.Flow;




import Model.EmptyCore;import Model.Annotation.MappedLibrary;import Mapper.Mapper;
import Model.Slots;
import lombok.Getter;

import org.example.Mapping.Model.EnumAttribute.EnumFederationLinkUsage;
import org.omg.sysml.lang.sysml.FlowUsage;

import java.util.Optional;

@MappedLibrary(libraryName = "TwinActionLibrary::FederatedLinkFlow", core = EmptyCore.class)
public class FederationFlowUsage extends TwinFlowUsage {

	@Getter private Optional<EnumFederationLinkUsage> linkType = Optional.empty();

	public FederationFlowUsage(FlowUsage sysmlElement, Mapper newMappe) {
		super(sysmlElement, newMappe);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		linkType = Slots.atMostOne(this, "linkType", instance.mapSlot("linkType", this, EnumFederationLinkUsage.class));
	}
}
