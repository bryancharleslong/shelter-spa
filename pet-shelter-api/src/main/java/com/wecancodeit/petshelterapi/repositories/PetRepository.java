package com.wecancodeit.petshelterapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.wecancodeit.petshelterapi.models.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {

}
