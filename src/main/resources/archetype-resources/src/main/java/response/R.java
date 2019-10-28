package ${package}.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

import static ${package}.response.ResponseType.OK;


/**
 * @author liusy
 */

@ApiModel
@Data
public class R<P> implements Serializable {
    private static final long serialVersionUID = 1L;

    private R() {
        this(OK.getStateCode(), OK.getMsg(), null);
    }

    private R(Integer stateCode, String msg, P payload) {
        this.stateCode = stateCode;
        this.msg = msg;
        this.payload = payload;
    }

    private R(String msg, P payload) {
        this(OK.getStateCode(), msg, payload);
    }

    private R(P payload) {
        this(OK.getStateCode(), OK.getMsg(), payload);
    }

    private R(Integer stateCode, String msg) {
        this(stateCode, msg, null);
    }

    private R(String msg) {
        this(OK.getStateCode(), msg, null);
    }

    /**
     * 状态码
     */
    @ApiModelProperty(value = "返回状态码，1 请求成功 2 异常详情看返回提示信息 3 请求失败", example = "1")
    private Integer stateCode;

    /**
     * 提示信息
     */
    @ApiModelProperty(value = "返回提示信息，")
    private String msg;

    /**
     * 返回内容
     */
    @ApiModelProperty(value = "返回数据")
    private P payload;


    public static <P> R<P> of() {
        return new R<>();
    }

    public static <P> R<P> of(Integer stateCode, String msg, P payload) {
        return new R<>(stateCode, msg, payload);
    }

    public static <P> R<P> of(String msg, P payload) {
        return new R<>(msg, payload);
    }

    public static <P> R<P> of(P payload) {
        return new R<>(payload);
    }

    public static <P> R<P> of(Integer stateCode, String msg) {
        return new R<>(stateCode, msg);
    }

    public static <P> R<P> of(String msg) {
        return new R<>(msg);
    }
}
