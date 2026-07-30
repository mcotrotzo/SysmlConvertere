package org.example.Mapping.Mapper;

import org.example.Mapping.AbstractMapper;
import org.example.Mapping.ChildMapper;
import org.example.Mapping.QueryRaw;
import org.example.Mapping.TwinRaw;

import java.util.Set;

public class QueryMapper extends ChildMapper<QueryRaw, TwinRaw> {
    @Override
    protected void parse(QueryRaw raw) {
        parent.getQueries().add(raw);
    }

    @Override
    protected Set<? extends AbstractMapper<?>> getChildMappers() {
        return Set.of(new TwinAttributeMapperQuery());
    }

    @Override
    protected Class<QueryRaw> getRawClass() {
        return QueryRaw.class;
    }
}
