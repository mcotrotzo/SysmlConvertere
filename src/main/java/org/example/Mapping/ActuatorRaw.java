package org.example.Mapping;

import lombok.ToString;
import org.omg.sysml.lang.sysml.Type;
@LibraryElement("PhysicalTwinLibrary::Actuator")
@ToString(
        callSuper = true)
public class ActuatorRaw extends TwinPortRaw{
    public ActuatorRaw(Type sysmlElement) {
        super(sysmlElement);
    }
}
