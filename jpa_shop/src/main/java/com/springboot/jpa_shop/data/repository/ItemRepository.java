package com.springboot.jpa_shop.data.repository;

import com.springboot.jpa_shop.data.entity.item.Item;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {
	
	    @PersistenceContext
	    EntityManager em;
	
	    public void save(Item item) {
	        if (item.getId() == null) {
	            em.persist(item);
	        } else {
	            em.merge(item);
	        }
	    }
	
	    public Item findOne(Long id) {
	        return em.find(Item.class, id);
	    }
	
	    public List<Item> findAll() {
	        return em.createQuery("select i from Item i", Item.class).getResultList();
	    }
}
