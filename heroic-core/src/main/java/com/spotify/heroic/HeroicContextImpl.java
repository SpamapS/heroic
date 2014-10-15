package com.spotify.heroic;

import javax.inject.Inject;
import javax.inject.Named;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spotify.heroic.aggregation.Aggregation;
import com.spotify.heroic.aggregation.AggregationSerializer;
import com.spotify.heroic.aggregation.model.AggregationQuery;

public class HeroicContextImpl implements HeroicContext {
    @Inject
    @Named("application/json")
    private ObjectMapper mapper;

    @Inject
    private AggregationSerializer aggregationSerializer;

    @Override
    public <T extends Aggregation, R extends AggregationQuery> void aggregation(short id, Class<T> type,
            Class<R> queryType, AggregationSerializer.Serializer<T> serializer) {
        mapper.registerSubtypes(type, queryType);
        aggregationSerializer.register(type, id, serializer);
    }
}