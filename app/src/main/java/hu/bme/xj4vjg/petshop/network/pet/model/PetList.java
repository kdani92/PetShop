package hu.bme.xj4vjg.petshop.network.pet.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import hu.bme.xj4vjg.petshop.model.Pet;

public class PetList {
	@SerializedName("petList")
	private List<PetDetailSmall> petList;

	public PetList() {

	}

	public PetList(List<PetDetailSmall> petList) {
		this.petList = petList;
	}

	public List<PetDetailSmall> getPetList() {
		return petList;
	}

	public void setPetList(List<PetDetailSmall> petList) {
		this.petList = petList;
	}

	public List<Pet> getListOfPets() {
		List<Pet> pets = new ArrayList<>();
		for (PetDetailSmall petDetailSmall : petList) {
			Pet pet = new Pet();
			pet.setPetId(petDetailSmall.getId());
			pet.setSpecies(petDetailSmall.getSpecies());
			pet.setPrice(petDetailSmall.getPrice());
			pet.setImageUrl(petDetailSmall.getImageUrl());
			pets.add(pet);
		}
		return pets;
	}
}
