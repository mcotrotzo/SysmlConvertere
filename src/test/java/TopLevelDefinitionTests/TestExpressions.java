package TopLevelDefinitionTests;

import org.example.Mapping.Interfaces.*;
import org.example.Mapping.Mapper.TwinExpression.TwinCalculationExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinConstructorExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinLiteralExpressionElements.TwinLiteralBooleanExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinLiteralExpressionElements.TwinLiteralIntegerExpression;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.BaseFunctionKind;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestExpressions extends AbstarctTest {

	@Test
	public void testCollectionExpressionTest() {
		TwinRealAttributeUsage temp = named(TwinRealAttributeUsage.class, "collectionTest");

		Expression root = temp.getTwinExpressions().get();

		assertTrue(root instanceof TwinCalculationExpression);

		TwinCalculationExpression outer = (TwinCalculationExpression) root;

		MappedReference<?> ref = outer.getCalledFunction();

		var referentFunction = result.getByReference(ref, BaseFunction.class);

		assertNotNull(referentFunction);
		assertEquals(BaseFunctionKind.COLLECTION, referentFunction.getFunctionKind());
		assertEquals(2, outer.getArguments().size());

		Expression first = outer.getArguments().get(0);
		Expression second = outer.getArguments().get(1);

		assertInstanceOf(TwinLiteralIntegerExpression.class, first);

		assertInstanceOf(TwinCalculationExpression.class,second);

		TwinCalculationExpression inner = (TwinCalculationExpression) second;

		MappedReference<?> innerRed = inner.getCalledFunction();

		var referentInnerFunction = result.getByReference(innerRed, BaseFunction.class);

		assertNotNull(referentInnerFunction);
		assertEquals(BaseFunctionKind.COLLECTION, referentInnerFunction.getFunctionKind());
		assertEquals(2, inner.getArguments().size());

		assertTrue(inner.getArguments().get(0) instanceof TwinLiteralIntegerExpression);

		assertTrue(inner.getArguments().get(1) instanceof TwinLiteralIntegerExpression);
	}

	@Test
	public void testBaseFunctionExpression() {
		TwinRealAttributeUsage voltage = named(TwinRealAttributeUsage.class, "baseFunctionTest");

		assertNotNull(voltage.getTwinExpressions().get());

		Expression root = voltage.getTwinExpressions().get();

		assertTrue(root instanceof TwinCalculationExpression);

		TwinCalculationExpression calculation = (TwinCalculationExpression) root;

		BaseFunction function = result.getByReference(calculation.getCalledFunction(), BaseFunction.class);

		assertNotNull(function);
		assertEquals(BaseFunctionKind.DIVIDE, function.getFunctionKind());

		assertEquals(2, calculation.getArguments().size());

		Expression firstArgument = calculation.getArguments().get(0);

		Expression secondArgument = calculation.getArguments().get(1);


		assertInstanceOf(TwinLiteralIntegerExpression.class, firstArgument);

		assertInstanceOf(TwinLiteralIntegerExpression.class, secondArgument);

		TwinLiteralIntegerExpression firstLiteral = (TwinLiteralIntegerExpression) firstArgument;

		TwinLiteralIntegerExpression secondLiteral = (TwinLiteralIntegerExpression) secondArgument;

		assertEquals(Integer.valueOf(10), firstLiteral.getLiteralValue());

		assertEquals(Integer.valueOf(2), secondLiteral.getLiteralValue());
	}

	@Test
	public void testConstructorExpression() {
		TwinRealAttributeUsage current = named(TwinRealAttributeUsage.class, "constructorTest");

		assertNotNull(current.getTwinExpressions().get());

		Expression root = current.getTwinExpressions().get();

		assertInstanceOf(TwinConstructorExpression.class, root);

		TwinConstructorExpression calculation = (TwinConstructorExpression) root;

		BaseRealDefinition referentType = result.getByReference(calculation.getConstructedType(), BaseRealDefinition.class);

		assertNotNull(referentType);

		assertEquals(1, calculation.getArguments().size());
		Expression firstArgument = calculation.getArguments().get(0);

		assertInstanceOf(TwinLiteralIntegerExpression.class, firstArgument);
		assertEquals(Integer.valueOf(0), ((TwinLiteralIntegerExpression) firstArgument).getLiteralValue());

	}

	@Test
	public void testConstructorBooleanExpression() {
		TwinBooleanAttributeUsage current = named(TwinBooleanAttributeUsage.class, "constructorTestBoolean");

		assertNotNull(current.getTwinExpressions().get());

		Expression root = current.getTwinExpressions().get();

		assertInstanceOf(TwinConstructorExpression.class, root);

		TwinConstructorExpression calculation = (TwinConstructorExpression) root;

		BaseBooleanDefinition referentType = result.getByReference(calculation.getConstructedType(), BaseBooleanDefinition.class);

		assertNotNull(referentType);

		assertEquals(1, calculation.getArguments().size());
		Expression firstArgument = calculation.getArguments().get(0);

		assertInstanceOf(TwinLiteralBooleanExpression.class, firstArgument);
		assertEquals(Boolean.valueOf(false), ((TwinLiteralBooleanExpression) firstArgument).getLiteralValue());

	}

}
