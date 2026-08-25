package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.*;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseBoolean;

import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseBooleanUsage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseIntegerUsage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseRealUsage;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.Usage.CustomTypeUsage;
import org.example.Mapping.Interfaces.TwinExpression.BooleanLiteral;
import org.example.Mapping.Interfaces.TwinExpression.Calculation;
import org.example.Mapping.Interfaces.TwinExpression.FeatureReference;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;
import org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunction;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinRealMappedUsage;
import org.example.Mapping.TwinExpression.TwinCalculationExpression;
import org.example.Mapping.TwinExpression.TwinConstructorExpression;
import org.example.Mapping.TwinExpression.TwinLiteralExpressionElements.TwinLiteralBooleanExpression;
import org.example.Mapping.TwinExpression.TwinLiteralExpressionElements.TwinLiteralIntegerExpression;
import org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunctionKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestExpressions extends AbstarctTest {

	@Test
	public void testCollectionExpressionTest() {
		TwinBaseRealUsage temp = named(TwinBaseRealUsage.class, "collectionTest");

		TwinExpression root = temp.getExpression().get();

		assertTrue(root instanceof TwinCalculationExpression);

		TwinCalculationExpression outer = (TwinCalculationExpression) root;

		Reference<?> ref = outer.getCalledFunction();

		var referentFunction = result.getByReference(ref, BaseFunction.class);

		assertNotNull(referentFunction);
		assertEquals(BaseFunctionKind.COLLECTION, referentFunction.getFunctionKind());
		assertEquals(2, outer.getArguments().size());

		TwinExpression first = outer.getArguments().get(0);
		TwinExpression second = outer.getArguments().get(1);

		assertInstanceOf(TwinLiteralIntegerExpression.class, first);

		assertInstanceOf(TwinCalculationExpression.class,second);

		TwinCalculationExpression inner = (TwinCalculationExpression) second;

		Reference<?> innerRed = inner.getCalledFunction();

		var referentInnerFunction = result.getByReference(innerRed, BaseFunction.class);

		assertNotNull(referentInnerFunction);
		assertEquals(BaseFunctionKind.COLLECTION, referentInnerFunction.getFunctionKind());
		assertEquals(2, inner.getArguments().size());

		assertTrue(inner.getArguments().get(0) instanceof TwinLiteralIntegerExpression);

		assertTrue(inner.getArguments().get(1) instanceof TwinLiteralIntegerExpression);
	}

	@Test
	public void testBaseFunctionExpression() {
		TwinRealMappedUsage voltage = named(TwinRealMappedUsage.class, "baseFunctionTest");

		assertNotNull(voltage.getExpression().get());

		TwinExpression root = voltage.getExpression().get();

		assertTrue(root instanceof TwinCalculationExpression);

		TwinCalculationExpression calculation = (TwinCalculationExpression) root;

		BaseFunction function = result.getByReference(calculation.getCalledFunction(), BaseFunction.class);

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
		TwinBaseBooleanUsage current = named(TwinBaseBooleanUsage.class, "constructorTest");

		assertNotNull(current.getExpression().get());

		TwinExpression root = current.getExpression().get();

		assertInstanceOf(TwinConstructorExpression.class, root);

		TwinConstructorExpression calculation = (TwinConstructorExpression) root;

		TwinBaseBoolean referentType = result.getByReference(calculation.getConstructedType(), TwinBaseBoolean.class);

		assertNotNull(referentType);

		assertEquals(1, calculation.getArguments().size());
		TwinExpression firstArgument = calculation.getArguments().get(0);

		assertInstanceOf(BooleanLiteral.class, firstArgument);
		assertEquals(Boolean.TRUE, ((BooleanLiteral) firstArgument).getLiteralValue());

	}

	@Test
	public void testConstructorBooleanExpression() {
		TwinBaseBooleanUsage current = named(TwinBaseBooleanUsage.class, "constructorTestBoolean");

		assertNotNull(current.getExpression().get());

		TwinExpression root = current.getExpression().get();

		assertInstanceOf(TwinConstructorExpression.class, root);

		TwinConstructorExpression calculation = (TwinConstructorExpression) root;

		TwinBaseBoolean referentType = result.getByReference(calculation.getConstructedType(), TwinBaseBoolean.class);

		assertNotNull(referentType);

		assertEquals(1, calculation.getArguments().size());
		TwinExpression firstArgument = calculation.getArguments().get(0);

		assertInstanceOf(TwinLiteralBooleanExpression.class, firstArgument);
		assertEquals(Boolean.valueOf(false), ((TwinLiteralBooleanExpression) firstArgument).getLiteralValue());

	}

	@Test
	public void testFeatureChainExpression() {
		TwinBaseRealUsage current =
				named(TwinBaseRealUsage.class, "test12");

		TwinExpression root = current.getExpression().orElseThrow();

		assertInstanceOf(Calculation.class, root);

		Calculation invocation = (Calculation) root;

		assertEquals(1, invocation.getArguments().size());

		TwinExpression argument = invocation.getArguments().getFirst();

		assertInstanceOf(FeatureReference.class, argument);

		FeatureReference chain = (FeatureReference) argument;

		assertEquals(
				"x",
				chain.getTarget().getReferent().getName()
		);

		assertNotNull(
				chain.getTarget().getReferent().getParent()
		);

		CustomTypeUsage posTest12 =
				named(CustomTypeUsage.class, "posTest12");

		TwinBaseIntegerUsage x = posTest12.getFields().stream()
				.filter(y -> y.getName().equals("x"))
				.map(TwinBaseIntegerUsage.class::cast)
				.findFirst()
				.orElseThrow();

		assertEquals(
				x.getId(),
				chain.getTarget().getReferent().getId()
		);
	}
}
