package com.meiqia.user.service.impl;

import com.meiqia.user.entity.BalanceTrade;
import com.meiqia.user.mapper.BalanceTradeMapper;
import com.meiqia.user.service.BalanceTradeService;
import com.meiqia.user.struct.PageResult;
import com.meiqia.user.vo.SearchVO;
import com.meiqia.user.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Objects;
import java.util.List;

/**
 * <p>
 * 交易明细 服务实现类
 * </p>
 *
 * @author jiusan
 * @since 2020-12-08
 */
@Service
public class BalanceTradeServiceImpl extends ServiceImpl<BalanceTradeMapper, BalanceTrade> implements BalanceTradeService {

    @Override
    public PageResult<BalanceTrade> page(Integer currPage, Integer pageSize, SearchVO vo) {
        // 分页
        Page<BalanceTrade> balanceTradePage = new Page<>(currPage, pageSize);

        // 条件查询
        QueryWrapper<BalanceTrade> wrapper = new QueryWrapper<>();

        if (Objects.nonNull(vo)) {
            String keyWord = vo.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                wrapper.like("name", keyWord);
            }
        }
        balanceTradePage = page(balanceTradePage, wrapper);
        return PageResult.of(balanceTradePage.getRecords(), balanceTradePage.getTotal());
    }

    @Override
    public boolean saveBalanceTrade(BalanceTrade balanceTrade) {
        return save(balanceTrade);
    }

    @Override
    public boolean updateBalanceTrade(BalanceTrade balanceTrade) {
        return updateById(balanceTrade);
    }

    @Override
    public boolean delById(Long id) {
        return removeById(id);
    }

    @Override
    public boolean delByIds(List<Long> ids) {
        return removeByIds(ids);
    }

    @Override
    public BalanceTrade getLastRecord(Long userId) {
        QueryWrapper<BalanceTrade> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.orderByDesc("created");
        List<BalanceTrade> list = list(wrapper);
        return list.get(0);
    }
}
/**
 *
 * jvm优化
mysql优化
 nginx反向代理
 redis集群
tomcat优化
 底层算法
 spring常用的几个标签
 spring的动态代理
 所以框架的运行原理

 */