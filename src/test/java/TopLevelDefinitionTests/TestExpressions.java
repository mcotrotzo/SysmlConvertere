package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseBoolean;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseReal;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.CustomType;
import org.example.Mapping.Interfaces.TwinExpression.BooleanLiteral;
import org.example.Mapping.Interfaces.TwinExpression.Calculation;
import org.example.Mapping.Interfaces.TwinExpression.FeatureReference;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;
import org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunction;
import org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunctionKind;
import org.example.Mapping.TwinExpression.TwinCalculationExpression;
import org.example.Mapping.TwinExpression.TwinConstructorExpression;
import org.example.Mapping.TwinExpression.TwinLiteralExpressionElements.TwinLiteralBooleanExpression;
import org.example.Mapping.TwinExpression.TwinLiteralExpressionElements.TwinLiteralIntegerExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestExpressions extends AbstarctTest {

	@Test
	public void testCollectionExpressionTest() {
		TwinBaseReal<Usage> temp = named(TwinBaseReal.class, Usage.class, "collectionTest");

		TwinExpression root = temp.getExpression().get();

		assertTrue(root instanceof TwinCalculationExpression);

		TwinCalculationExpression outer = (TwinCalculationExpression) root;

		Reference<?> ref = outer.getCalledFunction();

		assertInstanceOf(BaseFunction.class, ref.getReferent());
		BaseFunction referentFunction = (BaseFunction) ref.getReferent();

		assertNotNull(referentFunction);
		assertEquals(BaseFunctionKind.COLLECTION, referentFunction.getFunctionKind());
		assertEquals(2, outer.getArguments().size());

		TwinExpression first = outer.getArguments().get(0);
		TwinExpression second = outer.getArguments().get(1);

		assertInstanceOf(TwinLiteralIntegerExpression.class, first);
		assertInstanceOf(TwinCalculationExpression.class, second);

		TwinCalculationExpression inner = (TwinCalculationExpression) second;

		Reference<?> innerRed = inner.getCalledFunction();

		assertInstanceOf(BaseFunction.class, innerRed.getReferent());
		BaseFunction referentInnerFunction = (BaseFunction) innerRed.getReferent();

		assertNotNull(referentInnerFunction);
		assertEquals(BaseFunctionKind.COLLECTION, referentInnerFunction.getFunctionKind());
		assertEquals(2, inner.getArguments().size());

		assertTrue(inner.getArguments().get(0) instanceof TwinLiteralIntegerExpression);
		assertTrue(inner.getArguments().get(1) instanceof TwinLiteralIntegerExpression);
	}

	@Test
	public void testBaseFunctionExpression() {
		TwinBaseReal<Usage> voltage = named(TwinBaseReal.class, Usage.class, "baseFunctionTest");

		assertNotNull(voltage.getExpression().get());

		TwinExpression root = voltage.getExpression().get();

		assertTrue(root instanceof TwinCalculationExpression);

		TwinCalculationExpression calculation = (TwinCalculationExpression) root;

		assertInstanceOf(BaseFunction.class, calculation.getCalledFunction().getReferent());
		BaseFunction function = (BaseFunction) calculation.getCalledFunction().getReferent();

		assertNotNull(function);
		assertEquals(BaseFunctionKind.DIVIDE, function.getFunctionKind());

		assertEquals(2, calculation.getArguments().size());

		TwinExpression firstArgument = calculation.getArguments().get(0);
		TwinExpression secondArgument = calculation.getArguments().get(1);

		assertInstanceOf(TwinLiteralIntegerExpression.class, firstArgument);
		assertInstanceOf(TwinLiteralIntegerExpression.class, secondArgument);

		TwinLiteralIntegerExpression firstLiteral = (TwinLiteralIntegerExpression) firstArgument;
		TwinLiteralIntegerExpression secondLiteral = (TwinLiteralIntegerExpression) secondArgument;

		assertEquals(Integer.valueOf(10), firstLiteral.getLiteralValue());
		assertEquals(Integer.valueOf(2), secondLiteral.getLiteralValue());
	}

	@Test
	public void testConstructorExpression() {
		TwinBaseBoolean<Usage> current = named(TwinBaseBoolean.class, Usage.class, "constructorTest");

		assertNotNull(current.getExpression().get());

		TwinExpression root = current.getExpression().get();

		assertInstanceOf(TwinConstructorExpression.class, root);

		TwinConstructorExpression calculation = (TwinConstructorExpression) root;

		assertInstanceOf(TwinBaseBoolean.class, calculation.getConstructedType().getReferent());
		TwinBaseBoolean<?> referentType =
				(TwinBaseBoolean<?>) calculation.getConstructedType().getReferent();

		assertNotNull(referentType);

		assertEquals(1, calculation.getArguments().size());

		TwinExpression firstArgument = calculation.getArguments().get(0);

		assertInstanceOf(BooleanLiteral.class, firstArgument);
		assertEquals(Boolean.TRUE, ((BooleanLiteral) firstArgument).getLiteralValue());
	}

	@Test
	public void testConstructorBooleanExpression() {
		TwinBaseBoolean<Usage> current =
				named(TwinBaseBoolean.class, Usage.class, "constructorTestBoolean");

		assertNotNull(current.getExpression().get());

		TwinExpression root = current.getExpression().get();

		assertInstanceOf(TwinConstructorExpression.class, root);

		TwinConstructorExpression calculation = (TwinConstructorExpression) root;

		assertInstanceOf(TwinBaseBoolean.class, calculation.getConstructedType().getReferent());
		TwinBaseBoolean<?> referentType =
				(TwinBaseBoolean<?>) calculation.getConstructedType().getReferent();

		assertNotNull(referentType);

		assertEquals(1, calculation.getArguments().size());

		TwinExpression firstArgument = calculation.getArguments().get(0);

		assertInstanceOf(TwinLiteralBooleanExpression.class, firstArgument);
		assertEquals(Boolean.valueOf(false),
				((TwinLiteralBooleanExpression) firstArgument).getLiteralValue());
	}

	@Test
	public void testFeatureChainExpression() {
		TwinBaseReal<Usage> current =
				named(TwinBaseReal.class, Usage.class, "test12");

		TwinExpression root = current.getExpression().orElseThrow();

		assertInstanceOf(Calculation.class, root);

		Calculation invocation = (Calculation) root;

		assertEquals(1, invocation.getArguments().size());

		TwinExpression argument = invocation.getArguments().getFirst();

		assertInstanceOf(FeatureReference.class, argument);

		FeatureReference chain = (FeatureReference) argument;

		assertFalse(chain.getChain().isEmpty());
		assertEquals(2, chain.getChain().size());

		assertEquals(
				"posTest12",
				chain.getChain().getFirst().getReferent().getName()
		);

		assertEquals(
				"x",
				chain.getChain().getLast().getReferent().getName()
		);

		CustomType<Usage> posTest12 =
				named(CustomType.class, Usage.class, "posTest12");

		var x = posTest12.getFields()
				.getCompartment()
				.stream()
				.map(c -> c.getElement())
				.filter(y -> "x".equals(y.getName()))
				.findFirst()
				.orElseThrow();

		assertEquals(
				posTest12.getId(),
				chain.getChain().getFirst().getReferent().getId()
		);

		assertEquals(
				x.getId(),
				chain.getChain().getLast().getReferent().getId()
		);

		assertNotNull(
				chain.getChain().getLast().getReferent().getParent()
		);


	}
}