package com.icaballero.proyectofinal.Utils;

import java.io.InputStream;

import com.duhnnae.proyectofinal.R;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Clase con los recursos necesarios para dibujar las pantallas.
 * Se guardan todos los objetos de tipo Bitmap necesarios para dibujar el escenario
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */
public class ResourcesP2 {
	
	Resources resources=null;
	Context context=null;
	private boolean pause=false;
	
	
	
	public Bitmap ranaBmp1;
	public Bitmap ranaBmp2;
	public Bitmap ranaBmpOpen;
	public Bitmap ranaBmpHit;
	public Bitmap ranaBurn1;
	public Bitmap ranaBurn2;
	public Bitmap ranaBurn3;
	public Bitmap ranaBurn4;
	public Bitmap ranaBurnDie1;
	public Bitmap ranaBurnDie2;
	public Bitmap riprrana3;
	public Bitmap riprrana4;
	public Bitmap botonFire;
	public Bitmap botonFire2;
	public Bitmap moveUp;
	public Bitmap moveDown;
	public Bitmap escupitajo;
	public Bitmap escupitajo1;
	public Bitmap meteo;
	public Bitmap nave;
	public Bitmap laser1;
	public Bitmap laser2;
	public Bitmap vida;
	
	
	
	
	/**
	 * 
	 * @return
	 * Devuelve las imagenes
	 */
	public boolean isPause() {
		return pause;
	}
	
	/**
	 * 
	 * @param pause
	 */
	/**
	 * 
	 * @param pause
	 */
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	/**
	 * 
	 * @return Recoje Resources
	 */
	
	public Resources getResources()
	{
		return resources;
	}
	/**
	 * Convierte las imagenes
	 * @param context
	 * @param resources
	 */
	
	
	public ResourcesP2(Context context,Resources resources)
	{
		this.resources=resources;
		this.context=context;
		
		
		vida = BitmapFactory.decodeResource(getResources(),
				R.drawable.vida);
		 ranaBmp1 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.ranalow2);
		 ranaBmp2 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.ranalow3);
		 ranaBmpOpen = BitmapFactory.decodeResource(getResources(),
	            R.drawable.ranalowopen);
		 ranaBmpHit = BitmapFactory.decodeResource(getResources(),
	            R.drawable.ranalowhit);
		 ranaBurn1 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.ranaburn1);
		 ranaBurn2 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.ranaburn2);
		 ranaBurn3 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.ranaburn3);
		ranaBurn4 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.ranaburn4);
		ranaBurnDie1 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.ranaburndown1);
		ranaBurnDie2 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.ranaburndown2);
		 riprrana3 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.riprana3);
		 riprrana4 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.riprana4);
		  botonFire=BitmapFactory.decodeResource(getResources(), R.drawable.botonfire);
		  botonFire2=BitmapFactory.decodeResource(getResources(), R.drawable.botonfire2);
		  moveUp = BitmapFactory.decodeResource(getResources(),
		            R.drawable.up);
		  moveDown= BitmapFactory.decodeResource(getResources(),
		            R.drawable.down);
		escupitajo = BitmapFactory.decodeResource(getResources(),
		            R.drawable.escupitajo1);
		 escupitajo1 = BitmapFactory.decodeResource(getResources(),
		            R.drawable.escupitajo2);
		 
		 meteo=BitmapFactory.decodeResource(getResources(), R.drawable.meteo);
		
		 
		 laser1=BitmapFactory.decodeResource(getResources(), R.drawable.laser1);
		 laser2=BitmapFactory.decodeResource(getResources(), R.drawable.laser2);
	}
	
		
	
}



