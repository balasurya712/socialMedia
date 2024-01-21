package com.application.socialMedia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.Like;

@Repository
public class LikeDao {
    
    @Autowired
    MongoTemplate template;

	/*delete like (works like unlike) */
    public void deleteLike(Like like) {
 
		Criteria criteria = Criteria.where("postOrCommentId").is(like.getPostOrCommentId()).andOperator(Criteria.where("likedId").is(like.getLikedId()));
		Query query = new Query(criteria);
		template.remove(query, Like.class);

	}

	/* get the liked page id's */
	public List<Like> getLike(String id) {
 
		Criteria criteria = Criteria.where("postOrCommentId").is(id);
		Query query = new Query(criteria);
		return template.find(query, Like.class);

	}
	
}
