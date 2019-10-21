package com.icaballero.proyectofinal.Utils;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;



/**
 * Clase que genera la Bola de Fuego Grande del Final Boss
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */


public class GreatFirebal extends Thread{
	
	
	public int x;
	public int y;
	public int radius;
	public int viewWidth;
	public Bitmap greatFireball;
	
	public Random generator = new Random();
	public int velocidad = generator.nextInt(40)+20;
	public boolean alive=true;
	public boolean moving = false;
	public long timeStarted;

	public GreatFirebal(int x,int y,int radius,int viewWidth,Bitmap greatFireball) {
		this.x=x;
		this.y=y;
		this.greatFireball=greatFireball;
		this.viewWidth=viewWidth;
		this.radius=greatFireball.getWidth()/2;
		
		
	}
	  @Override
	public void run() {
		while(alive){
			
			if(moving){
				this.x -= (int)viewWidth/100*1;				
				
				try {
					sleep(38);
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
	
	
	//Crea una lista con los puntos geometricos basicos de la redonda
	public ArrayList<PuntoGeometrico> getPuntosGeometricos(){
		ArrayList<PuntoGeometrico> puntos = new ArrayList<PuntoGeometrico>();
		
		puntos.add(new PuntoGeometrico(this.x-radius, this.y));
		puntos.add(new PuntoGeometrico(this.x+radius, this.y));
		puntos.add(new PuntoGeometrico(this.x, this.y-radius/2));
		puntos.add(new PuntoGeometrico(this.x, this.y+radius/2));			
		puntos.add(new PuntoGeometrico(this.x+radius, this.y+radius));
		puntos.add(new PuntoGeometrico(this.x-radius, this.y-radius));
		puntos.add(new PuntoGeometrico(this.x+radius, this.y-radius));
		puntos.add(new PuntoGeometrico(this.x-radius, this.y+radius));
		
		return puntos;
	}

}
