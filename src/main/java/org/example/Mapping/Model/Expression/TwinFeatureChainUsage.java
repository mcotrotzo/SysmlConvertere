package org.example.Mapping.Model.Expression;

import Mapper.Mapper;
import Model.Annotation.MappedMetaClass;
import Model.EmptyCore;
import Model.Predefined.MetaClasses.Expression.FeatureChainUsage;
import Model.Usage;
import org.example.Mapping.Model.Attribute.TwinAttributeUsage;
import org.omg.sysml.lang.sysml.FeatureChainExpression;

@MappedMetaClass(value = FeatureChainExpression.class, core = EmptyCore.class)
public class TwinFeatureChainUsage extends FeatureChainUsage {
	public TwinFeatureChainUsage(FeatureChainExpression sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}


}
