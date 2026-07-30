package org.example.Mapping.Mapper;

import org.example.Mapping.*;

import java.util.Set;

public class ActuatorMapper extends TwinPortMapper<ActuatorRaw, TwinRaw> {


    @Override
    protected void parse(ActuatorRaw raw) {
        parent.getActuators().add(raw);
    }

    @Override
    protected Class<ActuatorRaw> getRawClass() {
        return ActuatorRaw.class;
    }
}