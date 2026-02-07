package com.springboot.jpa_model.data.entity.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("B")
public class Book extends Item {

	private String author;
    private String isbn;

    // Setter
	public Book(String name, int price, int stockQuantity, String author, String isbn) {
		super(name, price, stockQuantity);
		
		this.author = author;
		this.isbn = isbn;
	}
	
    @Override
    public String toString() {
        return "Album{" +
                "artist='" + author + '\'' +
                ", etc='" + isbn + '\'' +
                '}';
    }
}
