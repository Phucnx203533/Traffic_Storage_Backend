package aithings.camAI.controlpanel.repository;

import aithings.camAI.controlpanel.entity.ViolationInforEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;

public interface ViolationRepository extends MongoRepository<ViolationInforEntity,String> {
    List<ViolationInforEntity> findAll();



    @Query("{$or: [ { 'nameImageViolation' : ?0 }, { 'NameImageLicenseplateViolation' : ?0 }, { 'nameVideoViolation' : ?0 } ]}")
    ViolationInforEntity findByNameImageViolationOrNameImageLicenseplateViolationOrNameVideoViolation(String name);

}
