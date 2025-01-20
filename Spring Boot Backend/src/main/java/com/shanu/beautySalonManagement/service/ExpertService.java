// ExpertService.java
package com.shanu.beautySalonManagement.service;

import com.shanu.beautySalonManagement.model.Expert;
import com.shanu.beautySalonManagement.response.ExpertDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExpertService {
    public Expert createExpert(ExpertDto expertDto, MultipartFile profileImage);
    public Expert updateExpert(ExpertDto expertDto, long id, MultipartFile profileImage);
    public void deleteExpert(long id);
    public ExpertDto findExpert(long id);
    public List<ExpertDto> findAllExperts();
    public Expert findExpertById(long id);
    public List<Expert> findExpertsByServiceId(long serviceId);
    public List<Expert> getAllExperts();
    // get experts by service id
    public List<Expert> getExpertsByServiceId(Long serviceId);
    // add service to expert
    public Expert addServiceToExpert(Long expertId, Long serviceId);
    public List<ExpertDto> getAllExpertsAsDto();

}