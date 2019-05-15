package com.server.mshow.dao;

import com.server.mshow.domain.Comment;

import java.util.List;

public interface CommentMapper {

    Comment getComment(int cid);
    List<Comment> getCommentListByObject(int object_id);
    void createComment(Comment comment);
    void cancelComment(int cid);

}
