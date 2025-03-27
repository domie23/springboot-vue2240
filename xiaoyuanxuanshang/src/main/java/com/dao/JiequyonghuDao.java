package com.dao;

import com.entity.JiequyonghuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiequyonghuView;

/**
 * 接取用户 Dao 接口
 *
 * @author 
 */
public interface JiequyonghuDao extends BaseMapper<JiequyonghuEntity> {

   List<JiequyonghuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
