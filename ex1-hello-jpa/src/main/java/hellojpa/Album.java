package main.java.hellojpa;

import javax.persistence.*;

@Entity
@DiscriminatorValue("A")
public class Album extends Item {
	private String artist;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	
}
