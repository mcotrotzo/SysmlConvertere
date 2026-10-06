package org.example.Util;

import lombok.Getter;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.example.LoadedResources;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

public abstract class RawType {

	protected Resource resource;

	@Getter
	protected Element rootElement;

	private List<Type> cachedElements = new ArrayList<>();

	RawType(LoadedResources loadedResources) {
		resource = selectResource(loadedResources);
		rootElement = Utils.getRootElementFromResource(resource);
	}

	protected abstract Resource selectResource(LoadedResources loadedResources);


	public String ResourceId() {
		return resource.getURI().toString();
	}


	public List<Type> getAllElements() {
		for (TreeIterator<EObject> it = resource.getAllContents(); it.hasNext(); ) {
			EObject content = it.next();
			if (content instanceof Type element) {
				cachedElements.add(element);
			}
		}

		return cachedElements;
	}

}
