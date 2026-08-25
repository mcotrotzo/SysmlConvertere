package org.example.Mapping.TwinFunction;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinFunction.Definition.CustomCalculation;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Function;

@MappedElementType(LibraryNameSpaces.CUSTOM_CALCULATION)
@ToString(callSuper = true)
public class CustomCalculationMapped extends FunctionMapped<Function> implements CustomCalculation {


	public CustomCalculationMapped(Function sysmlElement) {
		super(sysmlElement);
	}
}