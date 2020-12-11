package com.meiqia.user.service.impl;

import com.meiqia.user.entity.Level;
import com.meiqia.user.mapper.LevelMapper;
import com.meiqia.user.service.LevelService;
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
 *  服务实现类
 * </p>
 *
 * @author jiusan
 * @since 2020-12-08
 */
@Service
public class LevelServiceImpl extends ServiceImpl<LevelMapper, Level> implements LevelService {

    @Override
    public PageResult<Level> page(Integer currPage, Integer pageSize, SearchVO vo) {
        // 分页
        Page<Level> levelPage = new Page<>(currPage, pageSize);

        // 条件查询
        QueryWrapper<Level> wrapper = new QueryWrapper<>();

        if (Objects.nonNull(vo)) {
            String keyWord = vo.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                wrapper.like("name", keyWord);
            }
        }
        levelPage = page(levelPage, wrapper);
        return PageResult.of(levelPage.getRecords(), levelPage.getTotal());
    }

    @Override
    public boolean saveLevel(Level level) {
        return save(level);
    }

    @Override
    public boolean updateLevel(Level level) {
        return updateById(level);
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
