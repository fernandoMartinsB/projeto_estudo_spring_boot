package com.fernandobarbosa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandobarbosa.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
