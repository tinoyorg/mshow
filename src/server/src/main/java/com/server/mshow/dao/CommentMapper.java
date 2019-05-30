package com.server.mshow.dao;

import com.server.mshow.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CommentMapper {

    Comment getComment(int cid);
    List<Comment> getCommentListByObject(int object_id);
    void createComment(Comment comment);
    void cancelComment(int cid);
    void cancelCommentByObject(@Param("uid") int uid, @Param("object_id") int object_id, @Param("object_type") String object_type);
}
