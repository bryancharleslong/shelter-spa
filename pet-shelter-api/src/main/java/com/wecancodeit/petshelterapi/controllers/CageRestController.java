package com.wecancodeit.petshelterapi.controllers;

import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecancodeit.petshelterapi.models.Cage;
import com.wecancodeit.petshelterapi.repositories.CageRepository;

@CrossOrigin
@RestController
@RequestMapping("/cages")
public class CageRestController {
	@Resource
	private CageRepository cageRepo;

	@GetMapping("")
	public Collection<Cage> getCages() {
		return (Collection<Cage>) cageRepo.findAllByOrderByCageNameAsc();
	}

	@GetMapping("/{id}")
	public Cage getOneCage(@PathVariable Long id) {
		return cageRepo.findById(id).get();
	}

	@PostMapping("/add-cage")
	public Collection<Cage> addCage() {
		List<Cage> cages =  (List<Cage>) cageRepo.findAllByOrderByCageNameAsc();
		String newCageName = null;
		//look for gaps in alphabetical list of cages
		for(int i = 0; i< cages.size()-1; i++) {
			int cageAscii = cages.get(i).getCageName().charAt(0);
			int nextCageAscii = cages.get(i+1).getCageName().charAt(0);
			if(nextCageAscii - cageAscii > 1) {
				newCageName = new Character((char)(cageAscii + 1)).toString();
			}
		}
		if (newCageName == null) {
			newCageName = Character.toString((char) (cages.size() + 65));
		}
		if(newCageName.charAt(0) < 91) { //A to Z allowed for cage name
			cageRepo.save(new Cage(newCageName));
		}
		return (Collection<Cage>) cageRepo.findAllByOrderByCageNameAsc();
	}

	@PostMapping("/remove-cage")
	public Collection<Cage> removeCage() {
		List<Cage> emptyCages = cageRepo.findByPetsIsNullOrderByCageNameAsc();
		if (emptyCages.size() >= 1) {
			Cage lastEmptyCage = emptyCages.get(emptyCages.size() - 1);
			cageRepo.delete(lastEmptyCage);
		}
		return (Collection<Cage>) cageRepo.findAllByOrderByCageNameAsc();
	}

}
