package com.davidan.SmartMirrorAPI.api_related.api_repos;

import org.springframework.data.repository.CrudRepository;

import com.davidan.SmartMirrorAPI.models.MathQuestionsModel;

public interface MathQuestionsRepo extends CrudRepository<MathQuestionsModel, Integer> {
    
}
