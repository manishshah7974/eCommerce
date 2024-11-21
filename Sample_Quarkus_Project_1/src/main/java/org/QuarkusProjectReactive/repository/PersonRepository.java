package org.QuarkusProjectReactive.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.QuarkusProjectReactive.model.Person;

import java.util.List;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    // Fetch all persons
    public Uni<List<Person>> findAllPersons() {
        return listAll(); // Returns Uni<List<Person>>
    }

    // Fetch a person by ID

    public Uni<Person> findPersonById(Long id) {
        return findById(id); // Returns Uni<Person>
    }

    @WithTransaction
    public Uni<Person> savePerson(Person person) {
        System.out.println("Saving person: " + person.getName() + ", Age: " + person.getAge());
        return persistAndFlush(person)
                .onFailure().invoke(ex -> System.out.println("Error saving person: " + ex.getMessage()))
                .onItem().transform(savedPerson -> {
                    System.out.println("Person saved: " + savedPerson.getId());
                    return savedPerson;
                });
    }


    // Update an existing person
    @WithTransaction
    public Uni<Person> updatePerson(Long id, Person person) {
        person.id = id; // Ensure the ID is set
        return findById(id) // First, find the person
                .onItem().ifNotNull().transformToUni(existingPerson -> {
                    existingPerson.setName(person.getName());
                    existingPerson.setAge(person.getAge());
                    return persistAndFlush(existingPerson); // Return the updated person
                })
                .onItem().ifNull().failWith(new Exception("Person not found")); // Fail if not found
    }

    // Delete a person by ID
    public Uni<Void> deletePerson(Long id) {
        return findById(id)
                .onItem().transformToUni(person -> {
                    if (person != null) {
                        return delete(person).replaceWithVoid(); // Deletes the person and returns Uni<Void>
                    } else {
                        return Uni.createFrom().voidItem(); // Return Uni<Void> if not found
                    }
                });
    }
}
