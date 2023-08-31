package com.logicea.service;

import java.util.List;

import com.logicea.model.Card;

public interface CardsService {

	List<Card> getAll();
	
	List<Card> getAll(String text);
	
	Card insertCard(Card card);
	
	Card updateCard(Card card);
	
	long deleteCard(Long id);
}
