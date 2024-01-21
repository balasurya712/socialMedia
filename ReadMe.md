# Project Title :- Social Media Application

# API calls and its functions

## Authentication module

api:- "api/auth/register"
work:- Here user,admin and content moderator can register. their role are specified internally using their email id and if the role is user then for them a document is created in page and follow request collection so that they no need to create a page again they can just update the details. it returns the jwt token

api:- "api/auth/authenticate"
work:- when a registered person want to login they use this call. if the user name and password are correct they get JWT token 

## Page module

here the page mention user profile.
The page document is created when a user register through register link in authentication module

api:- "page/get_page"
work:- return the page of a particular user

api:- "page/dp/{id}"
work:- update dp
note:- the image is stored as link in db and it was saved locally in our system inside the folder name image

api:- "page/bio/{id}"
work:- update bio  

api:- "page/page_handle/{id}"
work:- update page handle
note:- page handle is a recognition name for a user

api:- "page/mutual_friends"
work:- return the mutual friends between two pages
note:- it get mutual friends by identifying the common following between two pages 

## Post module

api:- "post/save"
work:- save a post

api:- "post/recommended_post"
work:- return a list of posts in the order of highest likes and comments

api:- "post/following_post"
work:- return a list of posts who they follow in the order of latest posted post
note:- it was done by ordering the date and time in descending

api:- "post/delete_post"
work:- delete a post and delete all its likes and comments. it also delete the comments likes and comments

## Following module

api:- "follow/request"
work:- adding the following 
note:- if the requested page is private the request is added to a document in follow_request collection. if not directly following is added and the count of followings and followers are increased in their respective pages

api:- "follow/view_request"
work:- return the list of requested pages for a particular page 

api:- "follow/accept_request"
work:- accept a particular following request and remove the request from the document in follow_request collection and also add it to the following collection 

api:- "follow/delete_request"
work:- it is called when the requester cancel the request and when a page rejects the request   

api:- "follow/delete_following"
work:- it will do un-follow operation  and decrease the follower following count i the respective pages

## Like module

api:- "like/add"
work:- it will add a like to post or comment mentioned in it and also increase the count 

api:- "like/delete"
work:- it will delete a like to post or comment mentioned in it and also decrease the count 

api:- "like/get_likes"
work:- it will return the list of page which liked that particular post or comment

## Comment module

api:- "comment/add"
work:- it will add a comment to post or comment mentioned in it and also increase the count 

api:- "comment/delete"
work:- it will delete a comment to post or comment mentioned in it and also decrease the count 

api:- "comment/get_comment"
work:- it will return the list of page which commented to that particular post or comment

## Post Report module

api:- "report_post/report"
work:- user can report a post if it is illegal content

api:- "report_post/view_report"
work:- content moderator can view all the report through this api 

api:- "report_post/delete_report"
work:- content moderator will delete the report alone if the post is not illegal

api:- "report_post/delete_report_post"
work:- content moderator can delete the post report and the post if the post is illegal

## Page Report module

api:- "report_page/report"
work:- user can report a page if it is spam or so misused page

api:- "report_page/view_report"
work:- Admin can view all the reported page through this api 

api:- "report_page/delete_report"
work:- Admin will delete the report alone if the page is not spam or so misused page

api:- "report_page/delete_report_post"
work:- Admin can delete the post report, page ,user and the post posted by the page if the page spam or so misused page