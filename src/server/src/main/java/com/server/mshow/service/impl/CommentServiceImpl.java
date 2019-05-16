package com.server.mshow.service.impl;

import com.server.mshow.dao.CommentMapper;
import com.server.mshow.domain.Comment;
import com.server.mshow.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Comment getComment(int cid) {
        return commentMapper.getComment(cid);
    }

    @Override
    public List<Comment> getCommentListByObject(int object_id) {
        return getCommentListByObject(object_id);
    }

    @Override
    public void createComment(Comment comment) {
        commentMapper.createComment(comment);
    }

    @Override
    public void cancelComment(int cid) {
        commentMapper.cancelComment(cid);
    }
}
