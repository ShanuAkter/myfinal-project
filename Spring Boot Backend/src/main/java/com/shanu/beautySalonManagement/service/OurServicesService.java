package com.shanu.beautySalonManagement.service;

import com.shanu.beautySalonManagement.model.OurServices;
import com.shanu.beautySalonManagement.request.OurServiceCreateRequest;
import com.shanu.beautySalonManagement.response.OurServiceDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OurServicesService {

    public OurServices createService(OurServiceCreateRequest request, MultipartFile serviceImage);

    public OurServices updateService(OurServiceCreateRequest request, long id, MultipartFile serviceImage);

    public void deleteService(long id);

    public OurServices findService(long id);

    public List<OurServices> findAllServices();

    public List<OurServiceDto> findAllServicesAsDto();
}
