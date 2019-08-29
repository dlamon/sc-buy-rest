package cn.net.liaowei.sc.buy.rest.controller;


import cn.net.liaowei.sc.buy.rest.domain.ResultVO;
import cn.net.liaowei.sc.buy.rest.util.ResultUtil;
import cn.net.liaowei.sc.order.rpc.client.OrderService;
import cn.net.liaowei.sc.order.rpc.common.OrderDTO;
import com.alipay.sofa.rpc.registry.consul.ConsulConstants;
import com.alipay.sofa.runtime.api.annotation.SofaParameter;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author LiaoWei
 */
@RestController
@Slf4j
@Api(tags = "/order", description = "订单服务")
@RequestMapping("/order")
public class BuyController {
    @SofaReference(
            binding = @SofaReferenceBinding(
                    bindingType = "bolt",
                    parameters = @SofaParameter(key = ConsulConstants.CONSUL_SERVICE_NAME_KEY, value = "order-rpc")
            ),
            jvmFirst = false
    )
    private OrderService orderService;


    @PostMapping("/create")
    @ApiOperation("创建订单")
    public ResultVO<String> create(@Valid @RequestBody @ApiParam("订单数据") OrderDTO orderDTO) {
        String orderId = orderService.create(orderDTO);
        return ResultUtil.success(orderId);

    }

    @DeleteMapping("/delete")
    @ApiOperation("删除订单")
    public ResultVO<List<String>> delete(@RequestParam("ids") @NotNull(message="订单编号不能为空")  @ApiParam("订单编号") List<String> ids) {
        return ResultUtil.success(orderService.delete(ids));
    }

    @PutMapping("/finish")
    @ApiOperation("完结订单")
    public ResultVO<List<String>> finish(@RequestBody @NotNull(message="订单编号不能为空")  @ApiParam("订单编号") List<String> ids) {
        return ResultUtil.success(orderService.finish(ids));
    }
}