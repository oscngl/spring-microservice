package com.oscngl.subscription.service.impl;

import com.oscngl.subscription.model.DatabaseSequence;
import com.oscngl.subscription.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {

    private final MongoOperations mongoOperations;

    @Override
    public long generateSequence(String sequence) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(sequence)),
                new Update().inc("sequence",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSequence() : 1;
    }

}
