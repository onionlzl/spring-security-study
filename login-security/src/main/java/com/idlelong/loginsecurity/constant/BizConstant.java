package com.idlelong.loginsecurity.constant;

/**
 * 业务常量
 *
 * @author lizhenlong
 * @date 2023/06/04
 */
public class BizConstant {

    public enum ResouceType {

        /**
         * 菜单类型 MENU_TYPE 0
         * 接口权限 INTER_FACE_TYPE 1
         */
        MENU_TYPE(0,"菜单权限"),
        INTER_FACE_TYPE(1,"接口权限"),
        ;

        ResouceType(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        /**
         * 代码
         */
        private Integer code;

        /**
         * 信息
         */
        private String info;

        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }
}
