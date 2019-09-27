package org.example.springapp.repositories;

import org.example.springapp.entitys.FilesItem;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface FileItemRepo extends CrudRepository<FilesItem, Integer> {

    List<FilesItem> findByTag(String tag);
    FilesItem findById(int id);

}
