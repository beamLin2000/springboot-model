package com.gxa.modules.content.carousel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.content.carousel.entity.Carousel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarouselMapper extends BaseMapper<Carousel> {
        void deleteByidBatch(List<Carousel> carousel);
}
