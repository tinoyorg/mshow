package com.server.mshow.service.impl;



import com.server.mshow.dao.ShowMapper;
import com.server.mshow.domain.Show;
import com.server.mshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ShowService")
public class ShowServiceImpl implements ShowService {

    //@Autowired
    private ShowMapper showMapper;

    @Override
    public Show getShow(int sid) {
        return showMapper.getShow(sid);
    }

    @Override
    public List<Show> getAllShowList() {
        return showMapper.getAllShowList();
    }

    @Override
    public List<Show> getShowListByEid(int eid) {
        return showMapper.getShowListByEid(eid);
    }

    @Override
    public void createShow(Show show) {
        showMapper.createShow(show);
    }

    @Override
    public void updateShow(Show show) {
        showMapper.updateShow(show);
    }

    @Override
    public void deleteShow(int sid) {
            showMapper.deleteShow(sid);
    }
}
