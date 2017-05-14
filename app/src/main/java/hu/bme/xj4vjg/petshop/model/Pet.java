package hu.bme.xj4vjg.petshop.model;

import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

@Table
public class Pet {
	@Unique
	private String petId;
	private String species;
	private String color;
	private long timeOfBirth;
	private int price;
	private String imageUrl;

	public Pet() {

	}

	public Pet(String petId, String species, String color, long timeOfBirth, int price, String imageUrl) {
		this.petId = petId;
		this.species = species;
		this.color = color;
		this.timeOfBirth = timeOfBirth;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public Pet(String species, String color, long timeOfBirth, int price, String imageUrl) {
		this(null, species, color, timeOfBirth, price, imageUrl);
	}

	public String getPriceFormatted() {
		return Integer.toString(price);
	}

	public String getPetId() {
		return petId;
	}

	public void setPetId(String petId) {
		this.petId = petId;
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

	public boolean equals(Pet pet) {
		return pet.getPetId().equals(petId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Pet)) {
			return false;
		}
		return equals((Pet) obj);
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Id: ").append(petId)
				.append(", Species: ").append(species)
				.append(", Color: ").append(color)
				.append(", TimeOfBirth: ").append(timeOfBirth)
				.append(", Price: ").append(price)
				.append(", ImageUrl: ").append(imageUrl)
				.toString();
	}
}
