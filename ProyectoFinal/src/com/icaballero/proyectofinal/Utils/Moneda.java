package com.icaballero.proyectofinal.Utils;

import java.util.ArrayList;

/**
 * Clase Moneda, que crea un objeto del tipo Moneda, que se visualiza en pantalla, usualmente cuando un
 * jefe final muere
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */
public class Moneda {
	int diametro;
	public int radio;
	public int initX;
	public int initY;
	int centerX;
	int centerY;
	public int valor;
	public ArrayList<PuntoGeometrico> puntos = new ArrayList<PuntoGeometrico>();
	
	/**
	 * Constructor de la clase, usualmente, donde ha muerto el jefe final
	 * @param diametro
	 * @param initX
	 * @param initY
	 * @param valor
	 */
	public Moneda(int diametro, int initX, int initY, int valor){
		this.diametro = diametro;
		this.initX = initX;
		this.initY = initY;
		this.radio = diametro/2;
		centerX = initX + radio;
		centerY = initY + radio;
		this.valor = valor;
		guardarPuntos();
		
	}
	
	public int getValor(){
		return valor;
	}

	/**
	 * Como la moneda es redonda, guarda una serie de puntos geométricos una vez que se crea el objeto
	 */
	private void guardarPuntos() {
		puntos.add(new PuntoGeometrico(centerX+radio, centerY));
		puntos.add(new PuntoGeometrico(centerX-radio, centerY));
		puntos.add(new PuntoGeometrico(centerX, centerY+radio));
		puntos.add(new PuntoGeometrico(centerX, centerY-radio));		
		puntos.add(new PuntoGeometrico(centerX+(radio/2), centerY+(radio/2)));
		puntos.add(new PuntoGeometrico(centerX-(radio/2), centerY+(radio/2)));
		puntos.add(new PuntoGeometrico(centerX-(radio/2), centerY-(radio/2)));
		puntos.add(new PuntoGeometrico(centerX+(radio/2), centerY-(radio/2)));		
	}
	
	public ArrayList<PuntoGeometrico> getPuntos(){
		return puntos;
	}
	
	

}
