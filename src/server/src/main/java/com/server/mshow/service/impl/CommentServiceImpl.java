package com.server.mshow.service.impl;

import com.server.mshow.dao.CommentMapper;
import com.server.mshow.domain.Comment;
import com.server.mshow.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    //@Autowired
    private CommentMapper commentMapper;

    @Override
    public Comment getComment(String cid) {
        return null;
    }

    @Override
    public List<Comment> getCommentListByObject(String object_id) {
        return null;
    }

    @Override
    public void createComment(Comment comment) {

    }

    @Override
    public void cancelComment(String cid) {

    }
}
