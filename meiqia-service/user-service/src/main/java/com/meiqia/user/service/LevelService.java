package com.meiqia.user.service;

import com.meiqia.user.entity.Level;
import com.meiqia.user.struct.PageResult;
import com.meiqia.user.vo.SearchVO;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiusan
 * @since 2020-12-08
 */
public interface LevelService extends IService<Level> {

    /**
    * 分页列表查询
    * @param currPage
    * @param pageSize
    * @param vo
    * @return
    */
    PageResult<Level> page(Integer currPage, Integer pageSize, SearchVO vo);

    /**
    * 添加
    * @param level
    * @return
    */
    boolean saveLevel(Level level);

    /**
    * 修改
    * @param level
    * @return
    */
    boolean updateLevel(Level level);

    /**
    * 根据ID删除
    * @param id
    * @return
    */
    boolean delById(Long id);

    /**
    * 根据主键IDs批量删除
    * @param ids
    * @return
    */
    boolean delByIds(List<Long> ids);

}
