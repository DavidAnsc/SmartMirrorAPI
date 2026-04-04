package com.davidan.SmartMirrorAPI.api_related.api_repos;
import org.springframework.data.repository.CrudRepository;

import com.davidan.SmartMirrorAPI.models.ZenQuotesModel;

public interface QuotesRepo extends CrudRepository<ZenQuotesModel, Integer> {
    
}
