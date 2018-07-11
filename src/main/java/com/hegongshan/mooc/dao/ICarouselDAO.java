package com.hegongshan.mooc.dao;

import java.util.List;

import javax.annotation.Resource;

import com.hegongshan.mooc.entity.Carousel;

@Resource
public interface ICarouselDAO {
	
	Integer saveCarousel(Carousel carousel) throws Exception;

	Integer removeCarouselByPriority(Integer priority) throws Exception;

	Integer updateCarousel(Carousel carousel) throws Exception;

	Carousel getCarouselByPriority(Integer priority) throws Exception;

	List<Carousel> listCarousel() throws Exception;

	//Integer countCarousel() throws Exception;
}
