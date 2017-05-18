package hu.bme.xj4vjg.petshop.model;

import com.orm.dsl.Table;
import com.orm.dsl.Unique;

@Table
public class Species {
	@Unique
	private String speciesId;
	private String name;

	public Species() {

	}

	public Species(String speciesId, String name) {
		this.speciesId = speciesId;
		this.name = name;
	}

	public Species(String name) {
		this(null, name);
	}

	public String getSpeciesId() {
		return speciesId;
	}

	public void setSpeciesId(String speciesId) {
		this.speciesId = speciesId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Species species) {
		return species.getSpeciesId().equals(speciesId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Species)) {
			return false;
		}
		return equals((Species) obj);
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Id: ").append(speciesId)
				.append(", Name: ").append(name)
				.toString();
	}
}