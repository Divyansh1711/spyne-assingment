# spyne-assingment

config service------------------------
for configuration realted to eureka server

registery service------------------------


Services and API Endpoints
User Service--------------------------------------------------------------------------------------------------------
Create User: POST /users
Update User: PUT /users/{userId}
Delete User: DELETE /users/{userId}
Get User by ID: GET /users/{userId}
Search User by Name: GET /users/search?name=<name>
List All Users: GET /users

Discussion Service---------------------------------------------------------------------------------------------------
Create Discussion: POST /discussions
Update Discussion: PUT /discussions/{discussionId}
Delete Discussion: DELETE /discussions/{discussionId}
Get Discussion by ID: GET /discussions/{discussionId}
List All Discussions: GET /discussions
Search Discussions by Hashtag: GET /discussions/searchByHashtag?hashtag=<hashtag>
Search Discussions by Text: GET /discussions/searchByText?text=<text>

Comment Service-------------------------------------------------------------------------------------------------------

Create Comment: POST /comments
Update Comment: PUT /comments/{commentId}
Delete Comment: DELETE /comments/{commentId}
Get All Comments for Discussion: GET /comments/discussion/{discussionId}

Like Service-----------------------------------------------------------------------------------------------------------

Like Discussion: POST /likes/discussion
Unlike Discussion: DELETE /likes/discussion
Like Comment: POST /likes/comment
Unlike Comment: DELETE /likes/comment

Follow Service--------------------------------------------------------------------------------------------------------

Follow User: POST /follow
Unfollow User: POST /unfollow?followerUserId=<followerUserId>&followedUserId=<followedUserId>
Get Followers: GET /followers/{userId}
Get Following: GET /following/{userId}
