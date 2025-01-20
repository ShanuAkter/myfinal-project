// ExpertServiceImpl.java
package com.shanu.beautySalonManagement.service.impl;

import com.shanu.beautySalonManagement.model.Expert;
import com.shanu.beautySalonManagement.model.OurServices;
import com.shanu.beautySalonManagement.model.User;
import com.shanu.beautySalonManagement.repository.ExpertRepository;
import com.shanu.beautySalonManagement.repository.OurServicesRepository;
import com.shanu.beautySalonManagement.repository.UserRepository;
import com.shanu.beautySalonManagement.response.ExpertDto;
import com.shanu.beautySalonManagement.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExpertServiceImpl implements ExpertService {

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private OurServicesRepository ourServicesRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Expert createExpert(ExpertDto expertDto, MultipartFile profileImage) {
        Expert expert = new Expert();
        expert.setSpeciality(expertDto.getSpeciality());
        expert.setPhone(expertDto.getPhone());
        expert.setExperienceYears(expertDto.getExperienceYears());
        try {
            expert.setProfileImage(profileImage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<OurServices> services = new HashSet<>();
        if (expertDto.getServiceIds() != null) {
            for (Long serviceId : expertDto.getServiceIds()) {
                Optional<OurServices> serviceOptional = ourServicesRepository.findById(serviceId);
                serviceOptional.ifPresent(services::add);
            }
        }
        expert.setOurServices(services);

        Optional<User> userOptional = userRepository.findById(expertDto.getUserId());
        userOptional.ifPresent(expert::setUser);

        expertRepository.save(expert);
        return expert;
    }

    @Override
    public Expert updateExpert(ExpertDto expertDto, long id, MultipartFile profileImage) {
        Optional<Expert> expertOptional = expertRepository.findById(id);
        if (expertOptional.isPresent()) {
            Expert expert = expertOptional.get();
            expert.setSpeciality(expertDto.getSpeciality());
            expert.setPhone(expertDto.getPhone());
            expert.setExperienceYears(expertDto.getExperienceYears());
            try {
                expert.setProfileImage(profileImage.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Set<OurServices> services = new HashSet<>();
            for (Long serviceId : expertDto.getServiceIds()) {
                Optional<OurServices> serviceOptional = ourServicesRepository.findById(serviceId);
                serviceOptional.ifPresent(services::add);
            }
            expert.setOurServices(services);

            Optional<User> userOptional = userRepository.findById(expertDto.getUserId());
            userOptional.ifPresent(expert::setUser);

            expertRepository.save(expert);
        }
        return null;
    }

    @Override
    public void deleteExpert(long id) {
        expertRepository.deleteById(id);
    }

    @Override
    public ExpertDto findExpert(long id) {
        Optional<Expert> expertOptional = expertRepository.findById(id);
        if (expertOptional.isPresent()) {
            Expert expert = expertOptional.get();
            ExpertDto expertDto = new ExpertDto();
            expertDto.setId(expert.getId());
            expertDto.setSpeciality(expert.getSpeciality());
            expertDto.setPhone(expert.getPhone());
            expertDto.setExperienceYears(expert.getExperienceYears());
            expertDto.setServiceIds(expert.getOurServices().stream().map(OurServices::getId).collect(Collectors.toList()));
            if (expert.getUser() != null) {
                expertDto.setUserId(expert.getUser().getId());
            }
            return expertDto;
        }
        return null;
    }

    @Override
    public List<ExpertDto> findAllExperts() {
        List<Expert> experts = expertRepository.findAll();
        return experts.stream().map(expert -> {
            ExpertDto expertDto = new ExpertDto();
            expertDto.setId(expert.getId());
            expertDto.setSpeciality(expert.getSpeciality());
            expertDto.setPhone(expert.getPhone());
            expertDto.setExperienceYears(expert.getExperienceYears());
            expertDto.setServiceIds(expert.getOurServices().stream().map(OurServices::getId).collect(Collectors.toList()));
            if (expert.getUser() != null) {
                expertDto.setUserId(expert.getUser().getId());
            }
            return expertDto;
        }).collect(Collectors.toList());
    }

    @Override
    public Expert findExpertById(long id) {
        return expertRepository.findById(id).orElse(null);
    }

    @Override
    public List<Expert> findExpertsByServiceId(long serviceId) {
        Optional<OurServices> serviceOptional = ourServicesRepository.findById(serviceId);
        if (serviceOptional.isPresent()) {
            OurServices service = serviceOptional.get();
            return expertRepository.findByOurServices(service);
        }
        return null;
    }

    @Override
    public List<Expert> getAllExperts() {
        return expertRepository.findAll();
    }

    @Override
    public List<Expert> getExpertsByServiceId(Long serviceId) {
        Optional<OurServices> serviceOptional = ourServicesRepository.findById(serviceId);
        if (serviceOptional.isPresent()) {
            OurServices service = serviceOptional.get();
            return expertRepository.findByOurServices(service);
        }
        return null;
    }

    @Override
    public Expert addServiceToExpert(Long expertId, Long serviceId) {
        Optional<Expert> expertOptional = expertRepository.findById(expertId);
        Optional<OurServices> serviceOptional = ourServicesRepository.findById(serviceId);
        if (expertOptional.isPresent() && serviceOptional.isPresent()) {
            Expert expert = expertOptional.get();
            OurServices service = serviceOptional.get();
            expert.getOurServices().add(service);
            expertRepository.save(expert);
            return expert;
        }
        return null;
    }

    @Override
    public List<ExpertDto> getAllExpertsAsDto() {
        List<Expert> experts = expertRepository.findAll();
        return experts.stream().map(expert -> {
            ExpertDto expertDto = new ExpertDto();
            expertDto.setId(expert.getId());
            expertDto.setSpeciality(expert.getSpeciality());
            expertDto.setPhone(expert.getPhone());
            expertDto.setExperienceYears(expert.getExperienceYears());
            expertDto.setServiceIds(expert.getOurServices().stream().map(OurServices::getId).collect(Collectors.toList()));
            if (expert.getUser() != null) {
                expertDto.setUserId(expert.getUser().getId());
            }
            return expertDto;
        }).collect(Collectors.toList());
    }
}