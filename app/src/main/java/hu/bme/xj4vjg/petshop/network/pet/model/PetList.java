package hu.bme.xj4vjg.petshop.network.pet.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
}
