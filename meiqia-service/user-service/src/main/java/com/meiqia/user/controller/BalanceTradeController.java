package com.meiqia.user.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

import com.meiqia.user.service.BalanceTradeService;
import com.meiqia.user.entity.BalanceTrade;
import com.meiqia.user.struct.PageResult;
import com.meiqia.user.struct.Result;
import com.meiqia.user.vo.SearchVO;

import java.util.List;

/**
 * <p>
 * 交易明细 前端控制器
 * </p>
 *
 * @author jiusan
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/balance-trade")
public class BalanceTradeController {
    @Resource
	private BalanceTradeService balanceTradeService;

    @GetMapping("/{id}")
    public Result<BalanceTrade> getById(@PathVariable("id") Long id) {
        return Result.ok(balanceTradeService.getById(id));
    }

    @GetMapping
    public Result<List<BalanceTrade>> list() {
        return Result.ok(balanceTradeService.list());
    }

    @PostMapping
    public Result add(@RequestBody BalanceTrade balanceTrade) {
        return balanceTradeService.saveBalanceTrade(balanceTrade) ? Result.ok() : Result.error();
    }

    @PutMapping
    public Result update(@RequestBody BalanceTrade balanceTrade) {
        return balanceTradeService.updateBalanceTrade(balanceTrade) ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Long id) {
        return balanceTradeService.delById(id) ? Result.ok() : Result.error();
    }

    @PostMapping("search/{currPage}/{pageSize}")
    public Result<PageResult<BalanceTrade>> page(@PathVariable("currPage") Integer currPage,
                                            @PathVariable("pageSize") Integer pageSize,
                                            @RequestBody(required = false) SearchVO vo) {
        return Result.ok(balanceTradeService.page(currPage, pageSize, vo));
    }

    @PostMapping("delByIds")
    public Result delByIds(@RequestBody List<Long> ids) {
        return balanceTradeService.delByIds(ids) ? Result.ok() : Result.error();
    }

}
