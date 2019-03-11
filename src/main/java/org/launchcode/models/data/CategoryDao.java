package org.launchcode.models.data;

import groovy.lang.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

public interface CategoryDao extends CrudRepository<Category, Integer> {


    void save(org.launchcode.models.Category category);

    @Repository
    @Transactional
    public interface CheeseDao extends CrudRepository<Category, Integer> {
    }
}
