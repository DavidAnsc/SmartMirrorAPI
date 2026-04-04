package com.davidan.SmartMirrorAPI.api_related.api_repos;

import org.springframework.data.repository.CrudRepository;

import com.davidan.SmartMirrorAPI.models.CodingNewsModel;

public interface CodingNewsRepo extends CrudRepository<CodingNewsModel, Integer> {
    
}
