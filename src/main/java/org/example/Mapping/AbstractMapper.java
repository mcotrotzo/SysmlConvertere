package org.example.Mapping;

import org.example.Utils;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.LibraryPackage;

import java.util.Set;

public abstract class AbstractMapper<T extends Raw> {

    protected final RawRegistry twinRawFactory = RawRegistry.getInstance();
    protected final Utils utils = Utils.getInstance();

    protected Set<T> raws;

    public final Set<T> map() {
        raws = getRaws();

        for (T raw : raws) {
            parse(raw);
            for (AbstractMapper<?> childMapper : getChildMappers()) {
                if (childMapper instanceof ChildMapper child) {
                    child.setParent(raw);
                }
                childMapper.map();
            }
        }
        return raws;
    }

    public boolean isFromLibrary(Element element) {
        Element current = element;
        while (current != null) {
            if (current.getOwningNamespace() instanceof LibraryPackage) {
                return true;
            }
            current = current.getOwningNamespace();
        }
        return false;
    }

    protected abstract Set<T> getRaws();

    protected abstract void parse(T raw);

    protected abstract Set<? extends AbstractMapper<?>> getChildMappers();

    protected abstract Class<T> getRawClass();
}