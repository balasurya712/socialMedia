package com.application.socialMedia.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.FollowDetail;
import com.application.socialMedia.model.FollowRequest;
import com.application.socialMedia.model.Page;

@Repository
public class PageDeleteDao {
    
    @Autowired
    MongoTemplate template;

    @Autowired
    PostDao postDao;

    @Autowired
    PageDao pageDao;

    /*delete all the things related to a page */
    public void deletePageAndItsRelevent(String id){
        postDao.deletePostByPageId(id);
        deleteFollowerDetailsByPage(id);
        deleteFollowingDetailsByPage(id);
        deleteDocumentInFollowRequestCollection(id);   
    
        Criteria criteria = Criteria.where("_id").is(id);
		Query query = new Query(criteria);
        template.remove(query,Page.class);
    }

    /*delete the follow request documents of this page */
    private void deleteDocumentInFollowRequestCollection(String id) {
        Criteria criteria = Criteria.where("pageId").is(new ObjectId(id));
		Query query = new Query(criteria);
        template.remove(query,FollowRequest.class);
    }

    /*decrease the count of following of that page followers and also delete the relevant document in followDetail collection */
    public void deleteFollowerDetailsByPage(String id) {
 
		Criteria criteria = Criteria.where("followingId").is(new ObjectId(id));
		Query query = new Query(criteria);
        List<FollowDetail> followers = template.find(query,FollowDetail.class);
        for(FollowDetail follower:followers){
            pageDao.decFollowingCount(follower.getFollowerId().get_id());
        }
		template.remove(query, FollowDetail.class);

	}

    /*decrease the count of follower of that page following and also delete the relevant document in followDetail collection */
    public void deleteFollowingDetailsByPage(String id) {
 
		Criteria criteria = Criteria.where("followerId").is(new ObjectId(id));
		Query query = new Query(criteria);
        List<FollowDetail> followings = template.find(query,FollowDetail.class);
        for(FollowDetail following:followings){
            pageDao.decFollowerCount(following.getFollowingId().get_id());
        }
		template.remove(query, FollowDetail.class);

	}
}
