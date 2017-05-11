package hu.bme.xj4vjg.petshop.interactor.pet.network;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class PetNetworkInteractor {
	@Inject
	EventBus bus;

	public PetNetworkInteractor() {
		injector.inject(this);
	}

	public void getSpecies() {
		// TODO : Call Pet API for list species
	}

	public void getPets() {
		// TODO : Call Pet API for list pets
	}

	public void getPetDetail(String petId) {
		// TODO : Call Pet API for get pet detail
	}

	public void addPet(String species, String color, long timeOfBirth, int price, String imageUrl) {
		// TODO : Call Pet API for add new pet
	}
}
