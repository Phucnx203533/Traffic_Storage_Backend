package aithings.camAI.controlpanel.services.impl;

import aithings.camAI.controlpanel.dto.DeviceProcessDTO;
import aithings.camAI.controlpanel.entity.DeviceProcessEntity;
import aithings.camAI.controlpanel.repository.DeviceProcessRepository;
import aithings.camAI.controlpanel.services.AuthService;
import aithings.camAI.controlpanel.utils.jwt.JwtUtils;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtils jwtUtils;
    private final DeviceProcessRepository deviceProcessRepository;
    public AuthServiceImpl(JwtUtils jwtUtils,DeviceProcessRepository deviceProcessRepository) {
        this.jwtUtils = jwtUtils;
        this.deviceProcessRepository = deviceProcessRepository;
    }

    @Override
    public String getJwtToken(DeviceProcessDTO deviceProcessDTO) {
        try{
            DeviceProcessEntity deviceProcessEntity = deviceProcessRepository.findBySerialAndUsernameAndPassword(deviceProcessDTO.getSerial(), deviceProcessDTO.getUsername(), deviceProcessDTO.getPassword());
            if (deviceProcessEntity == null) {
                return null;
            }else{
                Map<String,Object> claimDevice = new HashMap<>();
                claimDevice.put("serial", deviceProcessDTO.getSerial());
                claimDevice.put("username", deviceProcessDTO.getUsername());
                claimDevice.put("password",deviceProcessDTO.getPassword());
                String jwtToken = jwtUtils.generateJwtToken(claimDevice,deviceProcessDTO.getSerial());
                return jwtToken;
            }
        }catch (Exception e) {
            throw new RuntimeException();
        }

    }
}
