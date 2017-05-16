package hu.bme.xj4vjg.petshop.mock.interceptors;

import android.net.Uri;

import java.util.List;
import java.util.UUID;

import hu.bme.xj4vjg.petshop.model.Pet;
import hu.bme.xj4vjg.petshop.model.Species;
import hu.bme.xj4vjg.petshop.network.NetworkConfig;
import hu.bme.xj4vjg.petshop.network.pet.model.NewPetDetail;
import hu.bme.xj4vjg.petshop.network.pet.model.NewPetResponse;
import hu.bme.xj4vjg.petshop.network.pet.model.PetDetailFull;
import hu.bme.xj4vjg.petshop.network.pet.model.PetDetailSmall;
import hu.bme.xj4vjg.petshop.network.pet.model.PetList;
import hu.bme.xj4vjg.petshop.network.pet.model.SpeciesDetail;
import hu.bme.xj4vjg.petshop.network.pet.model.SpeciesList;
import hu.bme.xj4vjg.petshop.repository.MemoryRepository;
import hu.bme.xj4vjg.petshop.util.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static hu.bme.xj4vjg.petshop.mock.interceptors.MockHelper.ERROR_RESPONSE_CODE;
import static hu.bme.xj4vjg.petshop.mock.interceptors.MockHelper.getBody;
import static hu.bme.xj4vjg.petshop.mock.interceptors.MockHelper.makeResponse;


public class PetMock {
	private static MemoryRepository memoryRepository = new MemoryRepository();

	static {
		memoryRepository.open(null);
	}

	public static Response process(Request request) {
		Uri uri = Uri.parse(request.url().toString());

		String responseString = "";
		int responseCode = ERROR_RESPONSE_CODE;
		Headers headers = request.headers();
		String username = headers.get(NetworkConfig.USERNAME_HEADER);

		if (!isUserRegistered(username)) {
			responseCode = 401;
		} else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "pet/species") && request.method().equals("GET")) {
			responseString = GsonHelper.getGson().toJson(
					new SpeciesList(SpeciesDetail.getSpeciesDetailList(memoryRepository.getSpecies())));
			responseCode = 200;
		} else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "pet/list") && request.method().equals("GET")) {
			responseString = GsonHelper.getGson().toJson(
					new PetList(PetDetailSmall.getPetDetailSmallList(memoryRepository.getPets())));
			responseCode = 200;
		} else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "pet/new") && request.method().equals("POST")) {
			NewPetDetail newPetDetail = getBody(request, NewPetDetail.class);
			if (newPetDetail == null || newPetDetail.getSpecies() == null || newPetDetail.getSpecies().isEmpty()) {
				responseCode = 410;
			} else {
				NewPetResponse newPetResponse = new NewPetResponse(UUID.randomUUID().toString());
				memoryRepository.savePet(new Pet(newPetResponse.getPetId(), newPetDetail));
				boolean newSpecies = true;
				List<Species> speciesList = memoryRepository.getSpecies();
				for (Species species : speciesList) {
					if (species.getName().equals(newPetDetail.getSpecies())) {
						newSpecies = false;
						break;
					}
				}
				if (newSpecies) {
					speciesList.add(new Species(UUID.randomUUID().toString(), newPetDetail.getSpecies()));
				}

				responseString = GsonHelper.getGson().toJson(newPetResponse);
				responseCode = 200;
			}
		} else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "pet/") && request.method().equals("GET")) {
			Pet pet = null;
			for (Pet storedPet : memoryRepository.getPets()) {
				if (uri.getPath().endsWith(storedPet.getPetId())) {
					pet = storedPet;
					break;
				}
			}
			if (pet == null) {
				responseCode = 410;
			} else {
				responseString = GsonHelper.getGson().toJson(
						new PetDetailFull(pet));
				responseCode = 200;
			}
		}

		return makeResponse(request, headers, responseCode, responseString);
	}

	private static boolean isUserRegistered(String username) {
		return username != null && !username.isEmpty() && AuthMock.users.containsKey(username);
	}
}