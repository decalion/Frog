package com.icaballero.proyectofinal.Utils;

import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.duhnnae.proyectofinal.R;
/**
 * Clase con los recursos necesarios para dibujar las pantallas.
 * Se guardan todos los objetos de tipo Bitmap necesarios para dibujar el escenario
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */
public class ResourcesP1 {
	
	Resources resources = null;
	Context context = null;
	private boolean pause = false;

	
	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}
	public Bitmap currentFondo;
	
	AssetManager aManager;
	public Bitmap mosquitoDie1;
	public Bitmap mosquitoDie2;
	 public Bitmap mosquito1;
	 public Bitmap mosquito2;
	 public Bitmap mosquitoJefe2;
	 public Bitmap mosquitoJefe3;
	 public Bitmap mosquitoJefeHit;
	 public Bitmap mosquitoJefeDie1;
	 public Bitmap mosquitoJefeDie2;
	 public Bitmap fireball7;
	 public Bitmap fireball8;
	 public Bitmap fireball9;
	 public Bitmap fireball10;
	 public Bitmap fireball11;
	 public Bitmap fireball12;
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
	 public Bitmap riprana1;
	 public Bitmap escupitajo;
	 public Bitmap escupitajo2;
	 public Bitmap moneda1;
	 public Bitmap moneda2;
	 public Bitmap moveUp;
	 public Bitmap moveDown;
	 public Bitmap botonFire;
	 public Bitmap botonFire2;
	 public Bitmap fondo;
	 public Bitmap fondo2;
	 public Bitmap waterTexture;
	 public Bitmap riprana3;
	 public Bitmap riprana2;
	 public Bitmap riprana4;
	 public Bitmap riprana;
	 public Bitmap vida;

	private Resources getResources() {
		// TODO Auto-generated method stub
		return resources;
	}

	/**
	 * Constructor de la clase. 
	 * También transforma los recursos alojados en la carpeta res/drawable en objetos Bitmap utilizables 
	 * por el programa
	 * @param context
	 * @param resources
	 */
	public ResourcesP1 (Context context,Resources resources){
		this.resources = resources;
		this.context = context;
		aManager = context.getAssets();
		decodeResources();
		
		vida = BitmapFactory.decodeResource(getResources(),
				R.drawable.vida);
		mosquitoDie1 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.mosquito44);

		mosquitoDie2 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.mosquito33);
		mosquito1 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.mosquito4);
		mosquito2 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.mosquito3);
		mosquitoJefe2 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.mosquitojefe2);
		 mosquitoJefe3 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.mosquitojefe3);
		 mosquitoJefeHit = BitmapFactory.decodeResource(getResources(),
	            R.drawable.mosquitojefehit);
		 mosquitoJefeDie1 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.mosquitojefedie1);
		 mosquitoJefeDie2 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.mosquitojefedie2);
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
//		 riprana1 = BitmapFactory.decodeResource(getResources(),
//	            R.drawable.riprana1);
		Bitmap riprana2;
		Bitmap riprana3;
		Bitmap riprana4;
		Bitmap riprana;
		
		 escupitajo = BitmapFactory.decodeResource(getResources(),
	            R.drawable.escupitajo1);
		 escupitajo2 = BitmapFactory.decodeResource(getResources(),
	            R.drawable.escupitajo2);
		
		Bitmap fondo;
		Bitmap fondo2;
		Bitmap waterTexture;
		//monedas
		 moneda1= BitmapFactory.decodeResource(getResources(),
	            R.drawable.moneda1);
		 moneda2= BitmapFactory.decodeResource(getResources(),
	            R.drawable.moneda2);
		 moveUp = BitmapFactory.decodeResource(getResources(),
	            R.drawable.up);
		 moveDown= BitmapFactory.decodeResource(getResources(),
	            R.drawable.down);
		 botonFire= BitmapFactory.decodeResource(getResources(),
	            R.drawable.botonfire);
		 botonFire2= BitmapFactory.decodeResource(getResources(),
	            R.drawable.botonfire2);
	}
	private void decodeResources() {
		InputStream is = null;
		try{
		/*is = aManager.open("mosquito44.png");
		mosquitoDie1 = BitmapFactory.decodeStream(is);*/
		/*is = aManager.open("ranalow2.png");
		ranaBmp1 = BitmapFactory.decodeStream(is);
		is = aManager.open("ranalow3.png");
		ranaBmp2 = BitmapFactory.decodeStream(is);
		is = aManager.open("ranalowopen.png");
		ranaBmpOpen = BitmapFactory.decodeStream(is);
		is = aManager.open("ranalowhit.png");*/
		/*ranaBmpHit = BitmapFactory.decodeStream(is);
		is = aManager.open("moneda1.png");
		 moneda1 = BitmapFactory.decodeStream(is);
		is = aManager.open("moneda2.png");
		 moneda2 = BitmapFactory.decodeStream(is);
		is = aManager.open("up.png");
		 moveUp = BitmapFactory.decodeStream(is);
		is = aManager.open("down.png");*/
		 /*moveDown = BitmapFactory.decodeStream(is);*/
		is = aManager.open("texture.jpg");
		 fondo = BitmapFactory.decodeStream(is);
		is = aManager.open("texture2.jpg");
		 fondo2 = BitmapFactory.decodeStream(is);
		is = aManager.open("watertexture.jpg");
		 waterTexture = BitmapFactory.decodeStream(is);
		 is = aManager.open("riprana1.png");
		 riprana3 = BitmapFactory.decodeStream(is);
		 
		 is = aManager.open("riprana1.png");
			 riprana1 = BitmapFactory.decodeStream(is);
			 is = aManager.open("riprana2.png");
			 riprana2 = BitmapFactory.decodeStream(is);
			 is = aManager.open("riprana3.png");
			 riprana3 = BitmapFactory.decodeStream(is);
			 is = aManager.open("riprana4.png");
			 riprana4 = BitmapFactory.decodeStream(is);
			 is = aManager.open("riprana.png");
			 riprana = BitmapFactory.decodeStream(is);
			 /*is = aManager.open("escupitajo1.png");
			 escupitajo = BitmapFactory.decodeStream(is);
			 is = aManager.open("escupitajo1.png");
			 escupitajo2 = BitmapFactory.decodeStream(is);*/
			
		}catch(Exception e){
			
		}
		
	}

}
