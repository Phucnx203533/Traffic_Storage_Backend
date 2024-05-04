package aithings.camAI.controlpanel.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import aithings.camAI.controlpanel.dto.form.AuditForm;
import aithings.camAI.controlpanel.entity.SAAuditEntity;
import aithings.camAI.controlpanel.entity.SAUserEntity;

import java.util.List;

public interface AuditService {

    Page<SAAuditEntity> search(AuditForm form, Pageable pageable);

    List<SAUserEntity> getListUsers();
}
