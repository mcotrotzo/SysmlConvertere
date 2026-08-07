package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.*;
import org.example.Mapping.Mapper.TwinExpression.TwinCalculationExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinConstructorExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinLiteralExpressionElements.TwinLiteralBooleanExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinLiteralExpressionElements.TwinLiteralIntegerExpression;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.BaseFunctionKind;
import org.junit.Assert;
import org.junit.Test;

public class TestExpressions extends AbstarctTest {

	@Test
	public void testCollectionExpressionTest() {
		TwinRealAttribute temp = named(TwinRealAttribute.class, "collectionTest");

		Expression root = temp.getTwinExpressions().get();

		Assert.assertTrue(root instanceof TwinCalculationExpression);

		TwinCalculationExpression outer = (TwinCalculationExpression) root;

		MappedReference<?> ref = outer.getCalledFunction();

		var referentFunction = result.getByReference(ref, BaseFunction.class);

		Assert.assertNotNull(referentFunction);
		Assert.assertEquals(BaseFunctionKind.COLLECTION, referentFunction.getFunctionKind());
		Assert.assertEquals(2, outer.getArguments().size());

		Expression first = outer.getArguments().get(0);
		Expression second = outer.getArguments().get(1);

		Assert.assertTrue("Expected first argument to be TwinLiteralIntegerExpression, but was " + first.getClass().getName(), first instanceof TwinLiteralIntegerExpression);

		Assert.assertTrue("Expected second argument to be TwinCalculationExpression, but was " + second.getClass().getName(), second instanceof TwinCalculationExpression);

		TwinCalculationExpression inner = (TwinCalculationExpression) second;

		MappedReference<?> innerRed = inner.getCalledFunction();

		var referentInnerFunction = result.getByReference(innerRed, BaseFunction.class);

		Assert.assertNotNull(referentInnerFunction);
		Assert.assertEquals(BaseFunctionKind.COLLECTION, referentInnerFunction.getFunctionKind());
		Assert.assertEquals(2, inner.getArguments().size());

		Assert.assertTrue(inner.getArguments().get(0) instanceof TwinLiteralIntegerExpression);

		Assert.assertTrue(inner.getArguments().get(1) instanceof TwinLiteralIntegerExpression);
	}

	@Test
	public void testBaseFunctionExpression() {
		TwinRealAttribute voltage = named(TwinRealAttribute.class, "baseFunctionTest");

		Assert.assertNotNull(voltage.getTwinExpressions().get());

		Expression root = voltage.getTwinExpressions().get();

		Assert.assertTrue(root instanceof TwinCalculationExpression);

		TwinCalculationExpression calculation = (TwinCalculationExpression) root;

		BaseFunction function = result.getByReference(calculation.getCalledFunction(), BaseFunction.class);

		Assert.assertNotNull(function);
		Assert.assertEquals(BaseFunctionKind.DIVIDE, function.getFunctionKind());

		Assert.assertEquals(2, calculation.getArguments().size());

		Expression firstArgument = calculation.getArguments().get(0);

		Expression secondArgument = calculation.getArguments().get(1);


		Assert.assertTrue("Expected first argument to be TwinLiteralIntegerExpression, but was " + firstArgument.getClass().getName(), firstArgument instanceof TwinLiteralIntegerExpression);

		Assert.assertTrue("Expected second argument to be TwinLiteralIntegerExpression, but was " + secondArgument.getClass().getName(), secondArgument instanceof TwinLiteralIntegerExpression);

		TwinLiteralIntegerExpression firstLiteral = (TwinLiteralIntegerExpression) firstArgument;

		TwinLiteralIntegerExpression secondLiteral = (TwinLiteralIntegerExpression) secondArgument;

		Assert.assertEquals(Integer.valueOf(10), firstLiteral.getLiteralValue());

		Assert.assertEquals(Integer.valueOf(2), secondLiteral.getLiteralValue());
	}

	@Test
	public void testConstructorExpression() {
		TwinRealAttribute current = named(TwinRealAttribute.class, "constructorTest");

		Assert.assertNotNull(current.getTwinExpressions().get());

		Expression root = current.getTwinExpressions().get();

		Assert.assertTrue(root instanceof TwinConstructorExpression);

		TwinConstructorExpression calculation = (TwinConstructorExpression) root;

		TwinAttribute referentType = result.getByReference(calculation.getConstructedType(), TwinAttribute.class);

		Assert.assertNotNull(referentType);

		Assert.assertTrue(calculation.getArguments().size() == 1);
		Expression firstArgument = calculation.getArguments().get(0);

		Assert.assertTrue(firstArgument instanceof TwinLiteralIntegerExpression);
		Assert.assertEquals(Integer.valueOf(0), ((TwinLiteralIntegerExpression) firstArgument).getLiteralValue());

	}

	@Test
	public void testConstructorBooleanExpression() {
		TwinBooleanAttribute current = named(TwinBooleanAttribute.class, "constructorTestBoolean");

		Assert.assertNotNull(current.getTwinExpressions().get());

		Expression root = current.getTwinExpressions().get();

		Assert.assertTrue(root instanceof TwinConstructorExpression);

		TwinConstructorExpression calculation = (TwinConstructorExpression) root;

		TwinAttribute referentType = result.getByReference(calculation.getConstructedType(), TwinAttribute.class);

		Assert.assertNotNull(referentType);

		Assert.assertTrue(calculation.getArguments().size() == 1);
		Expression firstArgument = calculation.getArguments().get(0);

		Assert.assertTrue(firstArgument instanceof TwinLiteralBooleanExpression);
		Assert.assertEquals(Boolean.valueOf(false), ((TwinLiteralBooleanExpression) firstArgument).getLiteralValue());

	}

}
