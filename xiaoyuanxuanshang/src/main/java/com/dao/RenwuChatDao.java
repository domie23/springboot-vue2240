package com.dao;

import com.entity.RenwuChatEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.RenwuChatView;

/**
 * 任务咨询 Dao 接口
 *
 * @author 
 */
public interface RenwuChatDao extends BaseMapper<RenwuChatEntity> {

   List<RenwuChatView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
