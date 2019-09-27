package org.example.springapp.repositories;


import org.example.springapp.entitys.Tags;
import org.springframework.data.repository.CrudRepository;



public interface TagsRepo extends CrudRepository<Tags, Integer> {

}

