package org.example;

import org.example.Containers.ContainerManager;
import org.example.GenerelRules.GenerelRules;
import org.example.GenerelRules.MultiplicityRule;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Util.Utils;

import java.util.List;

public class MapperService {

	private final Utils utilsManager;
	private final ContainerManager containerManager;
	private final MappingContext mappingContext;

	public MapperService(String userTwinModelPath, String userLibraryPath) {
		ReadManager readManager = new ReadManager(userTwinModelPath, userLibraryPath);
		Utils utils = new Utils(readManager.getLoadedResources());
		this.utilsManager = utils;
		this.containerManager = new ContainerManager(utilsManager);
		this.mappingContext = new MappingContext(utils, containerManager);
	}

	public TwinDataBase map() throws MappingException {
		try {
			preRules();
			var s = mappingContext.parseAll();
			return new TwinDataBase(s);
		} catch (MappingException e) {
			System.err.println("MappingException: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			System.err.println("Unexpected exception: " + e.getMessage());
		}
		return null;
	}

	public void preRules() throws MappingException {
		var genereRules = List.of(new MultiplicityRule(utilsManager));
		boolean isValid = true;
		for (GenerelRules rule : genereRules) {
			System.out.println("Checking rule: " + rule.getClass().getSimpleName());
			boolean ruleValid = rule.isValid();
			System.out.println("Is valid: " + ruleValid);

			isValid = isValid && ruleValid;
		}
	}


}
