package hu.bme.xj4vjg.petshop.network.pet.model;

import com.google.gson.annotations.SerializedName;

public class NewPetDetail {
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

	public NewPetDetail() {

	}

	public NewPetDetail(String species, String color, long timeOfBirth, int price, String imageUrl) {
		this.species = species;
		this.color = color;
		this.timeOfBirth = timeOfBirth;
		this.price = price;
		this.imageUrl = imageUrl;
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
}
