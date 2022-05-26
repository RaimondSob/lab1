package com.example.lab1.services;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.util.Random;

@Default
@ApplicationScoped
public class StudentLspGeneratorDefault implements StudentLspGenerator{
    @Override
    public String generateStudentLsp(){
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {

        }
        String lsp = "s";
        for (int i = 0; i < 7; i++)
        {
            lsp += Integer.toString(new Random().nextInt(10));
        }
        return lsp;
    }

}
