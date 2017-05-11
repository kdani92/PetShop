package hu.bme.xj4vjg.petshop.network.pet.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import hu.bme.xj4vjg.petshop.model.Species;

public class SpeciesList {
	@SerializedName("speciesList")
	private List<SpeciesDetail> speciesList;

	public SpeciesList() {

	}

	public SpeciesList(List<SpeciesDetail> speciesList) {
		this.speciesList = speciesList;
	}

	public List<SpeciesDetail> getSpeciesList() {
		return speciesList;
	}

	public void setSpeciesList(List<SpeciesDetail> speciesList) {
		this.speciesList = speciesList;
	}

	public List<Species> getListOfSpecies() {
		List<Species> species = new ArrayList<>();
		for (SpeciesDetail speciesDetail : speciesList) {
			species.add(new Species(speciesDetail.getId(), speciesDetail.getName()));
		}
		return species;
	}
}
