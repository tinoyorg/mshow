package com.server.mshow.dao;

import com.server.mshow.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CommentMapper {

    Comment getComment(int cid);
    List<Comment> getCommentListByObject(int object_id);
    void createComment(Comment comment);
    void cancelComment(int cid);

}
