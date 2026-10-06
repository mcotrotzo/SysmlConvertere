package org.example.Util;

import lombok.Getter;
import org.eclipse.emf.ecore.resource.Resource;
import org.example.LoadedResources;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.util.SysMLLibraryUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class LibraryRawType extends RawType {

	Map<LibraryNameSpaces, Type> libraries = new HashMap<LibraryNameSpaces, Type>();

	LibraryRawType(LoadedResources loadedResources) {
		super(loadedResources);
		initLibraryElements();
	}


	@Override
	protected Resource selectResource(LoadedResources loadedResources) {
		return loadedResources.dtLibrary();
	}

	private void initLibraryElements() {
		for (LibraryNameSpaces libraryNameSpaces : LibraryNameSpaces.values()) {
			libraries.put(libraryNameSpaces, SysMLLibraryUtil.getLibraryType(rootElement, String.valueOf(libraryNameSpaces)));
		}
	}

	@Override
	public List<Type> getAllElements() {
		return new ArrayList<>(libraries.values());
	}
}
