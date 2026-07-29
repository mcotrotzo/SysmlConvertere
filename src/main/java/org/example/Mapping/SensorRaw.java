package org.example.Mapping;


import lombok.ToString;
import org.example.GenerelRules.LibraryElements;
import org.omg.sysml.lang.sysml.Type;

import java.util.HashSet;

@LibraryElement("PhysicalTwinLibrary::Sensor")
@ToString(
        callSuper = true)
public class SensorRaw extends TwinPortRaw {
    public SensorRaw(Type sysmlElement) {
        super(sysmlElement);
    }

}