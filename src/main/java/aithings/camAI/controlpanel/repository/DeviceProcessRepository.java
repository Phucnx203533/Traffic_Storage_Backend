package aithings.camAI.controlpanel.repository;


import aithings.camAI.controlpanel.dto.DeviceProcessDTO;
import aithings.camAI.controlpanel.entity.DeviceProcessEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceProcessRepository extends MongoRepository<DeviceProcessEntity,String> {

    DeviceProcessEntity findBySerialAndUsernameAndPassword(String serial, String username,String password);

    DeviceProcessEntity findBySerial(String serial);
}
