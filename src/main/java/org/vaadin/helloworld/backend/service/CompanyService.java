package org.vaadin.helloworld.backend.service;

import org.springframework.stereotype.Service;
import org.vaadin.helloworld.backend.entity.Company;
import org.vaadin.helloworld.backend.repository.CompanyRepository;

import java.util.List;

@Service
public class CompanyService {
    //we only inject the company repository
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    //we expose a findAll method
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
