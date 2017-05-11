package hu.bme.xj4vjg.petshop.network.pet.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpeciesList {
	@SerializedName("speciesList")
	private List<String> speciesList;

	public SpeciesList() {

	}

	public SpeciesList(List<String> speciesList) {
		this.speciesList = speciesList;
	}

	public List<String> getSpeciesList() {
		return speciesList;
	}

	public void setSpeciesList(List<String> speciesList) {
		this.speciesList = speciesList;
	}
}
