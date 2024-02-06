package com.application.socialMedia.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.socialMedia.dao.CommentDao;
import com.application.socialMedia.dao.FollowDao;
// import com.application.socialMedia.dao.LikeDao;
import com.application.socialMedia.dao.PostDao;
import com.application.socialMedia.model.Post;
import com.application.socialMedia.model.response_model.PostResponse;
import com.application.socialMedia.repository.PageRepository;
import com.application.socialMedia.repository.PostRepository;
import com.application.socialMedia.service.PostServiceInterface;

@Service
public class PostService implements PostServiceInterface {

    @Autowired
    PostRepository repo;

    @Autowired
    PostDao postDao;

    @Autowired
    FollowDao followDao;

    @Autowired
    PageRepository pageRepo;

    // @Autowired
    // LikeDao likeDao;

    @Autowired
    CommentDao commentDao;

    @Override
    public Post save(PostResponse postResponse) {
        MultipartFile file = postResponse.getMedia();
        String url = "";
        String a=file.getContentType();
        if(a!=null && a.startsWith("image")){
            url = "E:/socialMedia/src/main/java/com/application/socialMedia/media/dp_image" + file.getOriginalFilename();
            try {
                file.transferTo(new File(url));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        else{
            throw new RuntimeException("not image type");
        }
        Post post = Post.builder()._id(postResponse.get_id()).caption(postResponse.getCaption()).mediaUrl(url).pageId(pageRepo.findById(postResponse.getPageId()).get()).primary_poster(postResponse.getPrimary_poster()).commentCount(postResponse.getCommentCount()).likeCount(postResponse.getLikeCount()).date_and_time(postResponse.getDate_and_time()).build();
        return repo.save(post);
    }
    @Override
    public List<Post> recommendedPost(Integer limit) {
        return postDao.recommendPost(limit);
    }

    public List<Post> getFollowingPost(String id) {
        return followDao.getFollowingPost(id);
    }

    public boolean delete(String id){
        commentDao.deleteAllLikesAndCommentByPostOrCommentId(id);
        // likeDao.deleteAllLikeByPostOrComment(id);
        repo.deleteById(id);
        return true;
    }

    public Boolean deleteByPageId(String pageId){
        postDao.deletePostByPageId(pageId);
        return true;
    }

}
