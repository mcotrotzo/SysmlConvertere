package org.example.Mapping.Model.Expression;

import Mapper.Mapper;
import Model.Annotation.MappedMetaClass;
import Model.EmptyCore;
import Model.Predefined.MetaClasses.Expression.FeatureReferenceUsage;
import org.omg.sysml.lang.sysml.FeatureReferenceExpression;

@MappedMetaClass(value = FeatureReferenceExpression.class, core = EmptyCore.class)
public class TwinFeatureReferenceUsage extends FeatureReferenceUsage {
	public TwinFeatureReferenceUsage(FeatureReferenceExpression sysmlElement, Mapper mapper) {
		super(sysmlElement, mapper);
	}
}
