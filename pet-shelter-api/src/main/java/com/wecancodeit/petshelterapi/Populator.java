package com.wecancodeit.petshelterapi;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wecancodeit.petshelterapi.models.Cage;
import com.wecancodeit.petshelterapi.models.Pet;
import com.wecancodeit.petshelterapi.repositories.CageRepository;
import com.wecancodeit.petshelterapi.repositories.PetRepository;

@Component
public class Populator implements CommandLineRunner {
	
	@Resource
	private PetRepository petRepo;
	
	@Resource CageRepository cageRepo;

	@Override
	public void run(String... args) throws Exception {
		
		Cage cageA = new Cage("A");
		Cage cageB = new Cage("B");
		Cage cageC = new Cage("C");
		Cage cageD = new Cage("D");
		cageRepo.save(cageA);
		cageRepo.save(cageB);
		cageRepo.save(cageC);
		cageRepo.save(cageD);
		
		Pet puppyCat = new Pet("Puppycat", cageA);
		Pet merv = new Pet("Merv", cageA);
		Pet phideax = new Pet("Phideax", cageB);
		petRepo.save(puppyCat);
		petRepo.save(phideax);
		petRepo.save(merv);
	}

}
