package org.example.Mapping.Mapper;

import org.example.Mapping.AbstractMapper;
import org.example.Mapping.ChildMapper;
import org.example.Mapping.TwinAttributeRaw;

import java.util.Set;

public class TwinCustomAttributeMapper extends ChildMapper<TwinAttributeRaw, TwinAttributeRaw> {
    @Override
    protected void parse(TwinAttributeRaw raw) {
        parent.getTwinAttributes().add(raw);
        raw.parseExpressions();
    }
    @Override
    protected Set<? extends AbstractMapper<?>> getChildMappers() {
        return Set.of();
    }

    @Override
    protected Class<TwinAttributeRaw> getRawClass() {
        return TwinAttributeRaw.class;
    }
}
