package run.ouo.app.dao.mongo;

import run.ouo.app.model.entity.mongo.LogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<LogEntity, Long> {
}
