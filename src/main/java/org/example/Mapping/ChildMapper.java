package org.example.Mapping;

import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Type;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ChildMapper<T extends Raw,P extends Raw> extends AbstractMapper<T> {
    protected P parent;

    public void setParent(P parent) {
        this.parent = parent;
    }

    @Override
    protected Set<T> getRaws() {
        if (parent == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " needs a parent Type to retrieve its child elements.");
        }
        Set<Type> features = parent.getSysmlElement().getFeature().stream()
                .filter(x ->!isFromLibrary(x))
                .collect(Collectors.toSet());
        return twinRawFactory.getRaw(features, getRawClass(), x -> true);
    }
}
