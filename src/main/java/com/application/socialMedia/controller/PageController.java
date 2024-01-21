package com.application.socialMedia.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

// import com.application.socialMedia.model.Page;
import com.application.socialMedia.model.response_model.PageResponse;
import com.application.socialMedia.service.impl.PageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@Controller
@RequestMapping(path = "page")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class PageController {

    @Autowired
    PageService service;

    // @PostMapping("/save_page")
    // public ResponseEntity<Page> savePage(@RequestBody Page page) {
    //     MultipartFile file = null;
    //     return new ResponseEntity<>(service.save(page,file),HttpStatus.OK);
    // }

    @GetMapping("/get_page")
    public ResponseEntity<PageResponse> getPage(@RequestParam String id) {
        PageResponse page = service.getPageResponseById(id);
        // byte[] image = null;
        // if (page.getDp() != null)
        //     try {
        //         image = Files.readAllBytes(new File(page.getDp()).toPath());
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        // PageResponse pageResponse = new PageResponse(page.get_id(),page.getName(),page.getPage_handle(),page.getBio(),image,page.getDob(),page.getPage_privacy(),page.getPost_count(),page.getFollower_count(),page.getFollowing_count());
        return new ResponseEntity<>(page,HttpStatus.OK);
    }

    @PutMapping("dp/{id}")
    public  ResponseEntity<?> updateDP(@PathVariable String id, @RequestBody MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return new ResponseEntity<>(service.updateDP(id, file),HttpStatus.OK);
    }

    @PutMapping("bio/{id}")
    public  ResponseEntity<?> updateBio(@PathVariable String id, @RequestBody String bio) {
        return new ResponseEntity<>(service.updateBio(id, bio),HttpStatus.OK);
    }

    @PutMapping("page_handle/{id}")
    public  ResponseEntity<?> updatePsgeHandle(@PathVariable String id, @RequestBody String pageHandle) {
        return new ResponseEntity<>(service.updatePageHandle(id, pageHandle),HttpStatus.OK);
    }

    @GetMapping("mutual_friends")
    public ResponseEntity<?> getMethodName(@RequestParam String id1,@RequestParam String id2) {
        return new ResponseEntity<>(service.mutualFriends(id1,id2),HttpStatus.OK);
    }
    
    
       
}
