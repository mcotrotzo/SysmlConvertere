package org.example.Mapping.TwinFunction;

import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunctionKind;
import org.example.Mapping.NewVersion.Abstract.MappedElement;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAction.Annotation.MappedMetaclass;
import org.omg.sysml.lang.sysml.CalculationDefinition;
import org.omg.sysml.lang.sysml.Function;

@MappedMetaclass
public class BaseFunction extends MappedElement<Function, Definition> implements org.example.Mapping.Interfaces.TwinFunction.Definition.BaseFunction {

	private BaseFunctionKind functionKind;

	public BaseFunction(org.omg.sysml.lang.sysml.Function sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		try {
			functionKind = BaseFunctionKind.fromSymbol(getSysmlElement().getName());
		} catch (IllegalArgumentException e) {
			throw new MappingException(e.getMessage());
		}
	}

	@Override
	public BaseFunctionKind getFunctionKind() {
		return functionKind;
	}


}
