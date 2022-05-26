package com.example.lab1.services;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Alternative
@ApplicationScoped
public class StudentLspGeneratorAlternative implements StudentLspGenerator{
    @Override
    public String generateStudentLsp(){
        return "Alternative";
    }
}
