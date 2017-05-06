package hu.bme.xj4vjg.petshop.repository;


import android.content.Context;

import java.util.List;

import hu.bme.xj4vjg.petshop.model.Pet;

public interface Repository {
	void open(Context context);

	void close();

	List<Pet> getPets();

	void updatePets(List<Pet> pets);

	Pet getPet(String petId);

	void savePet(Pet pet);

	void removePet(String petId);

	boolean containsPet(String petId);
}
