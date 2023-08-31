package com.logicea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logicea.model.Card;
import com.logicea.service.CardsService;

@RestController
@RequestMapping("/api/cards")
public class CardsController {

	@Autowired
	private CardsService cardsService;

	@GetMapping
	public ResponseEntity<List<Card>> getAllCards() {
		return ResponseEntity.ok().body(cardsService.getAll());
	}

	@GetMapping(params = "search")
	public ResponseEntity<List<Card>> getAllCards(@RequestParam(value = "search", required = true) String search) {
		return ResponseEntity.ok().body(cardsService.getAll(search));
	}
	
	@PostMapping
	public ResponseEntity<Card> insertCard(@RequestBody Card card) {
		return ResponseEntity.ok().body(cardsService.insertCard(card));
	}

	@PutMapping
    public ResponseEntity<Card> updateCard(@RequestBody Card card) {
        return ResponseEntity.ok().body(cardsService.updateCard(card));
    }
	
	@DeleteMapping
	public ResponseEntity<Long> deleteCard(@RequestParam(name="id", value="id") long id) {
        return ResponseEntity.ok().body(cardsService.deleteCard(id));
    }
}
