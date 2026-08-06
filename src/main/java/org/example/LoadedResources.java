package org.example;

import org.eclipse.emf.ecore.resource.Resource;
import org.omg.sysml.lang.sysml.Element;

import java.util.Set;

public record LoadedResources(Set<Resource> standardLibrary, Resource dtLibrary, Resource userLibrary, Resource model,
							  Element rootElement) {
	public LoadedResources {
		standardLibrary = Set.copyOf(standardLibrary);
	}
}