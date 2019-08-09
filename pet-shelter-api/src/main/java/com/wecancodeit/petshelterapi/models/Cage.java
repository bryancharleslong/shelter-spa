package com.wecancodeit.petshelterapi.models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cage {
	@Id
	@GeneratedValue
	private Long id;

	private String cageName;

	@OneToMany(mappedBy = "cage")
	private Collection<Pet> pets;

	public Long getId() {
		return id;
	}

	public String getCageName() {
		return cageName;
	}

	public Collection<Pet> getPets() {
		return pets;
	}

	protected Cage() {
	}

	public Cage(String cageName) {
		this.cageName = cageName;
	}

	@Override
	public String toString() {
		return "Cage [cageName=" + cageName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cageName == null) ? 0 : cageName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cage other = (Cage) obj;
		if (cageName == null) {
			if (other.cageName != null)
				return false;
		} else if (!cageName.equals(other.cageName))
			return false;
		return true;
	}
	
}
