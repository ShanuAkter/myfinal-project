package com.shanu.beautySalonManagement.service.impl;

import com.shanu.beautySalonManagement.model.OurServices;
import com.shanu.beautySalonManagement.repository.OurServicesRepository;
import com.shanu.beautySalonManagement.request.OurServiceCreateRequest;
import com.shanu.beautySalonManagement.response.OurServiceDto;
import com.shanu.beautySalonManagement.service.OurServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OurServiceServiceImpl implements OurServicesService {

    @Autowired
    private OurServicesRepository ourServicesRepository;

    @Override
    public OurServices createService(OurServiceCreateRequest request, MultipartFile serviceImage) {
        OurServices ourServices = new OurServices();
        ourServices.setName(request.getName());
        ourServices.setDescription(request.getDescription());
        ourServices.setPrice(request.getPrice());
        try {
            ourServices.setServiceImage(serviceImage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ourServicesRepository.save(ourServices);
        return ourServices;
    }

    @Override
    public OurServices updateService(OurServiceCreateRequest request, long id, MultipartFile serviceImage) {
        Optional<OurServices> ourServices = ourServicesRepository.findById(id);
        if (ourServices.isPresent()) {
            OurServices ourService = ourServices.get();
            ourService.setName(request.getName());
            ourService.setDescription(request.getDescription());
            ourService.setPrice(request.getPrice());
            try {
                ourService.setServiceImage(serviceImage.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ourServicesRepository.save(ourService);
        }
        return null;
    }

    @Override
    public void deleteService(long id) {
        ourServicesRepository.deleteById(id);
    }

    @Override
    public OurServices findService(long id) {
        Optional<OurServices> ourServices = ourServicesRepository.findById(id);
        return ourServices.orElse(null);
    }

    @Override
    public List<OurServices> findAllServices() {
        return ourServicesRepository.findAll();
    }

    @Override
    public List<OurServiceDto> findAllServicesAsDto() {
        List<OurServices> services = ourServicesRepository.findAll();
        return services.stream().map(service -> {
            OurServiceDto dto = new OurServiceDto();
            dto.setId(service.getId());
            dto.setName(service.getName());
            dto.setDescription(service.getDescription());
            dto.setPrice(service.getPrice());
            dto.setServiceImage(service.getServiceImage());
            return dto;
        }).collect(Collectors.toList());
    }


}