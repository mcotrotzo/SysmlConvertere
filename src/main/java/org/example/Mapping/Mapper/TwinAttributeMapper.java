package org.example.Mapping.Mapper;

import org.eclipse.uml2.uml.LiteralReal;
import org.example.Mapping.*;
import org.example.Mapping.Mapper.TwinExpression.TwinExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionFactory;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.util.TypeUtil;


import java.util.HashSet;
import java.util.Set;

public class TwinAttributeMapper extends ChildMapper<TwinAttributeRaw, TwinPortRaw> {
    @Override
    protected void parse(TwinAttributeRaw raw) {
        parent.getTwinAttributes().add(raw);
        raw.parseExpressions();
    }
    @Override
    protected Set<? extends AbstractMapper<?>> getChildMappers() {
        return Set.of(new TwinCustomAttributeMapper());
    }

    @Override
    protected Class<TwinAttributeRaw> getRawClass() {
        return TwinAttributeRaw.class;
    }
}

class TwinAttributeMapperCalcs extends ChildMapper<TwinAttributeRaw, CustomCalculationRaw> {
    @Override
    protected void parse(TwinAttributeRaw raw) {
        parent.getAttributes().add(raw);
        raw.parseExpressions();
    }
    @Override
    protected Set<? extends AbstractMapper<?>> getChildMappers() {
        return Set.of(new TwinCustomAttributeMapper());
    }

    @Override
    protected Class<TwinAttributeRaw> getRawClass() {
        return TwinAttributeRaw.class;
    }
}
