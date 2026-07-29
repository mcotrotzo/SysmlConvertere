package org.example.Mapping;

import org.example.GenerelRules.LibraryElements;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.util.TypeUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.ToString;


@LibraryElement("TwinDefLibrary::Twin")
@ToString(
        callSuper = true)
public class TwinRaw extends Raw<Type> {

    public TwinRaw(Type sysmlElement) {
        super(sysmlElement);
    }

    private Set<SensorRaw> sensors = new HashSet<>();
    private Set<ControlUnitRaw> controlUnits = new HashSet<>();
    private Set<ActuatorRaw> actuators = new HashSet<>();


    public void parse(Set<SensorRaw> sensorRaws, Set<ActuatorRaw> actuatorRaws, Set<ControlUnitRaw> controlUnitRaws){
        sensors = sensorRaws;
        actuators = actuatorRaws;
        controlUnits = controlUnitRaws;
    }

}