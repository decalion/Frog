package com.icaballero.proyectofinal.Utils;

import android.graphics.Bitmap;



/**
 * Clase que Genera la Bola de luz del jefe Final
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */

public class LightBall {
	int x;
	int y;
	int radius;
	int viewWidth;
	Bitmap lightball;
	
	
	
	
	/**
	 * Constructor
	 * @param x posicion
	 * @param y posicion
	 * @param radius
	 * @param viewWidth ancho de la view
	 * @param lightBall imagen
	 */

	public LightBall(int x,int y,int radius,int viewWidth,Bitmap lightBall) {
		this.x=x;
		this.y=y;
		this.radius=radius/2;
	    this.viewWidth=viewWidth;
	    this.lightball=lightBall;
		
	}

}
