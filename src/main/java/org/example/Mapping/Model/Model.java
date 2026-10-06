package org.example.Mapping.Model;

import org.omg.sysml.lang.sysml.Type;


public abstract class Model<T extends Type> extends AbstractModel<T> {


	public Model(T sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}


}
