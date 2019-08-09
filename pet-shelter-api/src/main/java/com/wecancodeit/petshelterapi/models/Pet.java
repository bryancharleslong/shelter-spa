package com.wecancodeit.petshelterapi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pet {
	@Id
	@GeneratedValue
	private Long id;

	private String petName;

	@ManyToOne
	@JsonIgnore
	private Cage cage;

	public Long getId() {
		return id;
	}

	public String getPetName() {
		return petName;
	}
	
	public Cage getCage() {
		return cage;
	}

	protected Pet() {
	}

	public Pet(String petName) {
		this.petName = petName;
	}

	public Pet(String petName, Cage cage) {
		this.petName = petName;
		this.cage = cage;
	}

	@Override
	public String toString() {
		return "Pet [petName=" + petName + "]";
	}

}
