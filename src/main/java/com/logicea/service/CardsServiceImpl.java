package com.logicea.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.logicea.model.Card;
import com.logicea.repository.CardRepository;

@Service
@Transactional
public class CardsServiceImpl implements CardsService {

	@Autowired
	private CardRepository cardRepository;
	
	@Override
	public List<Card> getAll() {
		return cardRepository.findAll();
	}

	@Override
	public List<Card> getAll(String text) {
		List<String> attributes = Arrays.asList("id", "name", "color", "description", "status", "cread_by");        
        if (!text.contains("%")) {
            text = "%" + text + "%";
        }
        String finalText = text;
        Specification<Card> spec = (root, query, builder) -> 
        		builder.or(root.getModel().getDeclaredSingularAttributes().stream()
        				.filter(a -> attributes.contains(a.getName()))
        				.map(a -> {
        					if(a.getJavaType().getSimpleName().equalsIgnoreCase("Long")) {
        						return builder.like(root.get(a.getName()).as(String.class), finalText);
        					} else if(a.getJavaType().getSimpleName().equalsIgnoreCase("int")) {
        						return builder.like(root.get(a.getName()).as(String.class), finalText);
        					}
        					return builder.like(root.get(a.getName()), finalText);
        				})
        				.toArray(Predicate[]::new)
        );       
        return cardRepository.findAll(Specification.where(spec));
	}
	
	@Override
	public Card insertCard(Card card) {
		card = cardRepository.save(card);
		return card;
	}

	@Override
	public Card updateCard(Card card) {
		Optional<Card> projectDb = cardRepository.findById(card.getId());
		if(projectDb.isPresent()) {
			Card existingProject = projectDb.get();
			existingProject.setId(card.getId());
			existingProject.setColor(card.getColor());
			existingProject.setDescription(card.getDescription());
			existingProject.setName(card.getName());
            existingProject.setStatus(card.getStatus());
			return cardRepository.save(existingProject);
		}
		return null;
	}

	@Override
	public long deleteCard(Long id) {
		Optional<Card> projectDb = cardRepository.findById(id);
		if(projectDb.isPresent()) {
			cardRepository.delete(projectDb.get());
			return id;
		}
		return -1;
	}
}
