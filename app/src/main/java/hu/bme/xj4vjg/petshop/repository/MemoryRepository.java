package hu.bme.xj4vjg.petshop.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hu.bme.xj4vjg.petshop.model.Pet;

public class MemoryRepository implements Repository {
	private List<Pet> pets;

	@Override
	public void open(Context context) {
		pets = getDummyPets();
	}

	@Override
	public void close() {
		pets.clear();
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

	public static List<Pet> getDummyPets() {
		List<Pet> dummyPets = new ArrayList<>();
		long yearInMillis = 1000 * 60 * 60 * 24 * 365;
		long dummyTimeOfBirth = Calendar.getInstance().getTimeInMillis();
		String dummyImageUrl = "http://dummyurl.com";

		dummyPets.add(new Pet("pet1", "rabbit", "gray", dummyTimeOfBirth - yearInMillis * 4, 4500, dummyImageUrl));
		dummyPets.add(new Pet("pet2", "cat", "black", dummyTimeOfBirth - yearInMillis * 2, 9000, dummyImageUrl));
		dummyPets.add(new Pet("pet3", "dog", "white", dummyTimeOfBirth - yearInMillis * 6, 2000, dummyImageUrl));
		dummyPets.add(new Pet("pet4", "guinea pig", "gray", dummyTimeOfBirth - yearInMillis * 4, 2500, dummyImageUrl));
		dummyPets.add(new Pet("pet5", "cat", "gray", dummyTimeOfBirth - yearInMillis * 3, 7000, dummyImageUrl));
		dummyPets.add(new Pet("pet6", "snake", "white", dummyTimeOfBirth - yearInMillis * 2, 15000, dummyImageUrl));
		dummyPets.add(new Pet("pet7", "rat", "black", dummyTimeOfBirth - yearInMillis * 1, 12000, dummyImageUrl));
		dummyPets.add(new Pet("pet8", "guinea pig", "gray", dummyTimeOfBirth - yearInMillis * 1, 2000, dummyImageUrl));
		dummyPets.add(new Pet("pet9", "rat", "white", dummyTimeOfBirth - yearInMillis * 3, 1000, dummyImageUrl));
		dummyPets.add(new Pet("pet10", "guinea pig", "black", dummyTimeOfBirth - yearInMillis * 2, 6500, dummyImageUrl));

		return dummyPets;
	}
}
