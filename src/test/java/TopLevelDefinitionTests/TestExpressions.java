package TopLevelDefinitionTests;



import Model.AbstractType;
import Model.Predefined.MetaClasses.Function.BaseFunctionKind;
import org.example.Mapping.Model.Attribute.*;
import org.example.Mapping.Model.Expression.TwinCalculationUsage;
import org.example.Mapping.Model.Expression.TwinConstructorUsage;
import Model.Predefined.MetaClasses.Expression.ExpressionUsage;
import org.example.Mapping.Model.Expression.TwinLiteralBooleanUsage;
import org.example.Mapping.Model.Expression.TwinLiteralIntegerUsage;
import Model.Predefined.MetaClasses.Expression.ReferenceUsage;
import org.example.Mapping.Model.Function.BaseFunctionDefinition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestExpressions extends AbstarctTest {

	@Test
	public void testCollectionExpressionTest() {
		TwinAttributeRealUsage temp = named(TwinAttributeRealUsage.class, "collectionTest");

		ExpressionUsage root = temp.getExpression().get();

		TwinCalculationUsage outer = assertInstanceOf(TwinCalculationUsage.class, root);

		BaseFunctionDefinition referentFunction = assertInstanceOf(BaseFunctionDefinition.class, outer.getInvokeType());

		assertNotNull(referentFunction);
		assertEquals(BaseFunctionKind.COLLECTION, referentFunction.getCore().getFunctionKind());
		assertEquals(2, outer.getArguments().size());

		ExpressionUsage first = outer.getArguments().get(0);
		ExpressionUsage second = outer.getArguments().get(1);

		assertInstanceOf(TwinLiteralIntegerUsage.class, first);
		assertInstanceOf(TwinCalculationUsage.class, second);

		TwinCalculationUsage inner = assertInstanceOf(TwinCalculationUsage.class, second);

		BaseFunctionDefinition referentInnerFunction = assertInstanceOf(BaseFunctionDefinition.class, inner.getInvokeType());

		assertNotNull(referentInnerFunction);
		assertEquals(BaseFunctionKind.COLLECTION, referentInnerFunction.getCore().getFunctionKind());
		assertEquals(2, inner.getArguments().size());

		assertTrue(inner.getArguments().get(0) instanceof TwinLiteralIntegerUsage);
		assertTrue(inner.getArguments().get(1) instanceof TwinLiteralIntegerUsage);
	}

	@Test
	public void testBaseFunctionExpression() {
		TwinAttributeRealUsage voltage = named(TwinAttributeRealUsage.class, "baseFunctionTest");

		assertNotNull(voltage.getExpression().get());

		ExpressionUsage root = voltage.getExpression().get();

		TwinCalculationUsage calculation = assertInstanceOf(TwinCalculationUsage.class, root);

		BaseFunctionDefinition function = assertInstanceOf(BaseFunctionDefinition.class, calculation.getInvokeType());

		assertNotNull(function);
		assertEquals(BaseFunctionKind.DIVIDE, function.getCore().getFunctionKind());

		assertEquals(2, calculation.getArguments().size());

		ExpressionUsage firstArgument = calculation.getArguments().get(0);
		ExpressionUsage secondArgument = calculation.getArguments().get(1);

		TwinLiteralIntegerUsage firstLiteral = assertInstanceOf(TwinLiteralIntegerUsage.class, firstArgument);
		TwinLiteralIntegerUsage secondLiteral = assertInstanceOf(TwinLiteralIntegerUsage.class, secondArgument);

		assertEquals(Integer.valueOf(10), firstLiteral.getValue());
		assertEquals(Integer.valueOf(2), secondLiteral.getValue());
	}

	@Test
	public void testConstructorExpression() {
		TwinAttributeBooleanUsage current = named(TwinAttributeBooleanUsage.class, "constructorTest");

		assertNotNull(current.getExpression().get());

		ExpressionUsage root = current.getExpression().get();

		TwinConstructorUsage calculation = assertInstanceOf(TwinConstructorUsage.class, root);

		TwinAttributeBooleanDefinition referentType = assertInstanceOf(TwinAttributeBooleanDefinition.class, calculation.getConstructorType());


		assertNotNull(referentType);

		assertEquals(1, calculation.getArguments().size());

		ExpressionUsage firstArgument = calculation.getArguments().get(0);

		TwinLiteralBooleanUsage literal = assertInstanceOf(TwinLiteralBooleanUsage.class, firstArgument);
		assertEquals(Boolean.TRUE, literal.getValue());
	}

	@Test
	public void testConstructorBooleanExpression() {
		TwinAttributeBooleanUsage current =
				named(TwinAttributeBooleanUsage.class, "constructorTestBoolean");

		assertNotNull(current.getExpression().get());

		ExpressionUsage root = current.getExpression().get();

		TwinConstructorUsage calculation = assertInstanceOf(TwinConstructorUsage.class, root);

		// there is no boolean definition class anymore: TwinBoolean is an alias of ScalarValues::Boolean
		TwinAttributeDefinition<?> referentType = assertInstanceOf(TwinAttributeDefinition.class, calculation.getConstructorType());
		assertEquals("Boolean", referentType.getName());

		assertNotNull(referentType);

		assertEquals(1, calculation.getArguments().size());

		ExpressionUsage firstArgument = calculation.getArguments().get(0);

		TwinLiteralBooleanUsage literal = assertInstanceOf(TwinLiteralBooleanUsage.class, firstArgument);
		assertEquals(Boolean.valueOf(false), literal.getValue());
	}

	@Test
	public void testFeatureChainExpression() {
		TwinAttributeRealUsage current =
				named(TwinAttributeRealUsage.class, "test12");

		ExpressionUsage root = current.getExpression().orElseThrow();

		TwinCalculationUsage invocation = assertInstanceOf(TwinCalculationUsage.class, root);

		assertEquals(1, invocation.getArguments().size());

		ExpressionUsage argument = invocation.getArguments().getFirst();

		ReferenceUsage<?> reference = assertInstanceOf(ReferenceUsage.class, argument);

		List<AbstractType<?, ?>> chain = attributeChain(reference.getTarget());

		assertFalse(chain.isEmpty());
		assertEquals(2, chain.size());

		assertEquals(
				"posTest12",
				chain.getFirst().getName()
		);

		assertEquals(
				"x",
				chain.getLast().getName()
		);

		CustomTypeUsage posTest12 =
				named(CustomTypeUsage.class, "posTest12");

		TwinAttributeUsage<?, ?> x = posTest12.getFields()
				.stream()
				.filter(y -> "x".equals(y.getName()))
				.findFirst()
				.orElseThrow();

		assertEquals(
				posTest12.getId(),
				chain.getFirst().getId()
		);

		assertEquals(
				x.getId(),
				chain.getLast().getId()
		);

		assertNotNull(
				chain.getLast().getParent()
		);


	}
}
