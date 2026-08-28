package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.Base.Compartment;
import org.example.Mapping.Interfaces.Base.Type;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAction.Assignment;
import org.example.Mapping.Interfaces.TwinExpression.Calculation;
import org.example.Mapping.Interfaces.TwinExpression.ConstructorCall;
import org.example.Mapping.Interfaces.TwinExpression.FeatureReference;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;
import org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunction;
import org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunctionKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFeatureChainCompartmentResolution extends AbstarctTest {

	@Test
	public void testAddPositionAssignmentStructureIsPresent() {
		Assignment assignment = findAssignmentTo("pos_c");

		assertInstanceOf(ConstructorCall.class, assignment.getValue());
		ConstructorCall constructor = (ConstructorCall) assignment.getValue();

		assertEquals("Position", constructor.getConstructedType().getReferent().getName());
		assertEquals(3, constructor.getArguments().size());

		for (TwinExpression argument : constructor.getArguments()) {
			assertInstanceOf(Calculation.class, argument);

			Calculation calculation = (Calculation) argument;

			assertInstanceOf(BaseFunction.class, calculation.getCalledFunction().getReferent());

			BaseFunction function =
					(BaseFunction) calculation.getCalledFunction().getReferent();

			assertEquals(BaseFunctionKind.ADD, function.getFunctionKind());
			assertEquals(2, calculation.getArguments().size());
		}
	}

	@Test
	public void testTwoLevelChainPosAxResolvesToCorrectCompartment() {
		FeatureReference reference = chainArgumentOf("x", 0);

		assertChain(reference, "pos_a", "x");
		assertResolvedCompartment(reference);
	}

	@Test
	public void testThreeLevelChainPosBxxResolvesToCorrectCompartment() {
		FeatureReference reference = chainArgumentOf("x", 1);

		assertChain(reference, "pos_b", "x", "x");
		assertResolvedCompartment(reference);
	}

	@Test
	public void testThreeLevelChainPosByyResolvesToCorrectCompartment() {
		FeatureReference reference = chainArgumentOf("y", 1);

		assertChain(reference, "pos_b", "x", "y");
		assertResolvedCompartment(reference);
	}

	@Test
	public void testThreeLevelChainPosBzzResolvesToCorrectCompartment() {
		FeatureReference reference = chainArgumentOf("z", 1);

		assertChain(reference, "pos_b", "x", "z");
		assertResolvedCompartment(reference);
	}

	private void assertChain(
			FeatureReference reference,
			String... expectedNames
	) {
		assertEquals(expectedNames.length, reference.getChain().size());

		for (int i = 0; i < expectedNames.length; i++) {
			assertEquals(
					expectedNames[i],
					reference.getChain().get(i).getReferent().getName()
			);
		}
	}
	@Test
	public void testSameInheritedFeatureResolvesToDifferentCompartments() {
		FeatureReference posAx = chainArgumentOf("x", 0);
		FeatureReference posBxx = chainArgumentOf("x", 1);

		Compartment<?> posAxCompartment =
				posAx.getAsCompartment().getReferent();

		Compartment<?> posBxxCompartment =
				posBxx.getAsCompartment().getReferent();

		assertEquals(
				posAxCompartment.getElement().getId(),
				posBxxCompartment.getElement().getId()
		);

		assertNotEquals(
				posAxCompartment.getParent().orElseThrow().getId(),
				posBxxCompartment.getParent().orElseThrow().getId()
		);

		assertNotEquals(
				posAxCompartment.getId(),
				posBxxCompartment.getId()
		);
	}

	private void assertResolvedCompartment(FeatureReference reference) {
		int size = reference.getChain().size();

		Type<Usage> parent =
				reference.getChain().get(size - 2).getReferent();

		Type<Usage> element =
				reference.getChain().get(size - 1).getReferent();

		Compartment<?> expected =
				result.getCompartment(parent, element);

		Compartment<?> actual =
				reference.getAsCompartment().getReferent();

		assertNotNull(actual);

		assertEquals(
				expected.getId(),
				actual.getId(),
				"Feature chain resolved to the wrong compartment"
		);

		assertEquals(
				parent.getId(),
				actual.getParent().orElseThrow().getId(),
				"Resolved compartment has the wrong parent"
		);

		assertEquals(
				element.getId(),
				actual.getElement().getId(),
				"Resolved compartment has the wrong element"
		);
	}

	private FeatureReference chainArgumentOf(
			String constructorArgument,
			int calculationArgument
	) {
		ConstructorCall constructor =
				(ConstructorCall) findAssignmentTo("pos_c").getValue();

		int position = switch (constructorArgument) {
			case "x" -> 0;
			case "y" -> 1;
			case "z" -> 2;
			default -> throw new IllegalArgumentException(constructorArgument);
		};

		TwinExpression argument =
				constructor.getArguments().get(position);

		assertInstanceOf(Calculation.class, argument);

		TwinExpression reference =
				((Calculation) argument)
						.getArguments()
						.get(calculationArgument);

		assertInstanceOf(FeatureReference.class, reference);

		return (FeatureReference) reference;
	}

	private Assignment findAssignmentTo(String targetName) {
		return result.get(Assignment.class, Usage.class).stream()
				.filter(assignment ->
						assignment.getTarget() != null
								&& assignment.getTarget().getReferent() != null
								&& targetName.equals(
								assignment.getTarget().getReferent().getName()
						)
				)
				.findFirst()
				.orElseThrow(() ->
						new AssertionError(
								"Assignment to '" + targetName + "' not found"
						)
				);
	}
}