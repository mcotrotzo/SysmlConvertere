package org.example.Mapping.NewVersion;

import org.example.Mapping.TwinAction.MappedMetaclass;
import org.omg.sysml.lang.sysml.Function;

@MappedMetaclass
public class BaseFunction extends FunctionMapped<Function> implements org.example.Mapping.Interfaces.BaseFunction {

	private BaseFunctionKind functionKind;

	public BaseFunction(org.omg.sysml.lang.sysml.Function sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

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
