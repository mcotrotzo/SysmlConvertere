package org.example.Mapping.Mapper;

import org.example.Mapping.*;

import java.util.Set;

public abstract class TwinPortMapper<T extends TwinPortRaw,P extends TwinRaw> extends ChildMapper<T, P> {
    @Override
    protected abstract void parse(T raw);

    @Override
    protected Set<? extends AbstractMapper<?>> getChildMappers() {
        return Set.of(new TwinAttributeMapper());
    }

    @Override
    protected Class<T> getRawClass() {
        return (Class<T>) TwinPortRaw.class;
    }
}
