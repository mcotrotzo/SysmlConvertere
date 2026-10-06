package org.example.Mapping.Model.Function;

import lombok.Getter;
import org.example.Mapping.BaseFunctionKind;
import org.example.Mapping.Model.Action.ActionBlockCore;
import org.example.Mapping.Model.AbstractModel;
import org.example.Mapping.Model.Mapper;
import org.omg.sysml.lang.sysml.Type;


public class FunctionCore extends ActionBlockCore {

	@Getter
	private BaseFunctionKind functionKind;

	public FunctionCore(Type sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}

	@Override
	public void fillSlots(AbstractModel owner) {
		functionKind = BaseFunctionKind.fromSymbol(sysmlElement.getName());
	}
}
