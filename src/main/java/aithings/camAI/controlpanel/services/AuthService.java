package aithings.camAI.controlpanel.services;

import aithings.camAI.controlpanel.dto.DeviceProcessDTO;

public interface AuthService {


    String getJwtToken(DeviceProcessDTO deviceProcessDTO);
}
