package com.project.crud_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.crud_spring.entity.mahasiswa;

public interface mahasiswaRepository extends JpaRepository<mahasiswa, Long> {

}
