package com.caha.rest.swag.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caha.rest.swag.exception.PersonNotFoundException;
import com.caha.rest.swag.model.Person;
import com.caha.rest.swag.repository.PersonRepository;

@RestController
@RequestMapping("/api")
public class PersonController {

	@Autowired
	private PersonRepository repository;

	@GetMapping("/persons")
	public CollectionModel<Person> getAll() {
		List<Person> persons = repository.findAll();

		return CollectionModel.of(persons, linkTo(methodOn(PersonController.class).getAll()).withSelfRel());
	}

	@PostMapping("/persons")
	public EntityModel<Person> newPerson(@RequestBody Person person) {

		Person result = repository.save(person);

		return EntityModel.of(result,
				linkTo(methodOn(PersonController.class).getPersonById(person.getId())).withSelfRel(),
				linkTo(methodOn(PersonController.class).getAll()).withRel("persons"));
	}

	@GetMapping("/persons/{id}")
	public EntityModel<Person> getPersonById(@PathVariable Long id) {
		/*
		 * List<Person> list = Arrays.asList(new Person("1", "Carlos", "Hidalgo",
		 * "Liberia")); Person person = list.stream() .filter(x -> id.equals(x.getId()))
		 * .findAny() .orElse(null);
		 */

		// return repository.findById(id).orElseThrow(() -> new
		// PersonNotFoundException(id));

		Person person = repository.findById(id) //
				.orElseThrow(() -> new PersonNotFoundException(id));

		return EntityModel.of(person, linkTo(methodOn(PersonController.class).getPersonById(id)).withSelfRel(),
				linkTo(methodOn(PersonController.class).getAll()).withRel("persons"));
	}
}
