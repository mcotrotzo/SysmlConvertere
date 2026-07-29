package org.example.Mapping;

import lombok.ToString;
import org.omg.sysml.lang.sysml.Type;
@LibraryElement("ScalarValues::Real")
@ToString(
        callSuper = true)
public class TwinRealRaw extends TwinAttributeRaw<Double> {
    public TwinRealRaw(Type sysmlElement) {
        super(sysmlElement);
    }


}
