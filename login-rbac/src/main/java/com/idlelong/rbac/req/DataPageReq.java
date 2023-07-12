package com.idlelong.rbac.req;

import com.idlelong.security.common.api.BaseComonPageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 数据页面请求
 *
 * @author lizhenlong
 * @date 2023/06/06
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DataPageReq extends BaseComonPageReq implements Serializable {

    private static final long serialVersionUID = 6124095715180866487L;
}
