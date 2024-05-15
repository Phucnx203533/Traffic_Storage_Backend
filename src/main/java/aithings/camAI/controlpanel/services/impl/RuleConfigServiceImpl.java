package aithings.camAI.controlpanel.services.impl;

import aithings.camAI.controlpanel.entity.LineEntity;
import aithings.camAI.controlpanel.entity.RuleConfigEntity;
import aithings.camAI.controlpanel.entity.TrafficLightEntity;
import aithings.camAI.controlpanel.repository.RuleConfigRepository;
import aithings.camAI.controlpanel.services.RuleConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class RuleConfigServiceImpl implements RuleConfigService {

    private final RuleConfigRepository ruleConfigRepository;
    public RuleConfigServiceImpl(RuleConfigRepository ruleConfigRepository) {
        this.ruleConfigRepository = ruleConfigRepository;
    }


    @Override
    public RuleConfigEntity getRuleConfigSettingFromCamera(String cameraId) {
        Optional<RuleConfigEntity> optionalRuleConfigEntity = ruleConfigRepository.findById(cameraId);
        if (optionalRuleConfigEntity.isPresent()) {
            RuleConfigEntity ruleConfigEntity = optionalRuleConfigEntity.get();
            ruleConfigEntity.getLineEntities().forEach(entity ->{
                entity.setDirect(entity.getDirect()>0?1:entity.getDirect()<0?-1:0);
            });
            return ruleConfigEntity;
        }
        return null;
    }
}
