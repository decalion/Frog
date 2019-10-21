package com.icaballero.proyectofinal.Utils;
/**
 * 
 */
import java.util.Random;

import android.graphics.Bitmap;

/**
 * Clase disparo que extiende de Thread. Contiene los datos necesarios para dibujar un disparo en el canvas.
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */
	public class Disparo extends Thread{
		public int velocidad = new Random().nextInt(20)+10;
		//posiciones donde empieza la bola
		public int xInit = 0;
		public int yInit = 0;
		public int color = 0;
		public int radius = 0;
		public boolean alive = true;
		public int cont = 0;
		public int tempRadius = 0;
		public int viewWidth;
		public Bitmap escupitajo;
		public boolean pause = false;
		
		/**
		 * Constructor de la clase, con los parámetros necesarios para iniciar el objeto
		 * @param xInit
		 * @param yInit
		 * @param color
		 * @param radius
		 * @param viewWidth
		 * @param escupitajo
		 */
		public Disparo(int xInit,int yInit, int color, int radius, int viewWidth, Bitmap escupitajo){
			this.xInit = xInit;
			this.yInit = yInit;
			this.color = color;
			this.radius = radius;
			this.viewWidth = viewWidth;
			this.escupitajo = escupitajo;
		}
		
		@Override
		public void run() {

			while(alive){
				
				if(!pause){
				//Movimiento en porcentaje del disparo, en relación al ancho de la pantalla
				this.xInit +=viewWidth/100;				
				
				try {
					sleep(velocidad);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Se pierde el disparo cuando desaparece de la pantalla
				if(this.xInit>viewWidth){
					alive=false;
				}
				}
			}
			super.run();
		}
		
	}
