package com.application.socialMedia.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.Comment;
import com.application.socialMedia.model.Like;


@Repository
public class CommentDao {
    
    @Autowired
    MongoTemplate template;

	/*this method use to delete comment */
    public void deleteComment(Comment comment) {
 
		Criteria criteria = Criteria.where("postOrCommentId").is(comment.getPostOrCommentId()).andOperator(Criteria.where("pageId").is(comment.getPageId()));
		Query query = new Query(criteria);
		template.remove(query, Comment.class);

	}

	/*this method use to get the comment of particular post or comments*/
	public List<Comment> getComment(String id) {
 
		Criteria criteria = Criteria.where("postOrCommentId").is(id);
		Query query = new Query(criteria);
		return template.find(query, Comment.class);

	}

	/*increase the like count of the comment */
	public void incLikeCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("likeCount", 1);
 
		template.updateFirst(query, update, Comment.class);
	}

	/*increase the comment count of the comment */
    public void incCommentCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("commentCount", 1);
 
		template.updateFirst(query, update, Comment.class);
	}

	/*decrease the like count of the comment */
    public void decLikeCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("likeCount", -1);
 
		template.updateFirst(query, update, Comment.class);
	}

	/*decrease the comment count of the comment */
    public void decCommentCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("commentCount", -1);
 
		template.updateFirst(query, update, Comment.class);
	}

	/*delete all the likes and comments relevant to the post which is deleted */
	public void deleteAllLikesAndCommentByPostOrCommentId(String postOrcommentId){
		Criteria criteria = Criteria.where("postOrCommentId").is(postOrcommentId);
		Query query = new Query(criteria);
		template.remove(query, Like.class);
		List<Comment> comments =  template.find(query, Comment.class);

		if(comments.isEmpty()){
			return;
		}

		for(Comment comment:comments){
			deleteAllLikesAndCommentByPostOrCommentId(comment.get_id());
		}

		template.remove(query,Comment.class);
		
	}
}
