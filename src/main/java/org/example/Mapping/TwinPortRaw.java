package org.example.Mapping;

import lombok.ToString;

import org.omg.sysml.lang.sysml.Type;

import java.util.List;
import java.util.Set;

@ToString(
        callSuper = true)
@LibraryElement("PhysicalTwinLibrary::TwinPort")
public class TwinPortRaw extends Raw<Type>{
    public TwinPortRaw(Type sysmlElement) {
        super(sysmlElement);
    }

    Set<TwinAttributeRaw<?>> twinAttributes;

}
