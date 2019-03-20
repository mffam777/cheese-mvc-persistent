package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Entity
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @NotNull
    @Size(min = 1, message = "Description must not be empty")
    private String description;

    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "cheeses")
    private List<Menu> menus;

    public Cheese(String name , String description) {
        this.name = name;
        this.description = description;
    }

    public Cheese() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("set name - Cheese Model");

        this.name = name;
    }

    public String getDescription() {
        System.out.println("get Description - Cheese Model");

        return description;
    }

    public void setDescription(String description) {
        System.out.println("set Description - Cheese Model");

        this.description = description;
    }

    public void setId(int id) {
        System.out.println("set ID - Cheese Model");
        this.id = id;
    }

    public Category getCategory() {
        System.out.println("get Category - Cheese Model");
        return category;
    }

    public void setCategory(Category category) {
        System.out.println("Set Category  - Cheese Model ");
        this.category = category;
    }
}
