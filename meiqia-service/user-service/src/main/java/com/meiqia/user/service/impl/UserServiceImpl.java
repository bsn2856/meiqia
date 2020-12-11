package com.meiqia.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meiqia.meiqia.common.asserts.Asserts;
import com.meiqia.meiqia.common.constant.Constants;
import com.meiqia.meiqia.common.util.CodecUtils;
import com.meiqia.user.entity.User;
import com.meiqia.user.mapper.UserMapper;
import com.meiqia.user.service.UserService;
import com.meiqia.user.struct.PageResult;
import com.meiqia.user.util.StringUtils;
import com.meiqia.user.vo.SearchVO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiusan
 * @since 2020-12-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public PageResult<User> page(Integer currPage, Integer pageSize, SearchVO vo) {
        // 分页
        Page<User> userPage = new Page<>(currPage, pageSize);

        // 条件查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if (Objects.nonNull(vo)) {
            String keyWord = vo.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                wrapper.like("name", keyWord);
            }
        }
        userPage = page(userPage, wrapper);
        return PageResult.of(userPage.getRecords(), userPage.getTotal());
    }

    @Override
    public boolean saveUser(User user) {
        return save(user);
    }

    @Override
    public boolean updateUser(User user) {
        return updateById(user);
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
    public boolean register(String name, String pwd, Integer role) {
        // 用户角色
        Asserts.isNotNull(role,Constants.User.ROLE_NOT_EMPTY);
        // 查询当前用户是否存在
        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        lambda.eq(User::getName,name);
        User userFormDB = getOne(lambda);

        Asserts.isNull(userFormDB, Constants.User.USER_EXIST);

        User user = new User();
        user.setName(name);
        // 颜值
        user.setSalt(CodecUtils.md5Hex(name,""));
        // 加密
        user.setPassword(CodecUtils.md5Hex(pwd,user.getSalt()));

        user.setCreateTime(new Date());

        user.setStatus(1);

        user.setRole(role);

        return save(user);
    }

    @Override
    public User getDetails(Long id) {
        User user = getById(id);
        Asserts.isNotNull(user,Constants.User.NOT_FOUND);
        Integer role = user.getRole();


        return null;
    }
}
