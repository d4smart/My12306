package com.d4smart.my12306.common;

/**
 * Created by d4smart on 2017/6/30 17:39
 */
public class Const {

    public static final String LOGIN_USER = "login_user";

    public static final String PHONE = "phone";

    public static final String IDENTITY_NUMBER = "identity_number";

    public static final String EMAIL = "email";

    public interface Role {
        int ROLE_CUSTOMER = 0; // 普通用户
        int ROLE_ADMIN = 1; // 管理员
    }

    public interface UserStatus {
        int NORMAL = 0;
        int LOCKED = 1;
    }

    public enum OrderStatus {
        UNPAID(0, "未支付"),
        CANCELED(1, "已取消"),

        PAID(10, "已付款"),
        RETREAT(11, "已退票"),
        CHANGED(12, "改签"),

        FETCHED(20, "已取票");

        private int code;
        private String value;

        OrderStatus(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static OrderStatus codeOf(int code) {
            for(OrderStatus orderStatus : values()) {
                if(orderStatus.getCode() == code) {
                    return orderStatus;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }

}