package org.example.Mapping.Interfaces.Base;

import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.FullTwin.Twin;

import java.util.List;

public interface TwinDefLibrary extends Package {
	List<Twin<Definition>> getTwins();
}
