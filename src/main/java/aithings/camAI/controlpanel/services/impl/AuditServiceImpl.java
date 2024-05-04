package aithings.camAI.controlpanel.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import aithings.camAI.controlpanel.dto.form.AuditForm;
import aithings.camAI.controlpanel.entity.SAAuditEntity;
import aithings.camAI.controlpanel.entity.SAUserEntity;
import aithings.camAI.controlpanel.services.AuditService;
import aithings.camAI.controlpanel.utils.CriteriaUtils;
import aithings.camAI.controlpanel.utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static aithings.camAI.controlpanel.utils.constant.DocumentConstants.*;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserRepository userRepository;

    @Override
    public Page<SAAuditEntity> search(AuditForm form, Pageable pageable) {
        Query query = new Query();
        if (!StringUtils.isEmpty(form.getUserName())) {
            query.addCriteria(Criteria.where(USER_NAME).is(form.getUserName()));
        }
        if(!StringUtils.isEmpty(form.getFunctionCode())){
            query.addCriteria(Criteria.where(FUNCTION_CODE).is(form.getFunctionCode()));
        }
        if (!StringUtils.isEmpty(form.getActionType())) {
            query.addCriteria(Criteria.where(ACTION_TYPE).regex(Pattern.compile(form.getActionType(), Pattern.CASE_INSENSITIVE)));
        }
        if (StringUtils.isEmpty(form.getFromDate())) {
            if (StringUtils.isEmpty(form.getToDate())) {
                form.setToDate(DateTimeUtils.convertDateToString(new Date()));
            }
            LocalDateTime toDate = DateTimeUtils.convertStringToDate(form.getToDate()).withHour(23).withMinute(59).withSecond(59);
            query.addCriteria(Criteria.where(CREATED_AT).lte(toDate));
        } else {
            query.addCriteria(CriteriaUtils.criteriaRangeTime(form.getFromDate(), form.getToDate(), CREATED_AT));
        }
        long total = mongoTemplate.count(query, SAAuditEntity.class);
        query.with(Sort.by(Sort.Direction.DESC, CREATED_AT));
        List<SAAuditEntity> list = mongoTemplate.find(query.with(pageable), SAAuditEntity.class);
        return new PageImpl<>(list, pageable, total);
    }

    @Override
    public List<SAUserEntity> getListUsers() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, ID));
        return mongoTemplate.find(query, SAUserEntity.class);
    }
}
