package com.dao;

import com.entity.RenwuOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.RenwuOrderView;

/**
 * 任务订单 Dao 接口
 *
 * @author 
 */
public interface RenwuOrderDao extends BaseMapper<RenwuOrderEntity> {

   List<RenwuOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
