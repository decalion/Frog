package com.icaballero.proyectofinal.Utils;

import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.duhnnae.proyectofinal.R;

/**
 * Esta es la classe que carga las Imagenes de La pantalla hardlevel
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */


public class ResourcesP3 {

	Resources resources = null;
	Context context = null;

	AssetManager aManager;

	
	public Bitmap fondo;
	
	public Bitmap ranaBmp1;
	//public Bitmap ranaBmp2 ;
	public Bitmap ranaBmpOpen;
//	public Bitmap ranaBmpHit;
//	public Bitmap ranaBurn1;
//	public Bitmap ranaBurn2;
//	public Bitmap ranaBurn3;
//	public Bitmap ranaBurn4;
	public Bitmap ranaBurnDie1;
	public Bitmap ranaBurnDie2;
//	public Bitmap riprana1;
//	public Bitmap riprana2;
	public Bitmap riprana3 ;
	public Bitmap riprana4;
	public Bitmap riprana;
	public Bitmap ranaparalizada2;
	
	public Bitmap botonFire;
	public Bitmap botonFire2;
	public Bitmap moveUp;
	public Bitmap moveDown;
	
	public Bitmap escupitajo;
	public Bitmap escupitajo2;
	
	public Bitmap aranas;
	
	public Bitmap atelearanas;
	public Bitmap atelearanas2;
	
	public Bitmap aranajefe;
	public Bitmap aranajefehited;
	public Bitmap aranajefedead;
	
	public Bitmap fireball7;
	 public Bitmap fireball8;
	 public Bitmap fireball9;
	 public Bitmap fireball10;
	 public Bitmap fireball11;
	 public Bitmap fireball12;
	 
	 public Bitmap greatFireball1;
	 public Bitmap greatFireball2;
	 
	 
	 public Bitmap vida;
	
	/**
	 * Method que devuelve el objeto de typo Resources.
	 * @return
	 */
	private Resources getResources() {
		// TODO Auto-generated method stub
		return resources;
	}

	
	/**
	 * Constructor
	 * @param context de la actividad
	 * @param resources los resources
	 */
	public ResourcesP3(Context context, Resources resources) {
		this.resources = resources;
		this.context = context;
		aManager = context.getAssets();
		 decodeResources();

		 
			vida = BitmapFactory.decodeResource(getResources(),
					R.drawable.vida);
		 
		 //Rana
		ranaBmp1 = BitmapFactory.decodeResource(getResources(),
					R.drawable.ranalow2);
//		ranaBmp2 = BitmapFactory.decodeResource(getResources(),
//				R.drawable.ranalow3);
	   ranaBmpOpen = BitmapFactory.decodeResource(getResources(),
				R.drawable.ranalowopen);
//		 ranaBmpHit = BitmapFactory.decodeResource(getResources(),
//				R.drawable.ranalowhit);
//		 ranaBurn1 = BitmapFactory.decodeResource(getResources(),
//				R.drawable.ranaburn1);
//		 ranaBurn2 = BitmapFactory.decodeResource(getResources(),
//					R.drawable.ranaburn2);
	   
//	   ranaBurn3 = BitmapFactory.decodeResource(getResources(),
//				R.drawable.ranaburn3);
//		ranaBurn4 = BitmapFactory.decodeResource(getResources(),
//				R.drawable.ranaburn4);
		ranaBurnDie1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.ranaburndown1);
		ranaBurnDie2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.ranaburndown2);
		
//		riprana1 = BitmapFactory.decodeResource(getResources(),
//				R.drawable.riprana1);
		
//		riprana2 = BitmapFactory.decodeResource(getResources(),
//				R.drawable.riprana2);
		
		riprana3 = BitmapFactory.decodeResource(getResources(),
				R.drawable.riprana3);
		 riprana4 = BitmapFactory.decodeResource(getResources(),
				R.drawable.riprana4);
		 riprana = BitmapFactory.decodeResource(getResources(),
					R.drawable.riprrana);
	   
			ranaparalizada2 = BitmapFactory.decodeResource(getResources(),
					R.drawable.ranatela);
		 
		 //Telaranas
			atelearanas = BitmapFactory.decodeResource(getResources(),
					R.drawable.telarana1);
			atelearanas2 = BitmapFactory.decodeResource(getResources(),
					R.drawable.telarana2);
		 
		 
		// Disparo
		escupitajo = BitmapFactory.decodeResource(getResources(),
				R.drawable.escupitajo1);
		escupitajo2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.escupitajo2);
		
	
		// Aranas
		aranas = BitmapFactory.decodeResource(getResources(),
				R.drawable.arana_normal);
		
		

		// arana jefe
		aranajefe = BitmapFactory.decodeResource(getResources(),
				R.drawable.arana_mini_jefe);
		
		aranajefehited = BitmapFactory.decodeResource(getResources(),
				R.drawable.final_jefe_tocado);
		aranajefedead = BitmapFactory.decodeResource(getResources(),
				R.drawable.final_jefe_muerto);

		// Controles

		botonFire = BitmapFactory.decodeResource(getResources(),
				R.drawable.botonfire);
		botonFire2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.botonfire2);
		 moveUp = BitmapFactory.decodeResource(getResources(), R.drawable.up);
		 moveDown = BitmapFactory.decodeResource(getResources(),
					R.drawable.down);
		
		
		//FireBall
		 fireball7 = BitmapFactory.decodeResource(getResources(),
		            R.drawable.fireball7);
			 fireball8 = BitmapFactory.decodeResource(getResources(),
		            R.drawable.fireball8);
			 fireball9 = BitmapFactory.decodeResource(getResources(),
		            R.drawable.fireball9);
			 fireball10 = BitmapFactory.decodeResource(getResources(),
		            R.drawable.fireball10);
			 fireball11 = BitmapFactory.decodeResource(getResources(),
		            R.drawable.fireball11);
			 fireball12 = BitmapFactory.decodeResource(getResources(),
		            R.drawable.fireball12);
			 
			 //GreatFireball
			 greatFireball1 = BitmapFactory.decodeResource(getResources(),
			            R.drawable.great_fireball_1);
			 greatFireball2 =BitmapFactory.decodeResource(getResources(),
			            R.drawable.great_fireball_2);
		
	}

	
	/**
	 * Method para cargar Imagenes Grandes como fondos de Pantalla.
	 * 
	 */
	private void decodeResources() {
		InputStream is = null;

		try {

			is = aManager.open("fondopantalla2.jpg");
			fondo = BitmapFactory.decodeStream(is);

		} catch (Exception e) {
	}

}
}
