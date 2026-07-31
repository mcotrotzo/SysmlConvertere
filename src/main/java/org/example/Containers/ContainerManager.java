package org.example.Containers;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import io.github.classgraph.ClassInfo;
import org.example.Mapping.LibraryElement;
import org.example.Mapping.Mapper.TwinExpression.TwinExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpressionAnnotation;
import org.example.Mapping.Raw;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.util.SysMLLibraryUtil;
import org.omg.sysml.util.TypeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContainerManager {

    private static ContainerManager instance;

    private final Map<String, List<Class<?>>> containers = new HashMap<>();
    private final Utils utils = Utils.getInstance();
    private ContainerManager() {
        scan();
    }

    public static ContainerManager getInstance() {
        if (instance == null) {
            instance = new ContainerManager();
        }
        return instance;
    }

    private void scan() {
        try (ScanResult scanResult = new ClassGraph()
                .acceptPackages("org.example")
                .enableAllInfo()
                .scan()) {


            for (var annotationInfo : scanResult.getAllAnnotations()) {
                String annotationName = annotationInfo.getName();

                for (ClassInfo classInfo : scanResult.getClassesWithAnnotation(annotationName)) {
                    if (classInfo.isInterface()) {
                        continue;
                    }
                    if(classInfo.isAbstract()){
                        continue;
                    }
                    containers.computeIfAbsent(annotationName, k -> new ArrayList<>())
                            .add(classInfo.loadClass());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Class<?>> getClassesWithAnnotation(Class<?> annotationClass) {
        return containers.getOrDefault(annotationClass.getName(), new ArrayList<>());
    }

    public List<Class<Raw>> getRawClasses() {
        List<Class<Raw>> list = containers.getOrDefault(LibraryElement.class.getName(), new ArrayList<>()).stream().map(x -> (Class<Raw>) x).collect(Collectors.toList());
        sortRawByHierarchy(list);
        return list;
    }

    public List<Class<TwinExpression<?>>> getTwinExpressionClasses() {
        List<Class<TwinExpression<?>>> list = containers.getOrDefault(TwinExpressionAnnotation.class.getName(), new ArrayList<>()).stream().map(x -> (Class<TwinExpression<?>>) x).collect(Collectors.toList());
        sortByHierarchy(list);
        return list;
    }
    public void sortByHierarchy(List<Class<TwinExpression<?>>> list) {
        Map<Class<TwinExpression<?>>, Integer> specificity = new HashMap<>();
        for (Class<TwinExpression<?>> c : list) {
            Class<?> ct = c.getAnnotation(TwinExpressionAnnotation.class).value();
            int score = 0;
            for (Class<TwinExpression<?>> other : list) {
                if (other == c) continue;
                Class<?> ot = other.getAnnotation(TwinExpressionAnnotation.class).value();
                if (ot.isAssignableFrom(ct) && !ot.equals(ct)) {
                    score++;
                }
            }
            specificity.put(c, score);
        }

        list.sort((c1, c2) -> specificity.get(c2) - specificity.get(c1));
    }

    public void sortRawByHierarchy(List<Class<Raw>>  list) {
        list.sort((c1, c2) -> {
            Type t1 = SysMLLibraryUtil.getLibraryType(utils.getRootElement(), c1.getAnnotation(LibraryElement.class).value());
            Type t2 = SysMLLibraryUtil.getLibraryType(utils.getRootElement(), c2.getAnnotation(LibraryElement.class).value());
            if (TypeUtil.specializes(t2, t1)) return -1;
            if (TypeUtil.specializes(t1, t2)) return 1;
            return 0;
        });
    }
}