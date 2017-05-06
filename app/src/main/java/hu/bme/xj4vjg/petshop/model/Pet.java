package hu.bme.xj4vjg.petshop.model;

public class Pet {
	private String id;
	private String species;
	private String color;
	private long timeOfBirth;
	private int price;
	private String imageUrl;

	public Pet() {

	}

	public Pet(String id, String species, String color, long timeOfBirth, int price, String imageUrl) {
		this.id = id;
		this.species = species;
		this.color = color;
		this.timeOfBirth = timeOfBirth;
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
		return pet.getId().equals(id);
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
				.append("Id: ").append(id)
				.append(", Species: ").append(species)
				.append(", Color: ").append(color)
				.append(", TimeOfBirth: ").append(timeOfBirth)
				.append(", Price: ").append(price)
				.append(", ImageUrl: ").append(imageUrl)
				.toString();
	}
}
