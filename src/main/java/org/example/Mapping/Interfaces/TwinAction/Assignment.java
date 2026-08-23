package org.example.Mapping.Interfaces.TwinAction;

import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.Expression;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.LocalAttribute.Usage.LocalAttributeUsage;
import org.example.Mapping.Interfaces.TwinExpression.LocalExpression.LocalExpression;
import org.example.Mapping.Interfaces.TwinExpression.TwinExpression;
import org.example.Mapping.TwinAttributeMapped.LocalAttributeMapped.LocalAttributeUsageMapped;

/**
 * Represents an assignment action in the model.
 */
public interface Assignment extends Action {
	/**
	 * Returns the target of the assignment, which is a reference to a TwinAttribute.
	 *
	 * @return the target of the assignment
	 */
	Reference<? extends TwinAttributeUsage> getTarget();
	/**
	 * Returns the value to be assigned to the target.
	 *
	 * @return the value to be assigned
	 */
	TwinExpression getValue();
}
