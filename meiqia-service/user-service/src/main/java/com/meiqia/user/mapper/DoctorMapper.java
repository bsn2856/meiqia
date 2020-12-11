package com.meiqia.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meiqia.user.entity.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiusan
 * @since 2020-12-08
 */
@Mapper
public interface DoctorMapper extends BaseMapper<Doctor> {
    Page<Doctor> getPage(Page<Doctor> page, @Param("ew") QueryWrapper<Doctor> wrapper);

}
