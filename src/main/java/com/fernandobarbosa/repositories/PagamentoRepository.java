package com.fernandobarbosa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandobarbosa.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
