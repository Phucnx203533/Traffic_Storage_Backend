package aithings.camAI.controlpanel.controller;


import aithings.camAI.controlpanel.dto.DeviceProcessDTO;
import aithings.camAI.controlpanel.entity.SAAuditEntity;
import aithings.camAI.controlpanel.services.AuthService;
import aithings.camAI.controlpanel.utils.auditQueue.AuditProducer;
import aithings.camAI.controlpanel.utils.constant.ActionType;
import aithings.camAI.controlpanel.utils.response.CommonResult;
import aithings.camAI.controlpanel.utils.response.ResCode;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {
    private static final Gson gson = new Gson();
    private final AuthService authService;
    private final AuditProducer auditProducer;
    public AuthController(AuthService authService,AuditProducer auditProducer) {
        this.authService = authService;
        this.auditProducer = auditProducer;
    }

    @PostMapping("/token")
    public CommonResult getJwtToken(@RequestBody DeviceProcessDTO deviceProcessDTO, HttpServletRequest request){
        try {
            String jwtToken = authService.getJwtToken(deviceProcessDTO);
            if(jwtToken != null){
                log.info("Get jwt token from device {} successfully",deviceProcessDTO.toString());
                return CommonResult.success(jwtToken);
            }else{
                log.info("Deivice not exists",deviceProcessDTO);
                return new CommonResult<>(ResCode.DEVICE_NOT_EXIST);
            }
        }catch (Exception e) {
            log.error("Internal server error",e);
            return new CommonResult(ResCode.INTERNAL_ERROR);
        }


    }
}
