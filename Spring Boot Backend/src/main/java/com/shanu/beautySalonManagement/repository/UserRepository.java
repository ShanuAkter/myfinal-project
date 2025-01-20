package com.shanu.beautySalonManagement.repository;

import com.shanu.beautySalonManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM Expert e WHERE e.user.id = :userId")
    void deleteExpertsByUserId(Long userId);
}