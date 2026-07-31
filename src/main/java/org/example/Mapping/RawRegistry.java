package org.example.Mapping;


import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.util.SysMLLibraryUtil;
import org.omg.sysml.util.TypeUtil;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RawRegistry {

    private static RawRegistry instance;

    public static RawRegistry getInstance() {
        if (instance == null) instance = new RawRegistry();
        return instance;
    }

    private final Utils utils = Utils.getInstance();


    public <T extends Raw> Set<T> getRaw(Class<T> rawClass, Function<Raw, Boolean> condition) {
        return getRaw(utils.collect(Type.class), rawClass, condition);
    }

    public <T extends Raw> Set<T> getRaw(Collection<Type> elements, Class<T> rawClass, Function<Raw, Boolean> condition) {
        var annotation = rawClass.getAnnotation(LibraryElement.class);
        if (annotation == null) {
            throw new RuntimeException("Missing @LibraryElement annotation on " + rawClass.getSimpleName());
        }
        Type libraryType = SysMLLibraryUtil.getLibraryType(utils.getRootElement(), annotation.value());
        if (libraryType == null) {
            throw new RuntimeException("Library type not found for annotation value: " + annotation.value());
        }

        return elements.stream()
                .filter(utils::isDefinitionOrUsage)
                .filter(x -> TypeUtil.isCompatible(x, libraryType))
                .map(x -> (T) construct(rawClass, x))
                .filter(x -> condition.apply(x))
                .collect(Collectors.toSet());
    }


    public <T extends Raw> Raw construct(Class<T> rawClass, Type type) {
        try {
            Constructor<?> constructor = rawClass.getConstructor(Type.class);
            return (Raw) constructor.newInstance(type);
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate " + rawClass.getSimpleName(), e);
        }
    }

}