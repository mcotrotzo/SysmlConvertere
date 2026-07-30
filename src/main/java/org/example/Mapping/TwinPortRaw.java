package org.example.Mapping;

import lombok.Getter;
import lombok.ToString;

import org.omg.sysml.lang.sysml.Type;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString(
        callSuper = true)
@LibraryElement("PhysicalTwinLibrary::TwinPort")
@Getter
public class TwinPortRaw extends Raw{
    public TwinPortRaw(Type sysmlElement) {
        super(sysmlElement);
    }

    private Set<TwinAttributeRaw> twinAttributes = new HashSet<>();

}
