package org.example.Mapping;

import java.util.Set;

public abstract class RootMapper<T extends Raw> extends AbstractMapper<T> {
    @Override
    protected Set<T> getRaws() {
        return twinRawFactory.getRaw(getRawClass(), x -> true);
    }
}
