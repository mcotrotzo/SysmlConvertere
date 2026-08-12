package org.example.Mapping.Interfaces;

import org.example.Mapping.NewVersion.Abstract.MappedReference;
/**
 * Represents an assignment action in the model.
 */
public interface Assignment extends Action {
	/**
	 * Returns the target of the assignment, which is a reference to a TwinAttribute.
	 *
	 * @return the target of the assignment
	 */
	Reference<? extends TwinAttribute> getTarget();
	/**
	 * Returns the value to be assigned to the target.
	 *
	 * @return the value to be assigned
	 */
	Expression getValue();
}
