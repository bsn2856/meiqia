package com.meiqia.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meiqia.content.entity.Content;
import com.meiqia.content.mapper.ContentMapper;
import com.meiqia.content.service.ContentService;
import com.meiqia.content.struct.PageResult;
import com.meiqia.content.util.StringUtils;
import com.meiqia.content.vo.SearchVO;
import com.meiqia.meiqia.common.constant.KeyConstants;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiusan
 * @since 2020-12-09
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {
    @Autowired
    private RedisTemplate redisTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentService.class);
    /**
     *
     */
    @Autowired
    private CacheManager cacheManager;
    @Override
    public PageResult<Content> page(Integer currPage, Integer pageSize, SearchVO vo) {
        // 分页
        Page<Content> contentPage = new Page<>(currPage, pageSize);

        // 条件查询
        QueryWrapper<Content> wrapper = new QueryWrapper<>();

        if (Objects.nonNull(vo)) {
            String keyWord = vo.getKeyWord();
            if (StringUtils.isNotEmpty(keyWord)) {
                wrapper.like("name", keyWord);
            }
        }
        contentPage = page(contentPage, wrapper);
        return PageResult.of(contentPage.getRecords(), contentPage.getTotal());
    }

    @Override
    public boolean saveContent(Content content) {
        return save(content);
    }

    @Override
    public boolean updateContent(Content content) {
        return updateById(content);
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
    public List<Content> getList() {
        // 获取缓存区域
        Cache cache = cacheManager.getCache("contentList");
        List<Content> result  =null;
        // 根据键获取元素
        Element element = cache.get(KeyConstants.KEY_CONTENTS);
        if(element!=null){
            // 解析数据
            result = (List<Content>) element.getObjectValue();
            if(!CollectionUtils.isEmpty(result)){
                LOGGER.info("data from eh");
                return  result;
            }
        }

        BoundListOperations listOps = redisTemplate.boundListOps(KeyConstants.KEY_CONTENTS);
        result = listOps.range(0, -1);

        if(!CollectionUtils.isEmpty(result)){
            // 放入ehcache
            cache.put(new Element(KeyConstants.KEY_CONTENTS,result));
            LOGGER.info("data from redis");
            return  result;
        }
        LOGGER.info("data from db");
        // 查询数据库
        QueryWrapper<Content> wrapper = new QueryWrapper<>();
        wrapper.eq("status",1).orderByAsc("sort");
        result = list(wrapper);
        if(!CollectionUtils.isEmpty(result)){
            // 放入ehcache
            cache.put(new Element(KeyConstants.KEY_CONTENTS,result));
            //转化成数组 保存到 redis
            listOps.leftPushAll(result.toArray(new Content[result.size()]));
        }

        return result;
    }
}
