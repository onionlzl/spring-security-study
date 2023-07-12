package com.idlelong.security.common.api;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;

/**
 * 基本分页请求参数
 *
 * @author lizhenlong
 * @date 2023/06/05
 */
@Data
public abstract class BaseComonPageReq {

    /**
     * 页面大小
     */
    @Range(min = 1,max = 500)
    private long pageSize = 10;

    /**
     * 当前页码
     */
    @Range(min = 1,max = 20000)
    private long pageNum = 1;

    /**
     * 排序字段
     */
    private String orderItem = "";


    public static<T> Page<T> restPageReq(BaseComonPageReq req){
        Page<T> page = new Page<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn(req.getOrderItem());
        page.setCurrent(req.getPageNum()).setSize(req.getPageSize()).addOrder(orderItem);
        return page;
    }
}
