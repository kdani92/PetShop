package hu.bme.xj4vjg.petshop.model;

import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import hu.bme.xj4vjg.petshop.network.pet.model.NewPetDetail;

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

	public Pet(String petId, NewPetDetail newPetDetail) {
		this(petId, newPetDetail.getSpecies(), newPetDetail.getColor(), newPetDetail.getTimeOfBirth(), newPetDetail.getPrice(), newPetDetail.getImageUrl());
	}

	public String getAge() {
		long monthInMillis = 1000L * 60L * 60L * 24L * 30L;
		long currentTime = Calendar.getInstance().getTimeInMillis();
		if (timeOfBirth <= 0 || timeOfBirth > currentTime) {
			return "";
		}
		double ageInMillis = System.currentTimeMillis() - timeOfBirth;
		double ageInMonths = ageInMillis / monthInMillis;
		double ageInYears = 0;
		while ((int) (ageInMonths / 12.0) >= 1) {
			ageInMonths -= 12.0;
			ageInYears++;
		}

		String age;
		if ((int) ageInYears > 0) {
			if ((int) ageInMonths > 0) {
				age = Long.toString((long) ageInYears) + " years " + Long.toString((long) ageInMonths) + " months";
			} else {
				age = Long.toString((long) ageInYears) + " years";
			}
		} else {
			age = Long.toString((long) ageInMonths) + " months";
		}
		return age;
	}

	public String getPriceFormatted() {
		if (price < 0) {
			return "";
		}
		return Integer.toString(price) + " HUF";
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

	public static long getTimeOfBirthFromAgeInMonths(int ageInMonths) {
		return Calendar.getInstance().getTimeInMillis() - ageInMonths * 30L * 24L * 60L * 60L * 1000L;
	}
}
