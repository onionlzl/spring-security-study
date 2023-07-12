package com.idlelong.rbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.idlelong.rbac.mapper.DataMapper;
import com.idlelong.rbac.req.DataPageReq;
import com.idlelong.rbac.service.DataService;
import com.idlelong.rbac.vo.DataPageVO;
import com.idlelong.security.common.api.BaseComonPageReq;
import com.idlelong.security.common.api.CommonPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 数据服务impl
 *
 * @author lizhenlong
 * @date 2023/06/06
 */
@Service
public class DataServiceImpl implements DataService {

    @Resource
    private DataMapper dataMapper;

    @Override
    public CommonPage<DataPageVO> selectPage(DataPageReq dataPageReq) {
        Page<DataPageVO> pageReq = BaseComonPageReq.<DataPageVO>restPageReq(dataPageReq);
        IPage<DataPageVO> page = dataMapper.selectPage(pageReq, new QueryWrapper<DataPageVO>());
        return CommonPage.restPage(page);
    }
}
