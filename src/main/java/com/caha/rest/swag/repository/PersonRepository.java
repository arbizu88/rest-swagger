package com.caha.rest.swag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caha.rest.swag.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
