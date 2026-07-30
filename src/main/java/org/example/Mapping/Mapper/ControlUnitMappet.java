package org.example.Mapping.Mapper;

import org.example.Mapping.AbstractMapper;
import org.example.Mapping.ChildMapper;
import org.example.Mapping.ControlUnitRaw;
import org.example.Mapping.TwinRaw;

import java.util.Set;

public class ControlUnitMappet extends ChildMapper<ControlUnitRaw, TwinRaw> {
    @Override
    protected void parse(ControlUnitRaw raw) {
        parent.getControlUnits().add(raw);
    }

    @Override
    protected Set<? extends AbstractMapper<?>> getChildMappers() {
        return Set.of();
    }

    @Override
    protected Class<ControlUnitRaw> getRawClass() {
        return ControlUnitRaw.class;
    }
}
