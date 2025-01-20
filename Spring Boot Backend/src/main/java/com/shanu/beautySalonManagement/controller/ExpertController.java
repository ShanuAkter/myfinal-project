package com.shanu.beautySalonManagement.controller;

import com.shanu.beautySalonManagement.model.Expert;
import com.shanu.beautySalonManagement.response.ExpertDto;
import com.shanu.beautySalonManagement.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/experts")
@CrossOrigin("*")
public class ExpertController {

    @Autowired
    private ExpertService expertService;

    @PostMapping("/create")
    public ResponseEntity<Expert> createExpert(@ModelAttribute ExpertDto expertDto, @RequestParam("profileImage") MultipartFile profileImage) {
        Expert expert = expertService.createExpert(expertDto, profileImage);
        return new ResponseEntity<>(expert, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Expert> updateExpert(@ModelAttribute ExpertDto expertDto, @PathVariable long id, @RequestParam("profileImage") MultipartFile profileImage) {
        Expert expert = expertService.updateExpert(expertDto, id, profileImage);
        return new ResponseEntity<>(expert, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteExpert(@PathVariable long id) {
        expertService.deleteExpert(id);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ExpertDto> findExpert(@PathVariable long id) {
        ExpertDto expertDto = expertService.findExpert(id);
        if (expertDto != null) {
            return ResponseEntity.ok(expertDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Expert>> getAllExperts() {
        List<Expert> experts = expertService.getAllExperts();
        return ResponseEntity.ok(experts);
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<Expert>> getExpertsByServiceId(@PathVariable Long serviceId) {
        return ResponseEntity.ok(expertService.getExpertsByServiceId(serviceId));
    }

    @PutMapping("/addService/{expertId}/{serviceId}")
    public ResponseEntity<Expert> addServiceToExpert(@PathVariable Long expertId, @PathVariable Long serviceId) {
        Expert expert = expertService.addServiceToExpert(expertId, serviceId);
        return ResponseEntity.ok(expert);
    }

    @GetMapping("/all/dto")
    public ResponseEntity<List<ExpertDto>> getAllExpertsAsDto() {
        List<ExpertDto> experts = expertService.getAllExpertsAsDto();
        return ResponseEntity.ok(experts);
    }
}