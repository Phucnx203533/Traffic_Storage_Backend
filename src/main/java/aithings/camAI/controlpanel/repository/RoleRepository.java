package aithings.camAI.controlpanel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import aithings.camAI.controlpanel.entity.SARoleEntity;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends MongoRepository<SARoleEntity, String> {

    SARoleEntity findByName(String name);

    Optional<SARoleEntity> findSARoleByIdAndStatus(String id, Integer status);
    List<SARoleEntity> findAll();
    List<SARoleEntity> findAllByOrderByName();
}
