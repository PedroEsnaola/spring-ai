package com.example.semantickernelspring.domain.repository;

import com.example.semantickernelspring.domain.model.User;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    public Optional<User> findFirstByUsername(String username);


}
