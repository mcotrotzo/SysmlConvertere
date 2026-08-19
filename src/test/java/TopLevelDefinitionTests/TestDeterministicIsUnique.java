package TopLevelDefinitionTests;


import org.example.Mapping.Interfaces.Twin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TestDeterministicIsUnique extends AbstarctTest{

	@Test
	public void testDeterministicIsUnique() {
		var allModels = result.get(Twin.class).stream().findFirst().get().getPhysicalTwin().get().getConstAttributes();


		for(var model : allModels) {
			for (var model2: allModels) {

				if(model.getId().equals(model2.getId())) {
					continue;
				}
				if(model.getDeterministicId().equals(model2.getDeterministicId())) {
					fail("Unique Ids are the same for different models: " + model.getDeterministicId() + " for models " + model.getName() + " and " + model2.getName());
				}

			}
		}
	}
}
