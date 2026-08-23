package org.example.Mapping.TwinFunction;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAction.Action;
import org.example.Mapping.Interfaces.TwinFunction.Definition.CustomCalculation;
import org.example.Mapping.Interfaces.TwinAction.Succession;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinAttributeUsageMapped;
import org.example.Mapping.TwinAction.Usage.TwinActionBlockUsage;
import org.example.Mapping.TwinAction.TwinSuccessionAction;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.*;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.CUSTOM_CALCULATION)
@ToString(callSuper = true)
public class CustomCalculationMapped extends FunctionMapped<Function> implements CustomCalculation {


	public CustomCalculationMapped(Function sysmlElement) {
		super(sysmlElement);
	}
}