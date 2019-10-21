package com.icaballero.proyectofinal.Utils;

import java.util.Random;

import android.graphics.Bitmap;
/**
 * 
 * @author Cristian Bautista, Daniel Córdoba,Ismael Caballero
 * Clase DisparoOvni
 *
 */
public class DisparosOvni extends Thread {
	public int velocidad = new Random().nextInt(20)+45;
	public int xInit = 0;
	public int yInit = 0;
	public int color = 0;
	public int radius = 0;
	public boolean alive = true;
	public int cont = 0;
	public int tempRadius = 0;
	public int viewWidth;
	public Bitmap laser;
	/**
	 * Constructor
	 * @param xInit
	 * @param yInit
	 * @param radius
	 * @param viewWidth
	 * @param laser
	 */
	 public DisparosOvni(int xInit,int yInit,int radius, int viewWidth, Bitmap laser){
		this.xInit = xInit;
		this.yInit = yInit;
		this.color = color;
		this.radius = radius;
		this.viewWidth = viewWidth;
		this.laser = laser;
	}
	
	@Override
	public void run() {

		while(alive){
			
			this.xInit-=viewWidth/100;				
			
			try {
				sleep(velocidad);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Se pierde el disparo cuando desaparece de la pantalla
			if(this.xInit<=0-this.radius*2){
				alive=false;
			}
		
			
		}
		super.run();
	}
	}
	
	
	
	
