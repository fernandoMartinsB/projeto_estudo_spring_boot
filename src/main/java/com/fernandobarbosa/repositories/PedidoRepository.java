package com.fernandobarbosa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandobarbosa.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
