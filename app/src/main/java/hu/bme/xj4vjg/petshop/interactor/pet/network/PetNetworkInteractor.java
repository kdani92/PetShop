package hu.bme.xj4vjg.petshop.interactor.pet.network;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import hu.bme.xj4vjg.petshop.interactor.pet.network.event.AddPetNetworkEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.network.event.GetPetDetailNetworkEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.network.event.GetPetsNetworkEvent;
import hu.bme.xj4vjg.petshop.interactor.pet.network.event.GetSpeciesNetworkEvent;
import hu.bme.xj4vjg.petshop.model.Settings;
import hu.bme.xj4vjg.petshop.network.pet.PetApi;
import hu.bme.xj4vjg.petshop.network.pet.model.NewPetDetail;
import hu.bme.xj4vjg.petshop.network.pet.model.NewPetResponse;
import hu.bme.xj4vjg.petshop.network.pet.model.PetDetailFull;
import hu.bme.xj4vjg.petshop.network.pet.model.PetList;
import hu.bme.xj4vjg.petshop.network.pet.model.SpeciesList;
import retrofit2.Call;
import retrofit2.Response;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class PetNetworkInteractor {
	@Inject
	PetApi petApi;

	@Inject
	Settings settings;

	@Inject
	EventBus bus;

	public PetNetworkInteractor() {
		injector.inject(this);
	}

	public void getSpecies() {
		GetSpeciesNetworkEvent event = new GetSpeciesNetworkEvent();
		Call<SpeciesList> call = petApi.getSpecies(settings.getUsername());
		Response<SpeciesList> response;

		try {
			response = call.execute();

			event.setCode(response.code());
			event.setContent(response.body().getListOfSpecies());
			bus.post(event);
		} catch (Exception e) {
			event.setThrowable(e);
			bus.post(event);
		}
	}

	public void getPets() {
		GetPetsNetworkEvent event = new GetPetsNetworkEvent();
		Call<PetList> call = petApi.getPets(settings.getUsername());
		Response<PetList> response;

		try {
			response = call.execute();

			event.setCode(response.code());
			event.setContent(response.body().getListOfPets());
			bus.post(event);
		} catch (Exception e) {
			event.setThrowable(e);
			bus.post(event);
		}
	}

	public void getPetDetail(String petId) {
		GetPetDetailNetworkEvent event = new GetPetDetailNetworkEvent();
		Call<PetDetailFull> call = petApi.getPetDetail(settings.getUsername(), petId);
		Response<PetDetailFull> response;

		try {
			response = call.execute();

			event.setCode(response.code());
			if (response.code() == 200) {
				event.setContent(response.body().getPet());
			}
			bus.post(event);
		} catch (Exception e) {
			event.setThrowable(e);
			bus.post(event);
		}
	}

	public void addPet(String species, String color, long timeOfBirth, int price, String imageUrl) {
		AddPetNetworkEvent event = new AddPetNetworkEvent();
		Call<NewPetResponse> call = petApi.addPet(
				settings.getUsername(),
				new NewPetDetail(species, color, timeOfBirth, price, imageUrl));
		Response<NewPetResponse> response;

		try {
			response = call.execute();

			event.setCode(response.code());
			if (response.code() == 200) {
				event.setContent(response.body().getPetId());
			}
			bus.post(event);
		} catch (Exception e) {
			event.setThrowable(e);
			bus.post(event);
		}
	}
}
