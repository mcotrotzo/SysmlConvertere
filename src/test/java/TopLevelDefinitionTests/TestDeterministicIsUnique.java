package TopLevelDefinitionTests;


import org.example.Mapping.Model.Taxonomy.PhysicalTwin.PhysicalTwinDefiniton;
import org.example.Mapping.Model.Taxonomy.PhysicalTwin.PhysicalTwinUsage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TestDeterministicIsUnique extends AbstarctTest {

	@Test
	public void testDeterministicIsUnique() {


		var allModels = result.getAll();
		for (var model : allModels) {
			if (model instanceof PhysicalTwinUsage) {
				System.out.println("Usage model: " + model.getName() + " with deterministic id: " + model.getId());
			}
			if (model instanceof PhysicalTwinDefiniton twin) {
				System.out.println("Definition model: " + model.getName() + " with deterministic id: " + model.getId());
				if (model.isLibrary()) {
					System.out.println("Model is library: ");
					twin.getSensors().forEach(System.out::println);
				}
			}
			for (var model2 : allModels) {

				// the id is the deterministic id now, so only the same object may share it
				if (model == model2) {
					continue;
				}

				if (model.getId().equals(model2.getId())) {
					fail("Unique Ids are the same for different models: " + model.getId() + " for models " + model.getName() + " and " + model2.getName());
				}

			}
		}

	}


}
