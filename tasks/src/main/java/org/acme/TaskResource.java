package org.acme;

import java.util.ArrayList;

import org.acme.model.Task;

import io.vertx.core.json.JsonObject;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/tasks")
public class TaskResource {

    private ArrayList<Task> tasks = new ArrayList<>();

    @GET
    @Path("/listTasks")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Task> listTasks() {
        return tasks;
    }

    @POST
    @Path("/addTask")
    @RolesAllowed("User")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Task> addTask(String  json) {

        JsonObject jsonObject = new JsonObject(json);
        String title = jsonObject.getString("title");
        String description = jsonObject.getString("description");
        String status = jsonObject.getString("status");
        String id = String.valueOf(tasks.size() + 1);
        
        Task task = new Task(title, description, status, id);
        tasks.add(task);

        return tasks;
    }

    @POST
    @Path("/deleteTask")
    @RolesAllowed("User")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Task> deleteTask(String json) {

        JsonObject jsonObject = new JsonObject(json);
        String id = jsonObject.getString("id");

        tasks.removeIf(task -> task.getId().equals(id));

        return tasks;
    }
    
    

    @POST
    @Path("/updateTask")
    @RolesAllowed("User")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Task> updateTask(String json) {

        JsonObject jsonObject = new JsonObject(json);
        String id = jsonObject.getString("id");
        String title = jsonObject.getString("title");
        String description = jsonObject.getString("description");
        String status = jsonObject.getString("status");

        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setTitle(title);
                task.setDescription(description);
                task.setStauts(status);
                break;
            }
        }
        return tasks;
    }
     

    @POST
    @Path("/changeStatus")
    @RolesAllowed("User")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Task> changeStatus(String json) {

        JsonObject jsonObject = new JsonObject(json);
        String id = jsonObject.getString("id");
        String status = jsonObject.getString("status");

        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setStauts(status);
                break;
            }
        }
        return tasks;
    }

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "Pong";
    }

}
