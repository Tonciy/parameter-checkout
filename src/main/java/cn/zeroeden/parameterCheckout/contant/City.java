package cn.zeroeden.parameterCheckout.contant;

public enum City {

    CHINA("中国", 100),
    ENGLAND("英国", 200),
    AMERICA("美国", 300),
    JAPAN("日本", 400),
    EGYPT("埃及", 500);

    /**
     * 名称
     */
    String name;

    /**
     * 地区码
     */
    Integer code;


    City(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }
}
