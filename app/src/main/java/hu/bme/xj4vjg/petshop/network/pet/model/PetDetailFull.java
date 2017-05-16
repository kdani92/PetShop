package hu.bme.xj4vjg.petshop.network.pet.model;

import com.google.gson.annotations.SerializedName;

import hu.bme.xj4vjg.petshop.model.Pet;

public class PetDetailFull {
	@SerializedName("id")
	private String id;
	@SerializedName("species")
	private String species;
	@SerializedName("color")
	private String color;
	@SerializedName("timeOfBirth")
	private long timeOfBirth;
	@SerializedName("price")
	private int price;
	@SerializedName("imageUrl")
	private String imageUrl;

	public PetDetailFull() {

	}

	public PetDetailFull(String id, String species, String color, long timeOfBirth, int price, String imageUrl) {
		this.id = id;
		this.species = species;
		this.color = color;
		this.timeOfBirth = timeOfBirth;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public PetDetailFull(Pet pet) {
		this(pet.getPetId(), pet.getSpecies(), pet.getColor(), pet.getTimeOfBirth(), pet.getPrice(), pet.getImageUrl());
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public long getTimeOfBirth() {
		return timeOfBirth;
	}

	public void setTimeOfBirth(long timeOfBirth) {
		this.timeOfBirth = timeOfBirth;
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

	public Pet getPet() {
		return new Pet(id, species, color, timeOfBirth, price, imageUrl);
	}
}
