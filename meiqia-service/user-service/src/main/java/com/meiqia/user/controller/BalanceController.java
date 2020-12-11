package com.meiqia.user.controller;


import com.meiqia.user.entity.Balance;
import com.meiqia.user.service.BalanceService;
import com.meiqia.user.struct.PageResult;
import com.meiqia.user.struct.Result;
import com.meiqia.user.vo.SearchVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户余额 前端控制器
 * </p>
 *
 * @author jiusan
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/balance")
public class BalanceController {
    @Resource
	private BalanceService balanceService;

    @GetMapping("/{id}")
    public Result<Balance> getById(@PathVariable("id") Long id) {
        return Result.ok(balanceService.getById(id));
    }

    @GetMapping
    public Result<List<Balance>> list() {
        return Result.ok(balanceService.list());
    }

    @PostMapping
    public Result add(@RequestBody Balance balance) {
        return balanceService.saveBalance(balance) ? Result.ok() : Result.error();
    }

    @PutMapping
    public Result update(@RequestBody Balance balance) {
        return balanceService.updateBalance(balance) ? Result.ok() : Result.error();
    }

    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") Long id) {
        return balanceService.delById(id) ? Result.ok() : Result.error();
    }

    @PostMapping("search/{currPage}/{pageSize}")
    public Result<PageResult<Balance>> page(@PathVariable("currPage") Integer currPage,
                                            @PathVariable("pageSize") Integer pageSize,
                                            @RequestBody(required = false) SearchVO vo) {
        return Result.ok(balanceService.page(currPage, pageSize, vo));
    }

    @PostMapping("delByIds")
    public Result delByIds(@RequestBody List<Long> ids) {
        return balanceService.delByIds(ids) ? Result.ok() : Result.error();
    }

}
