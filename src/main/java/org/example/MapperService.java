package org.example;

import org.example.GenerelRules.*;
import org.example.Mapping.Model.Mapper;
import org.example.SemanticRules.*;
import org.example.Util.NewUtil;

import java.util.List;

public class MapperService {

	private final NewUtil utilsManager;
	private final Mapper mapper;

	public MapperService(String userContent) {
		ReadManager readManager = new ReadManager(userContent);
		utilsManager = new NewUtil(readManager.getLoadedResources());
		mapper = new Mapper(utilsManager);


	}
	public TwinDataBase map() throws IllegalArgumentException {
		try {
			preRules();
			mapper.parse();
			TwinDataBase db = new TwinDataBase(mapper.getMapperMap().values());
			postRules(db);
			return db;

		} catch (SemanticException e) {
			throw new IllegalArgumentException("Semantic exception: " + e.getClass().getName() + ": " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();

			throw new IllegalArgumentException("Unexpected exception: " + e.getClass().getName() + ": " + e.getMessage());
		}
	}

	private void preRules() throws IllegalArgumentException {
		var genereRules = List.of(new MultiType(utilsManager),
				new TwinAttributeHasToSpecialiced(utilsManager),
				new MultiplicityRule(utilsManager),
				new CalcInputOutputRules(utilsManager)
				);
		for (GenerelRules rule : genereRules) {
			rule.isValid();
		}
	}

	private void postRules(TwinDataBase database) throws SemanticException {
		var semanticRules = List.of(new CheckAssignemntRules(),new FlowRules(),new TwinBoundaryRules());
		for (SemanticRule rule : semanticRules) {
			rule.isValid(database);
		}
	}


}
