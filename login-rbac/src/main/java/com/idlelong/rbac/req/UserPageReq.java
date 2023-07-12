package com.idlelong.rbac.req;

import com.idlelong.security.common.api.BaseComonPageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户页面请求
 *
 * @author lizhenlong
 * @date 2023/06/05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserPageReq extends BaseComonPageReq implements Serializable {

    private static final long serialVersionUID = 3164342564673377725L;

    /**
     * 用户名
     */
    private String username;

}
