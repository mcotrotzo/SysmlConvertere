package org.example;

import SemanticRules.OnlyQueryResultCanBeReferenced;
import SemanticRules.SemanticException;
import SemanticRules.SemanticRule;
import org.example.Containers.ContainerManager;
import org.example.GenerelRules.GenerelRules;
import org.example.GenerelRules.MultiType;
import org.example.GenerelRules.MultiplicityRule;
import org.example.GenerelRules.TwinAttributeHasToSpecialiced;
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
			TwinDataBase db = new TwinDataBase(s);
			postRules(db);
			return db;

		}
		catch (SemanticException e) {
			throw new MappingException("Semantic exception: " + e.getClass().getName() + ": " + e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();

			throw new MappingException("Unexpected exception: " + e.getClass().getName() + ": " + e.getMessage());
		}
	}

	private void preRules() throws MappingException {
		var genereRules = List.of(new MultiType(utilsManager),new TwinAttributeHasToSpecialiced(utilsManager),new MultiplicityRule(utilsManager));
		for (GenerelRules rule : genereRules) {
			rule.isValid();
		}
	}

	private void postRules(TwinDataBase database) throws SemanticException {
		var semanticRules = List.of(new SemanticRules.CheckAssignemntRules(),new OnlyQueryResultCanBeReferenced());
		for (SemanticRules.SemanticRule rule : semanticRules) {
			rule.isValid(database);
		}
	}


}
