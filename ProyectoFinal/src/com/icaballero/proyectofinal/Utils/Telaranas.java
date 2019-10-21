package com.icaballero.proyectofinal.Utils;

import java.util.Random;

import android.graphics.Bitmap;



/**
 * Esta clase se encarga de Generar las Telaranas que disparan las aranas.
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */
public class Telaranas extends Thread {
	public int velocidad = new Random().nextInt(20)+45;
	//posiciones donde empieza la bola
	public int x = 0;
	public int y = 0;
	public int color = 0;
	public int radius = 0;
	public int viewWidth;
	public Bitmap telearana;
	public boolean alive = true;
	public boolean pause = false;

	
	
	/**
	 * Constructor de la Telearana
	 * @param x la posicion x
	 * @param y la posicion y
	 * @param radius el radio
	 * @param viewWidth el Ancho de la View
	 * @param telearana la foto
	 */
	public Telaranas(int x,int y,int color,int radius,int viewWidth, Bitmap telearana) {
		
		this.x = x;
		this.y = y;
		this.color = color;
		this.radius = radius;
		this.viewWidth = viewWidth;
		this.telearana = telearana;
	
	}
	
	@Override
	public void run() {

		while(alive){
			if(!pause){
			
			this.x -=viewWidth/100;				
			
			try {
				sleep(velocidad);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Se pierde el disparo cuando desaparece de la pantalla
			if(this.x<=0-this.radius*2){
				alive=false;
			}
			}
		}
		super.run();
	}

}
