package com.example.demo.repository;

import com.example.demo.entity.ClientExpediteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientExpediteurRepository extends JpaRepository<ClientExpediteur, Long> {
}
