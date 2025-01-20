package com.shanu.beautySalonManagement.repository;

import com.shanu.beautySalonManagement.model.Expert;
import com.shanu.beautySalonManagement.model.OurServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpertRepository extends JpaRepository<Expert,Long> {
    List<Expert> findByOurServices(OurServices service);
}
