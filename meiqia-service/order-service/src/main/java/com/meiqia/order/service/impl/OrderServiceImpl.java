package com.meiqia.order.service.impl;

import com.meiqia.order.entity.Order;
import com.meiqia.order.mapper.OrderMapper;
import com.meiqia.order.service.OrderService;
import com.meiqia.order.struct.PageResult;
import com.meiqia.order.vo.SearchVO;
import com.meiqia.order.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Objects;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiusan
 * @since 2020-12-08
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public PageResult<Order> page(Integer currPage, Integer pageSize, SearchVO vo) {
        // 分页
        Page<Order> orderPage = new Page<>(currPage, pageSize);

        // 条件查询
        QueryWrapper<Order> wrapper = new QueryWrapper<>();

        if (Objects.nonNull(vo)) {
            String keyWord = vo.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                wrapper.like("name", keyWord);
            }
        }
        orderPage = page(orderPage, wrapper);
        return PageResult.of(orderPage.getRecords(), orderPage.getTotal());
    }

    @Override
    public boolean saveOrder(Order order) {
        return save(order);
    }

    @Override
    public boolean updateOrder(Order order) {
        return updateById(order);
    }

    @Override
    public boolean delById(Long id) {
        return removeById(id);
    }

    @Override
    public boolean delByIds(List<Long> ids) {
        return removeByIds(ids);
    }
}
