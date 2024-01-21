package com.application.socialMedia.dao;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.PageReport;

@Repository 
public class PageReportDao {
    
    @Autowired
    MongoTemplate template;

    /*delete the page report */
    public void deleteByPageId(String id){
        Criteria criteria = Criteria.where("pageId").is(new ObjectId(id));
		Query query = new Query(criteria);
		template.remove(query, PageReport.class);
    }
}
