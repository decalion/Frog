package com.icaballero.proyectofinal;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ViewManager;

import com.duhnnae.proyectofinal.R;
import com.icaballero.proyectofinal.CustomViews.EasyLevel;
import com.icaballero.proyectofinal.CustomViews.EasyLevel.Prota;
/**
 * Actividad de la primera pantalla. Contiene un customView que dibuja los objetos en la pantalla, y los controles necesarios para controlar al protagonista
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */
public class GameActivity extends Activity{

	EasyLevel prota;
	Prota rana;
	//Contiene el estado del view Protagonista para saber si la aplicaciÃ³n estÃ¡ preparada para ejecutarse
	public static boolean ready = false;
	
	//Util
	boolean salir = false;
	
	/**
	 * Override del método onCreate de la actividad
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		System.gc();
		
		prota = (EasyLevel) findViewById(R.id.protagonista1);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//Cogemos la rana del customView
		rana = prota.getRana();
		
		
		//Waits until game is ready
		new Thread(){
			public void run() {
				//Hasta que no se han cargado todas las imágenes, no da comienzo el juego.
				while(!ready){
					
					ready = prota.isReadyToPlay();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				ready = true;
				
			};
		
		}.start();
		
	}
	
	@Override
	protected void onPause() {
		//Para la música si se pausa la actividad y la destruye.
		try{
			prota.stopMusic();
		prota.getMediaPlayer().stop();
		prota.getMediaPlayer().release();
		prota.setPause(true);
		prota.res=null;
		prota.destroyDrawingCache();
		prota.destroyAll();
		
		try{
			((ViewManager)getParent()).removeView(prota);
			}catch(Exception e){
				
			}
		}catch(Exception e){
			
		}
		
		System.exit(0);
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		prota.setPause(true);
		prota.stopMusic();
		prota.stopMusic();
		prota.destroyDrawingCache();
		prota.destroyAll();
		System.gc();
		try{
		((ViewManager)getParent()).removeView(prota);
		}catch(Exception e){
			
		}
		super.onStop();
	}
	
	@Override
	protected void onResume() {
		prota = new EasyLevel(getBaseContext());
		super.onResume();
	}
	
	@Override
	protected void onRestart() {
		/*try{
			((ViewManager)getParent()).removeView(prota);
			}catch(Exception e){
				
			}
		prota = new Protagonista(getBaseContext());*/
		super.onRestart();
	}
	
	@Override
	protected void onDestroy() {
		try{
			
			prota.setPause(true);
			prota.destroyDrawingCache();
			prota.destroyAll();
			prota.getMediaPlayer().stop();
			prota.getMediaPlayer().release();
			prota.res = null;
			System.gc();
			try{
				((ViewManager)getParent()).removeView(prota);
				}catch(Exception e){
					
				}
			}catch(Exception e){
				
			}
		super.onDestroy();
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}	

}
