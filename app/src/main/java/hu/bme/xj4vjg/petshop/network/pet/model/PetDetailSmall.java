package hu.bme.xj4vjg.petshop.network.pet.model;

import com.google.gson.annotations.SerializedName;

public class PetDetailSmall {
	@SerializedName("id")
	private String id;
	@SerializedName("species")
	private String species;
	@SerializedName("price")
	private int price;
	@SerializedName("imageUrl")
	private String imageUrl;

	public PetDetailSmall() {

	}

	public PetDetailSmall(String id, String species, int price, String imageUrl) {
		this.id = id;
		this.species = species;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
