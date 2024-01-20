package com.application.socialMedia.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AddFieldsOperation;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.Post;

@Repository
public class PostDao {
    @Autowired
    MongoTemplate template;

    public void incLikeCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("likeCount", 1);
 
		template.updateFirst(query, update, Post.class);
	}

    public void incCommentCount(String id) {

		System.out.println(id);
		
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("commentCount", 1);
 
		template.updateFirst(query, update, Post.class);
	}

    public void decLikeCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("likeCount", -1);
 
		template.updateFirst(query, update, Post.class);
	}

    public void decCommentCount(String id) {
 
		Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
 
		Update update = new Update().inc("commentCount", -1);
 
		template.updateFirst(query, update, Post.class);
	}

	public List<Post> recommendPost(){
		AddFieldsOperation fieldsOperation = Aggregation.addFields().addField("total").withValue(ArithmeticOperators.Add.valueOf("likeCount").add("commentCount")).build();
		SortOperation sortOperation = Aggregation.sort(Sort.by(Direction.DESC,"total"));
		Aggregation aggregation = Aggregation.newAggregation(fieldsOperation, sortOperation);
		ArrayList<Post> output = new ArrayList<>( template.aggregate(aggregation, "posts", Post.class).getMappedResults());

		int index = 0;
		while (index < output.size()) {
			if(output.get(index).getPageId().getPage_privacy().equals("private")){
				System.out.println(output.get(index).getPageId().getPage_privacy().equals("private")+"  "+output.get(index).getPageId().getPage_privacy());
				output.remove(index);
				// continue;
			}
			index++;
		}

		return output;
	}

}
