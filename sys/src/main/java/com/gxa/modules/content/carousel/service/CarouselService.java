package com.gxa.modules.content.carousel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.content.carousel.entity.Carousel;

import java.util.List;
import java.util.Map;

public interface CarouselService extends IService<Carousel> {
    PageUtils queryByPage01(Map<String,Object>params);
    PageUtils queryByPageByCarouselTitle(Map<String,Object> params);
    void carouselAdd(Carousel carousel);
    void carouselDelete(Integer id);
    void carouselUpdate(Carousel carousel);
    void deleteByidBatch(List<Carousel> carousel);
}
