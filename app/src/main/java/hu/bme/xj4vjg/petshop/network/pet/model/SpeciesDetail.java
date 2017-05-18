package hu.bme.xj4vjg.petshop.network.pet.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import hu.bme.xj4vjg.petshop.model.Species;

public class SpeciesDetail {
	@SerializedName("id")
	private String id;
	@SerializedName("name")
	private String name;

	public SpeciesDetail() {

	}

	public SpeciesDetail(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static List<Species> getSpeciesList(List<SpeciesDetail> speciesDetailList) {
		List<Species> speciesList = new ArrayList<>();
		for (SpeciesDetail speciesDetail : speciesDetailList) {
			speciesList.add(new Species(speciesDetail.getId(), speciesDetail.getName()));
		}
		return speciesList;
	}

	public static List<SpeciesDetail> getSpeciesDetailList(List<Species> speciesList) {
		List<SpeciesDetail> speciesDetailList = new ArrayList<>();
		for (Species species : speciesList) {
			speciesDetailList.add(new SpeciesDetail(species.getSpeciesId(), species.getName()));
		}
		return speciesDetailList;
	}
}
