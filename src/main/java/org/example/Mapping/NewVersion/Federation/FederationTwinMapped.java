package org.example.Mapping.NewVersion.Federation;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.FederationTwin.FederationTwin;
import org.example.Mapping.Interfaces.TwinFlow.FederationFlow;
import org.example.Mapping.NewVersion.Abstract.CompartmentContainerMapped;
import org.example.Mapping.NewVersion.Abstract.CompartmentMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.NewVersion.TaxonomyMapped.PhysicalTwinMapped;
import org.example.Mapping.NewVersion.TaxonomyMapped.Taxonomy.TaxonomyMapped;
import org.example.Mapping.NewVersion.TwinFlow.Definition.FederationFlowMapped;
import org.example.Mapping.NewVersion.TwinFlow.Definition.QueryFlowMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.Optional;

@MappedElementType(LibraryNameSpaces.FEDERATION_TWIN)
@ToString(callSuper = true)
public class FederationTwinMapped<Z extends TypeKind>  extends TaxonomyMapped<Z> implements FederationTwin<Z> {
	private CompartmentContainerMapped<FederationFlowMapped<Usage>> federationFlows;

	public FederationTwinMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		federationFlows = context.mapSlot(this, "federatedLinks", rawClassOf(FederationFlowMapped.class));

	}


	@Override
	public CompartmentContainer<? extends FederationFlow<Usage>> getFederationFlows() {
		return federationFlows;
	}
}
