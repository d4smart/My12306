package com.d4smart.my12306.common;

/**
 * Created by d4smart on 2017/6/30 17:39
 */
public class Const {

    public static final String LOGIN_USER = "login_user";

    public static final String PHONE = "phone";

    public static final String IDENTITY_NUMBER = "identity_number";

    public static final String EMAIL = "email";

    public static final String DEFAULT_PASSWORD = "654123";

    public interface Role {
        int ROLE_CUSTOMER = 0; // 普通用户
        int ROLE_ADMIN = 1; // 管理员
    }

    public interface UserStatus {
        int NORMAL = 0;
        int LOCKED = 1;
    }

    public interface PassengerType {
        String ADULT = "成人";
        String CHILD = "儿童";
        String STUDENT = "学生";
    }

    public interface SaleMethod {
        String ONLINE = "网络";
        String OFFICE = "售票处";
        String PHONE = "电话";
        String STATION = "车站";
    }

    public enum OrderStatus {
        UNPAID(0, "未支付"),
        CANCELED(1, "已取消"),

        RETREATED(11, "已退款"),

        PAID(20, "已付款");

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

    public enum TicketStatus {
        UNPAID(0, "未支付"),
        CANCELED(1, "已取消"),

        RETREATED(11, "已退票"),
        CHANGED(12, "改签"),

        AVAILABLE(20, "可以使用");

        private int code;
        private String value;

        TicketStatus(int code, String value) {
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

        public static TicketStatus codeOf(int code) {
            for(TicketStatus ticketStatus : values()) {
                if(ticketStatus.getCode() == code) {
                    return ticketStatus;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }

    public enum FareRatio {
        FIRST("一等座", 4.0),
        SECOND("二等座", 2.5),
        HARDSEAT("硬座", 1.0),
        SOFRSEAT("软座", 1.5),
        HARDSLEEPER("硬卧", 2.0),
        SOFTSLEEPER("软卧", 3.0),
        NOSEAT("无座", 1.0);

        private String seatType;
        private double ratio;

        FareRatio(String seatType, double ratio) {
            this.seatType = seatType;
            this.ratio = ratio;
        }

        public String getSeatType() {
            return seatType;
        }

        public double getRatio() {
            return ratio;
        }

        public static double getRatio(String seatType) {
            for(FareRatio fareRatio : values()) {
                if(fareRatio.getSeatType().equals(seatType)) {
                    return fareRatio.getRatio();
                }
            }
            return 0;
        }
    }
}
