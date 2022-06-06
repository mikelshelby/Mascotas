package com.lm2a.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name = "Mascota_Order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date placedAt;

//	@NotBlank(message = "Name is required")
//	private String name;
//
//	@NotBlank(message = "Street is required")
//	private String street;
//
//	@NotBlank(message = "City is required")
//	private String city;
//
//	@NotBlank(message = "State is required")
//	private String state;
//
//	@NotBlank(message = "Zip code is required")
//	private String zip;

	@NotBlank(message = "Name is required")
	private String petName;

	@NotBlank(message = "Birth date is required")
	private Date petBirth;

	@NotBlank(message = "Breed is required")
	private String petBreed;

	@NotBlank(message = "Weight is required")
	private float petWeight;

	@NotBlank(message = "Chip status is required")
	private boolean petChip;
	
	@ManyToMany(targetEntity=Mascota.class)
	private List<Mascota> mascotas = new ArrayList<>();
	
	public void addDesign(Mascota design) {
		this.mascotas.add(design);
	}
	
	@PrePersist
	void placedAt() {
		this.placedAt=new Date();
	}
}
