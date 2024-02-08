package com.application.socialMedia.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

// import java.io.File;
// import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.socialMedia.dao.FollowDao;
import com.application.socialMedia.model.Page;
import com.application.socialMedia.model.response_model.PageResponse;
import com.application.socialMedia.repository.PageRepository;
import com.application.socialMedia.repository.UserRepository;
import com.application.socialMedia.service.PageServiceInterface;

@Service
public class PageService implements PageServiceInterface {

    @Autowired
    PageRepository repo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    FollowDao followDao;

    @Override
    public Page save(Page page, MultipartFile file) {
        if(!userRepo.existsByName(page.getName())){
            throw new RuntimeException("user not found");
        }
        if (repo.existsByName(page.getName())) {
            throw new RuntimeException("page already exists");
        }
        // if(file.getContentType()==null)
        // page.setDp("");
        // String a=file.getContentType();
        // if(a!=null && a.startsWith("image")){
        //     String filePath = "E:/socialMedia/src/main/java/com/application/socialMedia/media/dp_image" + file.getOriginalFilename();
        //     page.setDp(filePath);
        //     try {
        //         file.transferTo(new File(filePath));
        //     } catch (IllegalStateException | IOException e) {
        //         e.printStackTrace();
        //     }
        // }
        // else{
        //     throw new RuntimeException("not image type");
        // }
        
        return repo.save(page);
    }

    @Override
    public PageResponse getPageResponseById(String id) {
        Page page = repo.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not Found"));
        byte[] image = null;
        if (page.getDp() != null)
            try {
                image = Files.readAllBytes(new File(page.getDp()).toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        PageResponse pageResponse = new PageResponse(page.get_id(),page.getName(),page.getPage_handle(),page.getBio(),image,page.getDob(),page.getPage_privacy(),page.getPost_count(),page.getFollower_count(),page.getFollowing_count());
        return pageResponse;
    }

    
    @Override
    public Page getPageById(String id) {
        return repo.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not Found"));
    }

    @Override
    public boolean updateDP(String id, MultipartFile file) {
        Page page = getPageById(id);
        if(file == null)
        page.setDp("");
        else{
        String a=file.getContentType();
        if(a!=null && a.startsWith("image")){
            String filePath = "E:/socialMedia/src/main/java/com/application/socialMedia/media/dp_image/" + file.getOriginalFilename();
            page.setDp(filePath);
            try {
                file.transferTo(new File(filePath));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        else{
            throw new RuntimeException("not image type");
        }
    }
        repo.save(page);

        return true;
    }

    @Override
    public boolean updateBio(String id, String bio) {
        Page page = getPageById(id);
        page.setBio(bio);
        repo.save(page);
        return true;
    }

    @Override
    public boolean updatePageHandle(String id, String pageHandle) {
        Page page = getPageById(id);
        page.setPage_handle(pageHandle);
        repo.save(page);
        return true;
    }

    @Override
    public boolean isAccountPrivate(String id) {
        Page page = getPageById(id);
        if(page.getPage_privacy().equals("private")){
            return true;
        }
        return false;
    }

    public List<Page> mutualFriends(String id1,String id2){
        return followDao.findMutualFollowing(id1,id2);
    }

    public Page findPageByName(String name){
        return repo.findByName(name).get();
    }
}
