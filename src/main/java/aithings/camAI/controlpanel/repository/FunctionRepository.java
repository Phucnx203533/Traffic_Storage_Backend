package aithings.camAI.controlpanel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import aithings.camAI.controlpanel.entity.SAFunctionEntity;

import java.util.List;

public interface FunctionRepository extends MongoRepository<SAFunctionEntity, String> {
    List<SAFunctionEntity> getAllByOrderByOrder();

    @Query("{ 'sub_functions.authorities._id': ?0 }")
    SAFunctionEntity getFunctionByCode(String code);

    @Query("{'is_system' : false}")
    List<SAFunctionEntity> getFunctionByRole();
}
