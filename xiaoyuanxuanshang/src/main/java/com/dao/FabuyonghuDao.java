package com.dao;

import com.entity.FabuyonghuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.FabuyonghuView;

/**
 * 发布用户 Dao 接口
 *
 * @author 
 */
public interface FabuyonghuDao extends BaseMapper<FabuyonghuEntity> {

   List<FabuyonghuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
