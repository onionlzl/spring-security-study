package com.idlelong.rbac.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.idlelong.rbac.entity.DataEntity;
import com.idlelong.rbac.vo.DataPageVO;
import org.apache.ibatis.annotations.Param;

/**
 * 数据(DataEntity)表数据库访问层
 *
 * @author idlelong
 * @since 2023-06-04 14:32:56
 */
public interface DataMapper extends BaseMapper<DataEntity> {

    /**
     * 查询数据分页信息
     * @param page 分页条件
     * @param wrapper 查询条件
     * @return 分页对象
     */
    IPage<DataPageVO> selectPage(Page<DataPageVO> page, @Param(Constants.WRAPPER) Wrapper<DataPageVO> wrapper);
}

