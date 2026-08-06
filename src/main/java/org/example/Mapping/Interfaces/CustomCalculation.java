package org.example.Mapping.Interfaces;

import java.util.List;

public interface CustomCalculation extends Function {
	List<TwinAttribute> getInputs();

	List<TwinAttribute> getOutputs();

	List<Action> getActions();

	List<Succession> getSuccessions();

}
