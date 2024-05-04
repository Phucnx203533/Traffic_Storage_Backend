package aithings.camAI.controlpanel.repository;

import aithings.camAI.controlpanel.entity.RuleConfigEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleConfigRepository extends MongoRepository<RuleConfigEntity,String> {


}
