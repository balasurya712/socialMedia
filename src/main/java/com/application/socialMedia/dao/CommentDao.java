package com.application.socialMedia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.Comment;


@Repository
public class CommentDao {
    
    @Autowired
    MongoTemplate template;

    public void deleteComment(Comment comment) {
 
		Criteria criteria = Criteria.where("postOrCommentId").is(comment.getPostOrCommentId()).andOperator(Criteria.where("pageId").is(comment.getPageId()));
		Query query = new Query(criteria);
		template.remove(query, Comment.class);

	}

	public List<Comment> getComment(String id) {
 
		Criteria criteria = Criteria.where("postOrCommentId").is(id);
		Query query = new Query(criteria);
		return template.find(query, Comment.class);

	}

	public void incLikeCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("likeCount", 1);
 
		template.updateFirst(query, update, Comment.class);
	}

    public void incCommentCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("commentCount", 1);
 
		template.updateFirst(query, update, Comment.class);
	}

    public void decLikeCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("likeCount", -1);
 
		template.updateFirst(query, update, Comment.class);
	}

    public void decCommentCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("commentCount", -1);
 
		template.updateFirst(query, update, Comment.class);
	}
}
