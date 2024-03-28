package com.polania.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polania.demo.entities.Pago;

public interface PagoRepository extends JpaRepository<Pago, Integer> {

}