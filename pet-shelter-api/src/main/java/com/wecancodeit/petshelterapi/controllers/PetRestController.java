package com.wecancodeit.petshelterapi.controllers;

import java.util.Collection;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecancodeit.petshelterapi.models.Cage;
import com.wecancodeit.petshelterapi.models.Pet;
import com.wecancodeit.petshelterapi.repositories.CageRepository;
import com.wecancodeit.petshelterapi.repositories.PetRepository;

@CrossOrigin
@RestController
@RequestMapping("/pets")
public class PetRestController {
	@Resource
	private PetRepository petRepo;
	@Resource
	private CageRepository cageRepo;

	@GetMapping("")
	public Collection<Pet> getPets() {
		return (Collection<Pet>) petRepo.findAll();
	}

	@GetMapping("/{id}")
	public Pet getOnePet(@PathVariable Long id) {
		return petRepo.findById(id).get();
	}

	@PostMapping("/add-pet")
	public Collection<Pet> addPet(@RequestBody String body) throws JSONException {
		JSONObject newPet = new JSONObject(body);
		String petName = newPet.getString("petName");
		Long cageId = newPet.getLong("cageId");
		Cage cage = cageRepo.findById(cageId).get();
		petRepo.save(new Pet(petName, cage));
		return (Collection<Pet>) petRepo.findAll();
	}

}
