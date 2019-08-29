package cn.net.liaowei.sc.buy.rest.domain;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author LiaoWei
 */
@Data
public class ResultVO<T> {
    private String code;

    private String message;

    private T data;
}
