package com.lynxspa.sdm.core.services.media;

import java.awt.Image;

import org.springframework.stereotype.Service;

@Service
public class ImageMediaService {
	/**
	 * Escala una imagen de la forma más precisa a unas dimensions concretas
	 * 
	 * @param image La imagen
	 * @param width El ancho de la imagen final
	 * @param height El alto de la imagen final
	 * @return La imagen escalada
	 */
	public Image scale(Image image, int width, int height) {
		return image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
	}
	
	/**
	 * Devuelve el ratio de una imagen
	 * @param image
	 * @return ancho/alto
	 */
	public Integer findRatio(Image image) {
		return findRatio(image.getWidth(null), image.getHeight(null));
	}
	
	/**
	 * Devuelve el ratio de una imagen
	 * @param image
	 * @return ancho/alto
	 */
	public Integer findRatio(int width, int height) {
		return width / height;
	}
}
