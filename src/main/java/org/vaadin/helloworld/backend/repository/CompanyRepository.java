package org.vaadin.helloworld.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.vaadin.helloworld.backend.entity.Company;
import org.vaadin.helloworld.backend.entity.Contact;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    /*
    Uses the @Query annotation to define a custom query. In this case,
    it checks if the string matches the first or the last name, and ignores the case.
    The query uses Java Persistence Query Language (JPQL) which is an SQL-like language for querying JPA managed databases.
     */
    @Query("select c from Contact c " +
            "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))")

    List<Contact> search(@Param("searchTerm") String searchTerm); // Selects the Spring Framework import for @Param
}
