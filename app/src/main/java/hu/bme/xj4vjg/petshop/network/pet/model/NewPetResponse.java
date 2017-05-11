package hu.bme.xj4vjg.petshop.network.pet.model;

import com.google.gson.annotations.SerializedName;

public class NewPetResponse {
	@SerializedName("petId")
	private String petId;

	public NewPetResponse() {

	}

	public NewPetResponse(String petId) {
		this.petId = petId;
	}

	public String getPetId() {
		return petId;
	}

	public void setPetId(String petId) {
		this.petId = petId;
	}
}
