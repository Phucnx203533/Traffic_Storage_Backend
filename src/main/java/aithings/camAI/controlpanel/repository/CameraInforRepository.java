package aithings.camAI.controlpanel.repository;

import aithings.camAI.controlpanel.entity.CameraInforEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CameraInforRepository extends MongoRepository<CameraInforEntity,String> {
}
