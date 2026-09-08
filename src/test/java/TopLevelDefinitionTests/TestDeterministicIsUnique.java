package TopLevelDefinitionTests;


import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.BaseTaxonomy.PhysicalTwin;
import org.example.Mapping.Interfaces.FullTwin.Twin;
import org.example.Mapping.Interfaces.TwinFlow.Flow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TestDeterministicIsUnique extends AbstarctTest {

	@Test
	public void testDeterministicIsUnique() {


		var allModels = result.getAll();
		for (var model : allModels) {
			if( model instanceof PhysicalTwin<?> twin){
				if(model.getKind() instanceof Usage ){
					System.out.println("Usage model: " + model.getName() + " with deterministic id: " + model.getDeterministicId());
				}
				if( model.getKind() instanceof Definition){
					System.out.println("Definition model: " + model.getName() + " with deterministic id: " + model.getDeterministicId());
				}
				if(model.isLibraryElement()){
					System.out.println("Model is library: ");
					twin.getSensors().getCompartment().forEach(System.out::println);
				}
			}
			for (var model2 : allModels) {

				if (model.getId().equals(model2.getId())) {
					continue;
				}

				if (model.getDeterministicId().equals(model2.getDeterministicId())) {
					fail("Unique Ids are the same for different models: " + model.getDeterministicId() + " for models " + model.getName() + " and " + model2.getName());
				}

			}
		}

	}


}
