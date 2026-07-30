package org.example.Mapping.Mapper;

import org.example.Mapping.AbstractMapper;
import org.example.Mapping.RootMapper;
import org.example.Mapping.TwinRaw;

import java.util.Set;

public class TwinMapper extends RootMapper<TwinRaw> {


    @Override
    protected void parse(TwinRaw raw) {

    }

    @Override
    protected Set<AbstractMapper<?>> getChildMappers() {
        return Set.of(new SensorPortMapper(),
                      new ActuatorMapper(),
                      new TwinAttributeMapperTwin(),
                      new ControlUnitMappet(),
                      new QueryMapper()
                );
    }

    @Override
    protected Class<TwinRaw> getRawClass() {
        return TwinRaw.class;
    }
}
