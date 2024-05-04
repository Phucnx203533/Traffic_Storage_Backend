package aithings.camAI.controlpanel.services;

import aithings.camAI.controlpanel.entity.RuleConfigEntity;

public interface RuleConfigService {

    RuleConfigEntity getRuleConfigSettingFromCamera(String cameraId);
}
