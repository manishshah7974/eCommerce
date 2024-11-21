package org.QuarkusProjectReactive.resource;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.QuarkusProjectReactive.model.Person;
import org.QuarkusProjectReactive.repository.PersonRepository;

import java.util.List;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonRepository personRepository;

    @GET
    public Uni<List<Person>> getAllPersons() {
        return personRepository.findAllPersons();
    }

    @GET
    @Path("/{id}")
    public Uni<Person> getPersonById(@PathParam("id") Long id) {
        return personRepository.findPersonById(id);
    }

    @POST
    public Uni<Response> createPerson(Person person) {
        System.out.println("Creating person: " + person.getName() + ", Age: " + person.getAge());
        return personRepository.savePerson(person)
                .onFailure().invoke(ex -> System.out.println("Error saving person: " + ex.getMessage()))
                .onItem().transform(savedPerson -> {
                    if (savedPerson != null) {
                        System.out.println("Person saved with ID: " + savedPerson.id);
                        return Response.status(Response.Status.CREATED).entity(savedPerson).build();
                    } else {
                        System.out.println("Failed to save person");
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                    }
                });
    }


    @PUT
    @Path("/{id}")
    public Uni<Response> updatePerson(@PathParam("id") Long id, Person person) {
        person.setId(id); // Ensure the ID is set
        return personRepository.updatePerson(id, person)
                .onItem().transform(updatedPerson -> Response.ok(updatedPerson).build())
                .onFailure().recoverWithItem(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deletePerson(@PathParam("id") Long id) {
        return personRepository.deletePerson(id)
                .onItem().transform(voidResponse -> Response.noContent().build())
                .onFailure().recoverWithItem(Response.status(Response.Status.NOT_FOUND).build());
    }
}
