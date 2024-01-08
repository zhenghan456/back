package com.zhenghan.scenery.Service;

import com.zhenghan.scenery.Dao.FavoritesDao;
import com.zhenghan.scenery.Pojo.FavoritesPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritesServiceImpl {
    @Autowired
    FavoritesDao favoritesDao;
    public void add(String userid,String sceneryid,String favorites){
        favoritesDao.insert(new FavoritesPojo(userid,sceneryid,favorites));
    }
}
