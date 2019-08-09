package com.wecancodeit.petshelterapi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wecancodeit.petshelterapi.models.Cage;


public interface CageRepository extends CrudRepository<Cage, Long> {
	
	List<Cage> findByPetsIsNullOrderByCageNameAsc();

	List<Cage> findAllByOrderByCageNameAsc();

}
