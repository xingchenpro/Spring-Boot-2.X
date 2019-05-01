package com.hly.springbootmybatismultidatasources.dao.videoService;

import com.hly.springbootmybatismultidatasources.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2019/4/9
 */
@Repository
public interface VideoDao {

    List<Video> getVideoByArticleId(@Param(value="a_id")int  a_id);

}
