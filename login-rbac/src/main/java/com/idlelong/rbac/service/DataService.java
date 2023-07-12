package com.idlelong.rbac.service;

import com.idlelong.rbac.req.DataPageReq;
import com.idlelong.rbac.vo.DataPageVO;
import com.idlelong.security.common.api.CommonPage;

/**
 * 数据服务
 *
 * @author lizhenlong
 * @date 2023/06/06
 */
public interface DataService {
    CommonPage<DataPageVO> selectPage(DataPageReq dataPageReq);
}
