package com.springboot.jpa_model.data.entity.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("M")
public class Movie extends Item {

    private String director;
    private String actor;

    // Setter
	public Movie(String name, int price, int stockQuantity, String director, String actor) {
		super(name, price, stockQuantity);
		
		this.director = director;
		this.actor = actor;
	}
	
    @Override
    public String toString() {
        return "Movie{" +
                "director='" + director + '\'' +
                ", actor='" + actor + '\'' +
                '}';
    }
}
