package TopLevelDefinitionTests;

import org.example.Mapping.BaseFunctionKind;
import org.example.Mapping.Model.Action.TwinActionBlockUsage;
import org.example.Mapping.Model.Action.TwinAssignmentUsage;
import org.example.Mapping.Model.Attribute.CustomTypeUsage;
import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import org.example.Mapping.Model.Expression.TwinCalculationUsage;
import org.example.Mapping.Model.Expression.TwinConstructorUsage;
import org.example.Mapping.Model.Expression.TwinExpressionUsage;
import org.example.Mapping.Model.Expression.TwinReferenceUsage;
import org.example.Mapping.Model.Federation.FederationTwinDefinition;
import org.example.Mapping.Model.Flow.FederationFlowUsage;
import org.example.Mapping.Model.Function.BaseFunctionDefinition;
import org.example.Mapping.Model.Function.CustomCalculationDefinition;
import org.example.Mapping.Model.Port.ConstPortUsage;
import org.example.Mapping.Model.Strategy.TwinStrategyUsage;
import org.example.Mapping.Model.Twin.TwinUsage;
import org.example.Mapping.Model.Type.Usage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestFeatureChainCompartmentResolution extends AbstarctTest {

	@Test
	public void testAddPositionAssignmentStructureIsPresent() {
		TwinAssignmentUsage assignment = findAssignmentTo("pos_c");

		TwinConstructorUsage constructor = assertInstanceOf(TwinConstructorUsage.class, assignment.getValue());

		assertEquals("Position", constructor.getConstructorType().getName());
		assertEquals(3, constructor.getArguments().size());

		for (TwinExpressionUsage argument : constructor.getArguments()) {
			TwinCalculationUsage calculation = assertInstanceOf(TwinCalculationUsage.class, argument);

			BaseFunctionDefinition function =
					assertInstanceOf(BaseFunctionDefinition.class, calculation.getInvokeType());

			assertEquals(BaseFunctionKind.ADD, function.getCore().getFunctionKind());
			assertEquals(2, calculation.getArguments().size());
		}
	}

	@Test
	public void testTwoLevelChainPosAxResolvesToCorrectCompartment() {
		TwinReferenceUsage<?> reference = chainArgumentOf("x", 0);

		assertEquals(field(input("pos_a"), "x").getId(), reference.getTarget().getId());
	}

	@Test
	public void testThreeLevelChainPosBxxResolvesToCorrectCompartment() {
		TwinReferenceUsage<?> reference = chainArgumentOf("x", 1);

		assertEquals(field(fieldOf(input("pos_b"), "x"), "x").getId(), reference.getTarget().getId());
	}

	@Test
	public void testThreeLevelChainPosByyResolvesToCorrectCompartment() {
		TwinReferenceUsage<?> reference = chainArgumentOf("y", 1);

		assertEquals(field(fieldOf(input("pos_b"), "x"), "y").getId(), reference.getTarget().getId());
	}

	@Test
	public void testThreeLevelChainPosBzzResolvesToCorrectCompartment() {
		TwinReferenceUsage<?> reference = chainArgumentOf("z", 1);

		assertEquals(field(fieldOf(input("pos_b"), "x"), "z").getId(), reference.getTarget().getId());
	}

	@Test
	public void testSameInheritedFeatureResolvesToDifferentCompartments() {
		Usage<?, ?, ?> posAx = chainArgumentOf("x", 0).getTarget();
		Usage<?, ?, ?> posBxx = chainArgumentOf("x", 1).getTarget();

		assertEquals(posAx.getName(), posBxx.getName());

		assertNotEquals(
				posAx.getParent().orElseThrow().getId(),
				posBxx.getParent().orElseThrow().getId()
		);

		assertNotEquals(
				posAx.getId(),
				posBxx.getId()
		);
	}

	@Test
	public void testReferenceInsideCopyResolvesToCopy() {
		TwinUsage battery = named(TwinUsage.class, "battery");
		ConstPortUsage constPort = battery.getPhysicalTwin().orElseThrow().getConstPorts().getFirst();

		TwinAttributeUsage<?, ?> maxCharge = measurement(constPort, "maxCharge");
		TwinAttributeUsage<?, ?> nominalVoltage = measurement(constPort, "nominalVoltage");

		// INDEX_seq(nominalVoltage, 1) * 10
		TwinCalculationUsage times = assertInstanceOf(TwinCalculationUsage.class, maxCharge.getExpression().orElseThrow());
		TwinCalculationUsage index = assertInstanceOf(TwinCalculationUsage.class, times.getArguments().getFirst());
		TwinReferenceUsage<?> reference = assertInstanceOf(TwinReferenceUsage.class, index.getArguments().getFirst());

		assertEquals(nominalVoltage.getId(), reference.getTarget().getId());
	}

	@Test
	public void testFederationFlowTargetResolvesToCopy() {
		FederationTwinDefinition federatedBattery = named(FederationTwinDefinition.class, "FederatedBattery");
		FederationFlowUsage flow = federatedBattery.getFederationFlows().getFirst();

		TwinUsage battery = named(TwinUsage.class, "battery");
		TwinStrategyUsage<?, ?> llmRequest = battery.getDescriptiveModel().orElseThrow().getDescriptiveStrategies().stream()
				.filter(s -> "LLM_Request".equals(s.getName())).findFirst().orElseThrow();
		TwinAttributeUsage<?, ?> soc = llmRequest.getInputs().stream()
				.filter(i -> "soc".equals(i.getName())).findFirst().orElseThrow();

		assertEquals(soc.getId(), flow.getTarget().getId());
	}

	@Test
	public void testFirstElementOfChainResolvesToCorrectCompartment() {
		TwinActionBlockUsage<?, ?> soc2 = named(TwinActionBlockUsage.class, "soc2");
		TwinAttributeUsage<?, ?> voltageInSoc2 = soc2.getInputs().stream()
				.filter(i -> "voltage".equals(i.getName())).findFirst().orElseThrow();

		TwinAttributeUsage<?, ?> doubled = named(TwinAttributeUsage.class, "doubled");
		TwinCalculationUsage mult = assertInstanceOf(TwinCalculationUsage.class, doubled.getExpression().orElseThrow());
		TwinReferenceUsage<?> reference = assertInstanceOf(TwinReferenceUsage.class, mult.getArguments().getFirst());

		assertEquals(voltageInSoc2.getId(), reference.getTarget().getId());
	}

	private CustomTypeUsage input(String name) {
		CustomCalculationDefinition addPosition = named(CustomCalculationDefinition.class, "AddPosition");
		return assertInstanceOf(CustomTypeUsage.class, addPosition.getInputs().stream()
				.filter(i -> name.equals(i.getName())).findFirst().orElseThrow());
	}

	private TwinAttributeUsage<?, ?> field(CustomTypeUsage owner, String name) {
		return owner.getFields().stream().filter(f -> name.equals(f.getName())).findFirst().orElseThrow();
	}

	private CustomTypeUsage fieldOf(CustomTypeUsage owner, String name) {
		return assertInstanceOf(CustomTypeUsage.class, field(owner, name));
	}

	private TwinAttributeUsage<?, ?> measurement(ConstPortUsage port, String name) {
		return port.getMeasurements().stream().filter(m -> name.equals(m.getName())).findFirst().orElseThrow();
	}



	private TwinReferenceUsage<?> chainArgumentOf(
			String constructorArgument,
			int calculationArgument
	) {
		TwinConstructorUsage constructor =
				assertInstanceOf(TwinConstructorUsage.class, findAssignmentTo("pos_c").getValue());

		int position = switch (constructorArgument) {
			case "x" -> 0;
			case "y" -> 1;
			case "z" -> 2;
			default -> throw new IllegalArgumentException(constructorArgument);
		};

		TwinExpressionUsage argument =
				constructor.getArguments().get(position);

		TwinCalculationUsage calculation = assertInstanceOf(TwinCalculationUsage.class, argument);

		TwinExpressionUsage reference =
				calculation
						.getArguments()
						.get(calculationArgument);

		return assertInstanceOf(TwinReferenceUsage.class, reference);
	}

	private TwinAssignmentUsage findAssignmentTo(String targetName) {
		return originals(TwinAssignmentUsage.class).stream()
				.filter(assignment ->
						assignment.getReferent() != null
								&& targetName.equals(
								assignment.getReferent().getName()
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