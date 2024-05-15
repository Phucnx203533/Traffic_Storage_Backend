package aithings.camAI.controlpanel.services.impl;

import aithings.camAI.controlpanel.entity.CameraInforEntity;
import aithings.camAI.controlpanel.repository.CameraInforRepository;
import aithings.camAI.controlpanel.services.CameraInforService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CameraInforServiceImpl implements CameraInforService {

    private final CameraInforRepository cameraInforRepository;




    public CameraInforServiceImpl(CameraInforRepository cameraInforRepository) {
        this.cameraInforRepository = cameraInforRepository;

    }

    @Override
    public CameraInforEntity findById(String id) {
        return cameraInforRepository.findById(id).orElse(null);
    }

}
