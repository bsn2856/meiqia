package com.meiqia.user.service.impl;

import com.meiqia.user.entity.Doctor;
import com.meiqia.user.mapper.DoctorMapper;
import com.meiqia.user.service.DoctorService;
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
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {

    @Override
    public PageResult<Doctor> page(Integer currPage, Integer pageSize, SearchVO vo) {
        // 分页
        Page<Doctor> doctorPage = new Page<>(currPage, pageSize);

        // 条件查询
        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();

        if (Objects.nonNull(vo)) {
            String keyWord = vo.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                wrapper.like("name", keyWord);
            }
        }
        doctorPage = page(doctorPage, wrapper);
        return PageResult.of(doctorPage.getRecords(), doctorPage.getTotal());
    }

    @Override
    public boolean saveDoctor(Doctor doctor) {
        return save(doctor);
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        return updateById(doctor);
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
    public PageResult<Doctor> getByOne(int cpage,Long roomId) {
        // 分页
        Page<Doctor> doctorPage = new Page<>(cpage, 10);

        // 条件查询
        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();
        String l=roomId.toString();
        if (StringUtils.isNotEmpty(l)) {
            wrapper.eq("room_id",roomId);
        }
        doctorPage = baseMapper.getPage(doctorPage, wrapper);
        return PageResult.of(doctorPage.getRecords(), doctorPage.getTotal());
    }
}
