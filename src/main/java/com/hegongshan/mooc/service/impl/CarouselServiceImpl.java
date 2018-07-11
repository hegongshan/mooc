package com.hegongshan.mooc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hegongshan.mooc.dao.ICarouselDAO;
import com.hegongshan.mooc.entity.Carousel;
import com.hegongshan.mooc.service.ICarouselService;

@Service("carouselService")
public class CarouselServiceImpl implements ICarouselService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CarouselServiceImpl.class);

	@Resource
	private ICarouselDAO carouselDAO;
	
	@Override
	public Integer saveCarousel(Carousel carousel) {
		try {
			//判断是否已经存在该优先级的轮播记录，若存在，则更新轮播记录
			Carousel targetCarousel = carouselDAO.getCarouselByPriority(carousel.getPriority());
			if(targetCarousel != null) {
				carousel.setCarouselId(targetCarousel.getCarouselId());
				return carouselDAO.updateCarousel(carousel);
			}
			//若不存在该优先级的轮播记录，则判断是否属于为4,3,2,1中的某一个，若属于，则插入数据到t_carousel中
			if(carousel.getPriority() == 4 || carousel.getPriority() ==3 || carousel.getPriority() == 2 ||carousel.getPriority() == 1) {
				return carouselDAO.saveCarousel(carousel);
			} else {
				return 0;
			}
		} catch (Exception e) {
			LOGGER.error("插入数据到t_carousel中失败",e);
			return -1;
		}
	}
	
	@Override
	public Integer removeCarouselByPriority(Integer priority) {
		try {
			
			int result = carouselDAO.removeCarouselByPriority(priority);
			if(priority == 4) {
				return result;
			}
			int updateRows = 0;
			for(int i = priority ; i < 4 ; i++) {
				Carousel carousel = carouselDAO.getCarouselByPriority(i+1);
				carousel.setPriority(i);
				updateRows += carouselDAO.updateCarousel(carousel);
			}
			return updateRows;
		} catch (Exception e) {
			LOGGER.error("从t_carousel中删除数据失败",e);
			return -1;
		}
	}

	@Override
	public List<Carousel> listCarousel() {
		try {
			return carouselDAO.listCarousel();
		} catch (Exception e) {
			LOGGER.error("从t_carousel中查询数据失败", e);
			return null;
		}
	}

}
