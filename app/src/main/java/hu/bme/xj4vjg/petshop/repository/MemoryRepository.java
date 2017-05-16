package hu.bme.xj4vjg.petshop.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hu.bme.xj4vjg.petshop.model.Pet;
import hu.bme.xj4vjg.petshop.model.Species;

public class MemoryRepository implements Repository {
	private List<Species> speciesList;
	private List<Pet> pets;

	@Override
	public void open(Context context) {
		speciesList = getDummySpeciesList();
		pets = getDummyPets();
	}

	@Override
	public void close() {
		pets.clear();
	}

	@Override
	public List<Species> getSpecies() {
		return speciesList;
	}

	@Override
	public void updateSpecies(List<Species> speciesList) {
		for (Species species : speciesList) {
			boolean storedSpecies = false;
			for (Species oldSpecies : this.speciesList) {
				if (species.equals(oldSpecies)) {
					storedSpecies = true;
					oldSpecies.setName(species.getName());
					break;
				}
			}
			if (!storedSpecies) {
				this.speciesList.add(species);
			}
		}
	}

	@Override
	public List<Pet> getPets() {
		return pets;
	}

	@Override
	public void updatePets(List<Pet> pets) {
		for (Pet pet : pets) {
			savePet(pet);
		}
	}

	@Override
	public Pet getPet(String petId) {
		for (Pet pet : pets) {
			if (pet.getPetId().equals(petId)) {
				return pet;
			}
		}
		return null;
	}

	@Override
	public void savePet(Pet pet) {
		if (containsPet(pet.getPetId())) {
			Pet oldPet = getPet(pet.getPetId());
			oldPet.setSpecies(pet.getSpecies());
			oldPet.setColor(pet.getColor());
			oldPet.setTimeOfBirth(pet.getTimeOfBirth());
			oldPet.setPrice(pet.getPrice());
			oldPet.setImageUrl(pet.getImageUrl());
		} else {
			pets.add(pet);
		}
	}

	@Override
	public void removePet(String petId) {
		if (containsPet(petId)) {
			pets.remove(getPet(petId));
		}
	}

	@Override
	public boolean containsPet(String petId) {
		for (Pet pet : pets) {
			if (pet.getPetId().equals(petId)) {
				return true;
			}
		}
		return false;
	}

	public static List<Species> getDummySpeciesList() {
		List<Species> dummySpeciesList = new ArrayList<>();
		dummySpeciesList.add(new Species("species1", "Rabbit"));
		dummySpeciesList.add(new Species("species2", "Guinea pig"));
		dummySpeciesList.add(new Species("species3", "Cat"));
		dummySpeciesList.add(new Species("species4", "Dog"));
		dummySpeciesList.add(new Species("species5", "Snake"));
		dummySpeciesList.add(new Species("species6", "Rat"));

		return dummySpeciesList;
	}

	public static List<Pet> getDummyPets() {
		List<Pet> dummyPets = new ArrayList<>();
		long yearInMillis = 1000L * 60L * 60L * 24L * 365L;
		long dummyTimeOfBirth = Calendar.getInstance().getTimeInMillis();
		String dummyImageUrl = "http://notexistingrandomxyzurl.com";

		dummyPets.add(new Pet("pet1", "Dog", "black", dummyTimeOfBirth - yearInMillis / 2, 4500, "https://cdn.pixabay.com/photo/2014/03/05/19/23/dog-280332_960_720.jpg"));
		dummyPets.add(new Pet("pet2", "Cat", "red", dummyTimeOfBirth - yearInMillis * 2, 9000, "https://s-media-cache-ak0.pinimg.com/736x/07/c3/45/07c345d0eca11d0bc97c894751ba1b46.jpg"));
		dummyPets.add(new Pet("pet3", "Rabbit", "white", dummyTimeOfBirth - yearInMillis * 6, 2000, "https://fellowshipofminds.files.wordpress.com/2011/05/bunny3.jpg"));

		dummyPets.add(new Pet("pet4", "Guinea pig", "gray", dummyTimeOfBirth - yearInMillis * 4, 2500, dummyImageUrl));
		dummyPets.add(new Pet("pet5", "Cat", "gray", dummyTimeOfBirth - yearInMillis * 3, 7000, null));
		dummyPets.add(new Pet("pet6", "Snake", "white", dummyTimeOfBirth - yearInMillis * 2, 15000, dummyImageUrl));
		dummyPets.add(new Pet("pet7", "Rat", "black", dummyTimeOfBirth - yearInMillis * 1, 12000, null));
		dummyPets.add(new Pet("pet8", "Guinea pig", "gray", dummyTimeOfBirth - yearInMillis * 1, 2000, null));
		dummyPets.add(new Pet("pet9", "Rat", "white", dummyTimeOfBirth - yearInMillis * 3, 1000, dummyImageUrl));
		dummyPets.add(new Pet("pet10", "Guinea pig", "black", dummyTimeOfBirth - yearInMillis * 2, 6500, dummyImageUrl));

		return dummyPets;
	}
}
