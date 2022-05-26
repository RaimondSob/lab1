package com.example.lab1.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

@Specializes
@ApplicationScoped
public class StudentLspGeneratorSpecial extends StudentLspGeneratorDefault{
    @Override
    public String generateStudentLsp(){
        return super.generateStudentLsp() + "Special";
    }
}
