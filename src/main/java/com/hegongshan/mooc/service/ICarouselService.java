package com.hegongshan.mooc.service;

import java.util.List;

import com.hegongshan.mooc.entity.Carousel;

public interface ICarouselService {
	Integer saveCarousel(Carousel carousel);

	List<Carousel> listCarousel();
	
	Integer removeCarouselByPriority(Integer priority);
}
