package hu.bme.xj4vjg.petshop.network.pet;

import hu.bme.xj4vjg.petshop.network.NetworkConfig;
import hu.bme.xj4vjg.petshop.network.pet.model.NewPetDetail;
import hu.bme.xj4vjg.petshop.network.pet.model.NewPetResponse;
import hu.bme.xj4vjg.petshop.network.pet.model.PetDetailFull;
import hu.bme.xj4vjg.petshop.network.pet.model.PetList;
import hu.bme.xj4vjg.petshop.network.pet.model.SpeciesList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PetApi {
	@GET("pet/species")
	Call<SpeciesList> getSpecies(@Header(NetworkConfig.USERNAME_HEADER) String username);

	@GET("pet/list")
	Call<PetList> getPets(@Header(NetworkConfig.USERNAME_HEADER) String username);

	@GET("pet/{petId}")
	Call<PetDetailFull> getPetDetail(@Header(NetworkConfig.USERNAME_HEADER) String username, @Path("petId") String petId);

	@POST("pet/new")
	Call<NewPetResponse> addPet(@Header(NetworkConfig.USERNAME_HEADER) String username, @Body NewPetDetail newPetDetail);
}
