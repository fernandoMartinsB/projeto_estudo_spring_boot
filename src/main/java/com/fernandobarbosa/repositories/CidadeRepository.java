package com.fernandobarbosa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandobarbosa.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
