package com.icaballero.proyectofinal.Utils;
import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;

/**
 * Clase de tipo FireBall, que contiene las variables para crear un objeto con las variables necesarias
 * para crear una bola de fuego. Extiende de Thread
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */
	public class FireBall extends Thread{
		public Random generator = new Random();
		public int velocidad = generator.nextInt(40)+20;
		//posiciones donde empieza la bola
		public Bitmap bmp;
		public int xInit = 0;
		public int yInit = 0;
		public int radius = 0;
		public boolean alive = true;
		public int cont = 0;
		public int tempRadius = 0;
		public long timeStarted;
		public long creatingTime = Constantes.creatingFireBallTime;
		//ancho de la vista
		public int viewWidth;
		public boolean moving = false;
		
		/**
		 * Constructor de la clase. Se le pasan los parámetros necesarios
		 * @param xInit
		 * @param yInit
		 * @param radius
		 * @param viewWidth
		 * @param bmp
		 */
		public FireBall(int xInit,int yInit, int radius, int viewWidth, Bitmap bmp){
			this.xInit = xInit;
			this.yInit = yInit;
			this.viewWidth = viewWidth;
			this.bmp = bmp;
			 this.radius = bmp.getWidth()/2;
		}
		
		@Override
		public void run() {

			while(this.alive){
				
				//Se mueve a través de la pantalla, en proporción a su tamaño, en porcentajes
				if(moving){
				this.xInit -= (int)viewWidth/100*0.5;				
				
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
				
			}

		}
		
		//Cambia la velocidad de la bola
		public void modifyVelocidad(int change){
			if(this.velocidad+change>0)
			this.velocidad += change;
		}
		
		//Modifica la dirección
		public void modifyDirection(int y){
			if(this.yInit+y>0)
			this.yInit+=y;
		}
		
		//Crea una lista con los puntos geomÃ©tricos bÃ¡sicos de la bola
		public ArrayList<PuntoGeometrico> getPuntosGeometricos(){
			ArrayList<PuntoGeometrico> puntos = new ArrayList<PuntoGeometrico>();
			
			puntos.add(new PuntoGeometrico(this.xInit-radius, this.yInit));
			puntos.add(new PuntoGeometrico(this.xInit+radius, this.yInit));
			puntos.add(new PuntoGeometrico(this.xInit, this.yInit-radius/2));
			puntos.add(new PuntoGeometrico(this.xInit, this.yInit+radius/2));			
			puntos.add(new PuntoGeometrico(this.xInit+radius, this.yInit+radius));
			puntos.add(new PuntoGeometrico(this.xInit-radius, this.yInit-radius));
			puntos.add(new PuntoGeometrico(this.xInit+radius, this.yInit-radius));
			puntos.add(new PuntoGeometrico(this.xInit-radius, this.yInit+radius));
			
			return puntos;
		}
		
	}
