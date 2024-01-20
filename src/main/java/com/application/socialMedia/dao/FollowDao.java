package com.application.socialMedia.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.FollowDetail;
import com.application.socialMedia.model.FollowRequest;
import com.application.socialMedia.model.Page;
import com.application.socialMedia.model.Post;
import com.application.socialMedia.repository.PageRepository;

@Repository
public class FollowDao {

    @Autowired
    MongoTemplate template;

	@Autowired
	PageRepository repo;

    public void addFollowerToFollowerList(String followerId, String followingId) {
 
		Criteria criteria = Criteria.where("pageId").is(followingId);
		Query query = new Query(criteria);
 
		Update update = new Update().push("requestedId", new ObjectId(followerId));
 
		template.updateFirst(query, update, FollowRequest.class);
	}

    public void removeFollowerToFollowerList(String followerId, String followingId) {
 
		Criteria criteria = Criteria.where("pageId").is(followingId);
		Query query = new Query(criteria);
 
		Update update = new Update().pull("requestedId", new ObjectId(followerId));
 
		template.updateFirst(query, update, FollowRequest.class);
	}

	public void deleteFollowDetails(FollowDetail followDetail) {
 
		Criteria criteria = Criteria.where("followingId").is(followDetail.getFollowingId()).andOperator(Criteria.where("followerId").is(followDetail.getFollowerId()));
		Query query = new Query(criteria);
		template.remove(query, FollowDetail.class);

	}

	// public AggregationResults<String> mutualFrinds(String id1,String id2) {
 
	// 	// [{ $match: 
	// 	// 	{ $or: [ 
	// 	// 	{ followerId: '65a6558bfed7a90a9f1ed1f0' },
	// 	// 	{ followerId: '65a65586fed7a90a9f1ed1ed' }
	// 	//   ] } },
	// 	//  {
	// 	//    $group: {
	// 	// 	 _id: "$followerId",
	// 	// 	 following: {
	// 	// 	   $push : "$followingId"
	// 	// 	 }
	// 	//    }
	// 	//  },
	// 	//    {    $group: {
	// 	// 		 _id:null,
	// 	// 		 following1: { $first: "$following" },
	// 	// 		 following2: { $last: "$following" }
	// 	// 		 }
	// 	// 	},
	// 	//  {    $project:{
	// 	// 			   _id:0,
	// 	// 		mutual_following:{
	// 	// 		$setIntersection:["$following1","$following2"]
	// 	// 		}
	// 	// 		 }
	// 	// 	}
	// 	// ]
	// 	GroupOperation groupByStateAndSumPop = Aggregation.group("state").accumulate(null).as("statePop");
	// 	MatchOperation filterStates = Aggregation.match(new Criteria("statePop").gt(10000000));

	// 	Aggregation aggregation = Aggregation.newAggregation(groupByStateAndSumPop,filterStates);

	// 	AggregationResults<String> result=template.aggregate(aggregation, "follow_details",String.class);

		
	// 	return result;
	// }


	public List<Page> findMutualFollowing(String id1,String id2) {
		MatchOperation match1 =  Aggregation.match(new Criteria().orOperator(
								Criteria.where("followerId").is(new ObjectId(id1)),
								Criteria.where("followerId").is(new ObjectId(id2))));
		LookupOperation lookUp = Aggregation.lookup("page", "followingId", "_id", "mutual");
		UnwindOperation unwind = Aggregation.unwind("$mutual");
		ProjectionOperation projectStage = Aggregation.project()
		.andExpression("'$mutual._id'").as("_id")
		.andExpression("'$mutual.name'").as("name")
		.andExpression("'$mutual.page_handle'").as("page_handle")
		.andExpression("'$mutual.bio'").as("bio")
		.andExpression("'$mutual.dp'").as("dp")
		.andExpression("'$mutual.dob'").as("dob")
		.andExpression("'$mutual.page_privacy'").as("page_privacy")
		.andExpression("'$mutual.post_count'").as("post_count")
		.andExpression("'$mutual.follower_count'").as("follower_count")
		.andExpression("'$mutual.following_count'").as("following_count")
		.andExpression("'$mutual._class'").as("_class");
		Aggregation aggregation = Aggregation.newAggregation(match1,lookUp,unwind,projectStage);
		List<Page> output = template.aggregate(aggregation, "follow_details", Page.class).getMappedResults();
		// List<String> output1 = template.aggregate(aggregation, "follow_details", String.class).getMappedResults();
		// System.out.println("o1  "+output1);
		// System.out.println("o  "+output);
		// System.out.println(followDetail.getFollowerId() + "    "+followDetail.getFollowingId());

		// String result = output.get(0);

		// String followingJsonArray = result.substring(result.indexOf('[') + 2, result.lastIndexOf(']')-1);
        // List<String> followingArray = new ArrayList<>(List.of(followingJsonArray.split("\", \"")));
		List<Page> mutualFollower = new ArrayList<>();
		HashSet<Page> set = new HashSet<>();
        for (Page value : output) {
			if (!set.add(value)) {
                mutualFollower.add(value);
            }
            
        }
        if (!mutualFollower.isEmpty()) {
            return mutualFollower;
        } else {
            return Collections.emptyList();
        }
    }

	public List<Post> getFollowingPost(String followerId){
		MatchOperation match1 =  Aggregation.match(Criteria.where("followerId").is(new ObjectId(followerId)));
		LookupOperation lookUp = Aggregation.lookup("posts", "followingId", "pageId", "followerpost");
		UnwindOperation unwind = Aggregation.unwind("followerpost");
		SortOperation sortOperation = Aggregation.sort(Sort.by(Direction.DESC,"followerpost.date_and_time"));
		ProjectionOperation projection = Aggregation.project()
		.and("followerpost._id").as("_id")
		.and("followerpost.pageId").as("pageId")
		.and("followerpost.mediaUrl").as("mediaUrl")
		.and("followerpost.primary_poster").as("primary_poster")
		.and("followerpost.likeCount").as("likeCount")
		.and("followerpost.commentCount").as("commentCount")
		.and("followerpost.caption").as("caption")
		.and("followerpost.date_and_time").as("date_and_time");
		Aggregation aggregation = Aggregation.newAggregation(match1,lookUp,unwind,sortOperation,projection);
		List<Post> output = template.aggregate(aggregation, "follow_details", Post.class).getMappedResults();
		List<String> output1 = template.aggregate(aggregation, "follow_details", String.class).getMappedResults();
		System.out.println(output);
		System.out.println(output1);
		return output;
	}
}
