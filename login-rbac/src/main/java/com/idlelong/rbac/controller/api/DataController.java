package com.idlelong.rbac.controller.api;

import com.idlelong.rbac.req.DataPageReq;
import com.idlelong.rbac.service.DataService;
import com.idlelong.rbac.vo.DataPageVO;
import com.idlelong.security.common.api.CommonPage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 数据控制器
 *
 * @author lizhenlong
 * @date 2023/06/06
 */
@RestController
@RequestMapping("/API/data")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    /**
     * 获取页面
     *
     * @param dataPageReq 数据页面请求
     * @return {@link CommonPage}<{@link DataPageVO}>
     */
    @PostMapping("/page")
    public CommonPage<DataPageVO> getPage(@Valid @RequestBody DataPageReq dataPageReq) {
        return dataService.selectPage(dataPageReq);
    }

}
