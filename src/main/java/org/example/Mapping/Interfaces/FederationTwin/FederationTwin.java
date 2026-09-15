package org.example.Mapping.Interfaces.FederationTwin;

import org.example.Mapping.Interfaces.Base.CompartmentContainer;
import org.example.Mapping.Interfaces.Base.FederationDefLibrary;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.Taxonomy;
import org.example.Mapping.Interfaces.TwinFlow.FederationFlow;
import org.example.Mapping.Interfaces.TwinFlow.QueryFlow;

public interface FederationTwin <T extends TypeKind> extends Taxonomy<T>
{
	CompartmentContainer<? extends FederationFlow<Usage>> getFederationFlows();

}
