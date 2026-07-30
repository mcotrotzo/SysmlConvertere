package org.example.Mapping;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.omg.sysml.lang.sysml.Type;

import java.util.HashSet;
import java.util.Set;

@LibraryElement("TwinImp::CustomCalculationAction")
@Getter
@ToString(callSuper = true)
public class CustomCalculationRaw extends Raw{
    private Set<TwinAttributeRaw> attributes =new HashSet<>();

    public CustomCalculationRaw(Type sysmlElement) {
        super(sysmlElement);
    }
}
