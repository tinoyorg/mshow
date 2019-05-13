package com.server.mshow.service;

import com.server.mshow.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment getComment(String cid);
    List<Comment> getCommentListByObject(String object_id);
    void createComment(Comment comment);
    void cancelComment(String cid);


}
