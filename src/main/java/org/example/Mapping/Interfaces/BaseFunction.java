package org.example.Mapping.Interfaces;

import org.example.Mapping.NewVersion.BaseFunctionKind;
/**
 * Represents a base function in the model.
 * Base functions are build in functions of the sysml standard library
 * All functions which are allowed you will find in the BaseFunctionKind enum
 */
public interface BaseFunction extends Function {
	BaseFunctionKind getFunctionKind();
}
