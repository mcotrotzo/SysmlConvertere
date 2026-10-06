package org.example.Util;

import org.eclipse.emf.ecore.resource.Resource;
import org.example.LoadedResources;

public class UserRawType extends RawType{


	UserRawType(LoadedResources loadedResources) {
		super(loadedResources);
	}

	@Override
	protected Resource selectResource(LoadedResources loadedResources) {
		return loadedResources.userContent();
	}
}
