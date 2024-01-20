package org.wy.online_reading.core.comme.constant;

/**
 * @author 89589
 */
public class CommonConsts {
    /**
     * yes
     * */
    public static final Integer YES = 1;

    /**
     * no
     * */
    public static final Integer NO = 0;

    /**
     * sex const
     * */
    public enum SexEnum{

        /**
         * male
         * */
        MALE(0,"male"),

        /**
         * female
         * */
        FEMALE(1,"female");

        SexEnum(int code,String desc){
            this.code = code;
            this.desc = desc;
        }

        private int code;
        private String desc;

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

    }
}
