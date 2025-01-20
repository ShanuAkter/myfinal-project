package com.shanu.beautySalonManagement.controller;

import com.shanu.beautySalonManagement.model.OurServices;
import com.shanu.beautySalonManagement.request.OurServiceCreateRequest;
import com.shanu.beautySalonManagement.response.OurServiceDto;
import com.shanu.beautySalonManagement.service.OurServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/services")
@CrossOrigin("*")
public class OurServicesController {

    @Autowired
    private OurServicesService ourServicesService;

    @PostMapping("/create")
    public ResponseEntity<OurServices> createService(@ModelAttribute OurServiceCreateRequest request, @RequestParam("serviceImage") MultipartFile serviceImage) {
        OurServices ourServices = ourServicesService.createService(request, serviceImage);
        return new  ResponseEntity<>(ourServices, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OurServices> updateService(@ModelAttribute OurServiceCreateRequest request, @PathVariable long id, @RequestParam("serviceImage") MultipartFile serviceImage) {
        OurServices ourServices = ourServicesService.updateService(request, id, serviceImage);
        return new ResponseEntity<>(ourServices, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteService(@PathVariable long id) {
        ourServicesService.deleteService(id);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<OurServices> findService(@PathVariable long id) {
        OurServices ourServices = ourServicesService.findService(id);
        if (ourServices != null) {
            return ResponseEntity.ok(ourServices);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<OurServices>> findAllServices() {
        return ResponseEntity.ok(ourServicesService.findAllServices());
    }

    @GetMapping("/all/dto")
    public ResponseEntity<List<OurServiceDto>> findAllServicesDto() {
        return ResponseEntity.ok(ourServicesService.findAllServicesAsDto());
    }
}