package ${package}.response;

/**
 * @author liusy
 */

public enum ResponseType {

    /**
     * 成功
     */
    OK(1, "请求成功"),
    /**
     * 失败
     */
    BIZ_EXCEPTION(2, "自定义异常"),
    /**
     * 系统异常
     */
    SYSTEM_ERROR(3, "系统错误");

    /**
     * 状态码
     */
    private Integer stateCode;
    /**
     * 状态码信息
     */
    private String msg;

    ResponseType(Integer stateCode, String msg) {
        this.stateCode = stateCode;
        this.msg = msg;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public String getMsg() {
        return msg;
    }

}
