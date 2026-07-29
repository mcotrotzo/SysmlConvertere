package org.example.Mapping;

import jakarta.inject.Inject;
import org.example.Utils;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.util.SysMLLibraryUtil;
import org.omg.sysml.util.*;


import javax.xml.stream.events.Namespace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TwinMapper {



    private final RawRegistry rawRegistry = RawRegistry.getInstance();





    public void map(){
        Set<TwinRaw> twins = rawRegistry.getRaw(TwinRaw.class, x -> true);
        for (TwinRaw twin : twins) {
            parseTwin(twin);
            System.out.println(twin);
        }
    }

    public void parseTwin(TwinRaw twinRaw) {

        Set<SensorRaw> sensorRaws = new HashSet<>();
        Set<ActuatorRaw> actuatorRaws = new HashSet<>();
        Set<ControlUnitRaw> controlUnitRaws = new HashSet<>();

        sensorRaws = rawRegistry.getRaw(SensorRaw.class, x->Utils.getInstance().isOwningType(x.getSysmlElement(), twinRaw.getSysmlElement()));
        controlUnitRaws = rawRegistry.getRaw(ControlUnitRaw.class, x->Utils.getInstance().isOwningType(x.getSysmlElement(), twinRaw.getSysmlElement()));
        actuatorRaws = rawRegistry.getRaw(ActuatorRaw.class, x->Utils.getInstance().isOwningType(x.getSysmlElement(), twinRaw.getSysmlElement()));
        twinRaw.parse(sensorRaws, actuatorRaws, controlUnitRaws);

        parseTwinPorts(sensorRaws);
        parseTwinPorts(actuatorRaws);
    }

    public void parseTwinPorts(Set<? extends TwinPortRaw> twinPortRaw) {
        for (TwinPortRaw twinPort : twinPortRaw) {
            parseTwinPorts(twinPort);
        }
    }
    public void parseTwinPorts(TwinPortRaw twinPortRaw) {
        Set<TwinAttributeRaw<?>> twinAttributeRaws = rawRegistry.getRaw(TwinAttributeRaw.class, x -> Utils.getInstance().isOwningType(x.getSysmlElement(), twinPortRaw.getSysmlElement()));
        twinPortRaw.twinAttributes=twinAttributeRaws;
    }
}
