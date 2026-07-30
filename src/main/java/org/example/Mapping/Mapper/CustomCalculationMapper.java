package org.example.Mapping.Mapper;

import org.example.Mapping.AbstractMapper;
import org.example.Mapping.CustomCalculationRaw;
import org.example.Mapping.Raw;
import org.example.Mapping.RootMapper;

import java.util.Set;

public class CustomCalculationMapper extends RootMapper<CustomCalculationRaw> {

    @Override
    protected void parse(CustomCalculationRaw raw) {

    }

    @Override
    protected Set<? extends AbstractMapper<?>> getChildMappers() {
        return Set.of(new TwinAttributeMapperCalcs());
    }

    @Override
    protected Class<CustomCalculationRaw> getRawClass() {
        return CustomCalculationRaw.class;
    }
}
