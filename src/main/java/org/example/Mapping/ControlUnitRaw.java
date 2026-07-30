package org.example.Mapping;

import lombok.ToString;
import org.omg.sysml.lang.sysml.Type;
@LibraryElement("PhysicalTwinLibrary::ControlUnit")
@ToString(
        callSuper = true)
public class ControlUnitRaw extends Raw{
    public ControlUnitRaw(Type sysmlElement) {
        super(sysmlElement);
    }
}
