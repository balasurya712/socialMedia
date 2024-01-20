package com.application.socialMedia.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.Page;

@Repository
public class PageDao {
    @Autowired
    MongoTemplate template;

    public void updateBio(String id, String bio) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().set("bio", bio);
 
		template.updateFirst(query, update, Page.class);
	}

    public void updatedP(String id, String url) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().set("dp", url);
 
		template.updateFirst(query, update, Page.class);
	}

    public void updatePageHandle(String id, String pageHandle) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().set("page_handle", pageHandle);
 
		template.updateFirst(query, update, Page.class);
	}

    public void updateDob(String id, Date dob) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().set("dob", dob);
 
		template.updateFirst(query, update, Page.class);
	}

    public void updatePagePrivacy(String id, String pagePrivacy) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().set("page_privacy", pagePrivacy);
 
		template.updateFirst(query, update, Page.class);
	}

    public void incPostCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("post_count", 1);
 
		template.updateFirst(query, update, Page.class);
	}

    public void incFollowerCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("follower_count", 1);
 
		template.updateFirst(query, update, Page.class);
	}

    public void incFollowingCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("following_count", 1);
 
		template.updateFirst(query, update, Page.class);
	}

    public void decPostCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("post_count", -1);
 
		template.updateFirst(query, update, Page.class);
	}

    public void decFollowerCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("follower_count", -1);
 
		template.updateFirst(query, update, Page.class);
	}

    public void decFollowingCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("following_count", -1);
 
		template.updateFirst(query, update, Page.class);
	}

}
