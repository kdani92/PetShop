package hu.bme.xj4vjg.petshop.interactor.pet.repository;


import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.event.ContainsPetRepositoryEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.event.GetPetRepositoryEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.event.GetPetsRepositoryEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.event.GetSpeciesRepositoryEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.event.RemovePetRepositoryEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.event.SavePetRepositoryEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.event.UpdatePetsRepositoryEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.repository.event.UpdateSpeciesRepositoryEvent;
import hu.bme.xj4vjg.petshop.model.Pet;
import hu.bme.xj4vjg.petshop.model.Species;
import hu.bme.xj4vjg.petshop.repository.Repository;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class PetRepositoryInteractor {
	@Inject
	EventBus bus;
	@Inject
	Repository repository;

	public PetRepositoryInteractor() {
		injector.inject(this);
	}

	public void getSpecies() {
		GetSpeciesRepositoryEvent event = new GetSpeciesRepositoryEvent();
		try {
			event.setContent(repository.getSpecies());
			bus.post(event);
		} catch (Exception e) {
			event.setThrowable(e);
			bus.post(event);
		}
	}

	public void updateSpecies(List<Species> species) {
		UpdateSpeciesRepositoryEvent event = new UpdateSpeciesRepositoryEvent();
		try {
			repository.updateSpecies(species);
			bus.post(event);
		} catch (Exception e) {
			event.setThrowable(e);
			bus.post(event);
		}
	}

	public void getPets() {
		GetPetsRepositoryEvent event = new GetPetsRepositoryEvent();
		try {
			event.setContent(repository.getPets());
			bus.post(event);
		} catch (Exception e) {
			event.setThrowable(e);
			bus.post(event);
		}
	}

	public void updatePets(List<Pet> pets) {
		UpdatePetsRepositoryEvent event = new UpdatePetsRepositoryEvent();
		try {
			repository.updatePets(pets);
			bus.post(event);
		} catch (Exception e) {
			event.setThrowable(e);
			bus.post(event);
		}
	}

	public void getPet(String petId) {
		GetPetRepositoryEvent event = new GetPetRepositoryEvent();
		try {
			event.setContent(repository.getPet(petId));
			bus.post(event);
		} catch (Exception e) {
			event.setThrowable(e);
			bus.post(event);
		}
	}

	public void savePet(Pet pet) {
		SavePetRepositoryEvent event = new SavePetRepositoryEvent();
		try {
			repository.savePet(pet);
			bus.post(event);
		} catch (Exception e) {
			event.setThrowable(e);
			bus.post(event);
		}
	}

	public void removePet(String petId) {
		RemovePetRepositoryEvent event = new RemovePetRepositoryEvent();
		try {
			repository.removePet(petId);
			bus.post(event);
		} catch (Exception e) {
			event.setThrowable(e);
			bus.post(event);
		}
	}

	public void containsPet(String petId) {
		ContainsPetRepositoryEvent event = new ContainsPetRepositoryEvent();
		try {
			event.setContent(repository.containsPet(petId));
			bus.post(event);
		} catch (Exception e) {
			event.setThrowable(e);
			bus.post(event);
		}
	}
}