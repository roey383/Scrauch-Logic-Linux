package logic;

import java.awt.Image;

public class PlayerPersonalInfo {

	private final long id;
	private String name;
	private Image profil;
	
	public PlayerPersonalInfo(long id, String name, Image profil) {
		super();
		this.id = id;
		this.name = name;
		this.profil = profil;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Image getProfil() {
		return profil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((profil == null) ? 0 : profil.hashCode());
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
		PlayerPersonalInfo other = (PlayerPersonalInfo) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (profil == null) {
			if (other.profil != null)
				return false;
		} else if (!profil.equals(other.profil))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlayerPersonalInfo [id=" + id + ", name=" + name + ", profil=" + profil.hashCode()%1000 + "]";
	}
	
	
	
}
