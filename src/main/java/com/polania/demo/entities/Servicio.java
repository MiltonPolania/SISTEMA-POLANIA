package com.polania.demo.entities;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Servicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // Formato de fecha y hora
	private LocalDateTime fechaHora;
	private Integer turno;
	private String vehiculo;
	private String nombreCliente;
	private Integer cantidad;
	private String pago1;
	private String pago2;
	private boolean entregado;
	private String estado;
	private Double precio1;
	private Double precio2;
	private boolean flauta;
	private Integer pulso;
	private Integer chavetas;
	private String listo;
	private String diagnostico;
	private boolean tiempo;
	private String demora;

}
