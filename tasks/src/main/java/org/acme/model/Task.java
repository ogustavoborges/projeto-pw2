package org.acme.model;


public class Task {

    
    private String title;
    private String description;
    private String stauts;
    private String id;

    public Task() {
    }

    public Task(String title, String description, String stauts, String id) {
        this.title = title;
        this.description = description;
        this.stauts = stauts;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getStauts() {
        return stauts;
    }

    public void setStauts(String stauts) {
        this.stauts = stauts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }





}
