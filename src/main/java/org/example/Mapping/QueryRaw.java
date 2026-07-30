package org.example.Mapping;

import lombok.Getter;
import lombok.ToString;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@LibraryElement("DescriptiveModelLibrary::QueryHistory")
@ToString(
        callSuper = true)
@Getter
public class QueryRaw extends Raw{
    private Set<TwinAttributeRaw> queryAttributes = new HashSet<>();
    public QueryRaw(Type sysmlElement) {
        super(sysmlElement);
    }
}
