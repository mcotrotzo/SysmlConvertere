package org.example.Mapping.Interfaces.Base;

import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.FederationTwin.FederationTwin;

import java.util.List;

public interface FederationDefLibrary extends Package{

	List<FederationTwin<Definition>> getFederationTwins();
}
