
package aithings.camAI.controlpanel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import aithings.camAI.controlpanel.entity.SAAuditEntity;

public interface AuditRepository extends MongoRepository<SAAuditEntity, String> {
}
