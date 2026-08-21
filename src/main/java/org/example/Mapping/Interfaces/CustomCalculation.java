package org.example.Mapping.Interfaces;

import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;

import java.util.List;
/**
 * Represents a custom calculation(Definition) in the model, which is a specialized function with defined inputs, outputs, actions, and successions.
 */
public interface CustomCalculation extends Function {
	List<TwinAttributeUsage> getInputs();

	List<TwinAttributeUsage> getOutputs();

	/**
	 * Returns a list of actions of the body of the custom calculation..
	 * The order is from top to bottom
	 * @return a list of actions
	 */
	List<Action> getActions();
	/**
	 * Returns a list of successions of the body of the custom calculation.
	 *
	 * @return a list of successions
	 */
	List<Succession> getSuccessions();

}
