package TopLevelDefinitionTests;

import org.example.Mapping.EnumTimeUnit;
import org.example.Mapping.Model.Action.TwinTriggerActionUsage;
import org.example.Mapping.Model.Action.TwinTriggerUsage;
import org.example.Mapping.Model.Expression.TwinLiteralBooleanUsage;
import org.example.Mapping.Model.Expression.TwinLiteralIntegerUsage;
import org.example.Mapping.Model.StateMachine.TwinStateMachineUsage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTrigger extends AbstarctTest {

	@Test
	public void testControlUnitHasTrigger() {
		TwinTriggerUsage trigger = named(TwinStateMachineUsage.class, "cm1").getTrigger().orElseThrow();

		assertTrigger(trigger, 1, EnumTimeUnit.SECOND, true);
	}

	@Test
	public void testDerivedAttributeHasTrigger() {
		TwinTriggerActionUsage<?, ?> temp30 = named(TwinTriggerActionUsage.class, "temp30");
		TwinTriggerUsage trigger = temp30.getTrigger().orElseThrow();

		assertTrigger(trigger, 1, EnumTimeUnit.MINUTE, false);
	}


	@Test
	public void testActionWithoutTriggerHasNone() {
		TwinTriggerActionUsage<?, ?> soc2 = named(TwinTriggerActionUsage.class, "soc2");

		assertTrue(soc2.getTrigger().isEmpty());
	}

	@Test
	public void testTriggerParentIsAction() {
		TwinStateMachineUsage cm1 = named(TwinStateMachineUsage.class, "cm1");

		assertEquals(cm1.getId(), cm1.getTrigger().orElseThrow().getParent().orElseThrow().getId());
	}

	private void assertTrigger(TwinTriggerUsage trigger, Integer interval, EnumTimeUnit unit, Boolean triggerOnly) {
		TwinLiteralIntegerUsage intervalValue = assertInstanceOf(TwinLiteralIntegerUsage.class, trigger.getInterval().getExpression().orElseThrow());
		TwinLiteralBooleanUsage triggerOnlyValue = assertInstanceOf(TwinLiteralBooleanUsage.class, trigger.getTriggerOnly().getExpression().orElseThrow());

		assertEquals(interval, intervalValue.getValue());
		assertEquals(unit, trigger.getIntervalUnit().getValue().orElseThrow());
		assertEquals(triggerOnly, triggerOnlyValue.getValue());
	}
}
