package com.gxa.modules.content.carousel.service.impl;

import com.baomidou.mybatisplus.core.conditions.interfaces.Func;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.content.carousel.entity.Carousel;
import com.gxa.modules.content.carousel.mapper.CarouselMapper;
import com.gxa.modules.content.carousel.service.CarouselService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper,Carousel>  implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public PageUtils queryByPage01(Map<String, Object> params) {
        IPage<Carousel> page = this.page(new Query<Carousel>().getPage(params));
        PageUtils pageUtils = new PageUtils(page);
        return pageUtils;
    }

    @Override
    public PageUtils queryByPageByCarouselTitle(Map<String, Object> params) {
        String carouselTitle = (String) params.get("carouselTitle");
        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");

        //apply
        IPage<Carousel> page = this.page(new Query<Carousel>().getPage(params),
                new QueryWrapper<Carousel>().like(StringUtils.isNotEmpty(carouselTitle),"carousel_title",carouselTitle)
                .ge(StringUtils.isNotEmpty(beginTime),"date",beginTime)
                .le(StringUtils.isNotEmpty(endTime),"date",endTime));



        return new PageUtils(page);
    }

    @Override
    public void carouselAdd(Carousel carousel) {
        this.baseMapper.insert(carousel);
    }

    @Override
    public void carouselDelete(Integer id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void carouselUpdate(Carousel carousel) {
        UpdateWrapper<Carousel>wrapper = new UpdateWrapper<>();
        wrapper.eq("id",carousel.getId()).set(null,carousel);
        this.baseMapper.updateById(carousel);
    }



    @Override
    public void deleteByidBatch(List<Carousel> carousel) {
        this.carouselMapper.deleteByidBatch(carousel);
    }
}
