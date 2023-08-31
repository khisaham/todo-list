package com.logicea.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.logicea.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

	List<Card> findAll(Specification<Card> spec);	
}