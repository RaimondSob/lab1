package com.example.lab1.usecases;

import com.example.lab1.services.StudentLspGenerator;
import com.example.lab1.interceptors.LoggedInvocation;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateStudentLsp implements Serializable {

    @Inject
    StudentLspGenerator studentLspGenerator;

    private CompletableFuture<String> studentLspGenerationTask = null;

    @LoggedInvocation
    public String generateStudentLsp(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        studentLspGenerationTask = CompletableFuture.supplyAsync(() -> studentLspGenerator.generateStudentLsp());

        return "studentDetails?faces-redirect=true&amp;studentId="+requestParameters.get("studentId");
    }

    public boolean isStudentLspGenerationRunning() {
        return studentLspGenerationTask != null && !studentLspGenerationTask.isDone();
    }

    public String getStudentLspGenerationStatus() throws ExecutionException, InterruptedException {
        if (studentLspGenerationTask == null) {
            return null;
        }
        else if (isStudentLspGenerationRunning()) {
            return "Student lsp generation in progress";
        }
        return "Suggested lsp: " + studentLspGenerationTask.get();
    }
}
