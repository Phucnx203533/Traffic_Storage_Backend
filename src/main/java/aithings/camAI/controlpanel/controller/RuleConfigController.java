package aithings.camAI.controlpanel.controller;

import aithings.camAI.controlpanel.entity.RuleConfigEntity;
import aithings.camAI.controlpanel.services.RuleConfigService;
import aithings.camAI.controlpanel.utils.response.CommonResult;
import aithings.camAI.controlpanel.utils.response.ResCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/rule-config")
public class RuleConfigController {


    private final RuleConfigService ruleConfigService;


    public RuleConfigController(RuleConfigService ruleConfigService) {
        this.ruleConfigService = ruleConfigService;
    }


    @GetMapping("/{camera}")
    public CommonResult getRuleConfigSettingsFromCamera(@PathVariable String camera){
        try {
            RuleConfigEntity result = ruleConfigService.getRuleConfigSettingFromCamera(camera);
            if (result != null){
                log.info("Get rule config setting of camera {} successfully",camera);
                return CommonResult.success(result);
            }
            log.info("Get rule config setting of camera {} not exist",camera);
            return new CommonResult(ResCode.CAMERA_NOT_EXIST);
        }catch (Exception e){
            log.error("Error while get rule config : {}",e.getMessage());
            return CommonResult.serverError();
        }
    }
}
