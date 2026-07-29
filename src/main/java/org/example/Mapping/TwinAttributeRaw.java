package org.example.Mapping;

import lombok.ToString;
import org.omg.sysml.lang.sysml.Type;

@LibraryElement("Base::DataValue")
@ToString(
        callSuper = true)
public class TwinAttributeRaw<T> extends Raw<Type>{
    public TwinAttributeRaw(Type sysmlElement) {
        super(sysmlElement);
    }
    private T value;
}
