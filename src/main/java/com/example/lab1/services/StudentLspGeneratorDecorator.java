package com.example.lab1.services;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class StudentLspGeneratorDecorator implements StudentLspGenerator{
    @Inject @Delegate @Any
    StudentLspGenerator studentLspGenerator;

    @Override
    public String generateStudentLsp(){
        return "Decorator";
    }
}
