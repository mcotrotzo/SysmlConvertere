package org.example.Mapping.Mapper;

import org.example.Mapping.*;

import java.util.Set;

public class SensorPortMapper extends TwinPortMapper<SensorRaw, TwinRaw> {

    @Override
    protected void parse(SensorRaw raw) {
        parent.getSensors().add(raw);
    }

    @Override
    protected Class<SensorRaw> getRawClass() {
        return SensorRaw.class;
    }
}
