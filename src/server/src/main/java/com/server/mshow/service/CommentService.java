package com.server.mshow.service;

import com.server.mshow.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment getComment(int cid);
    List<Comment> getCommentListByObject(int object_id);
    void createComment(Comment comment);
    void cancelComment(int cid);
    void cancelCommentByObject(int uid,int object_id,String object_type);

}
