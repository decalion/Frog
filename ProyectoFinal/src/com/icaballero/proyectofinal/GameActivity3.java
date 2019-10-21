package com.icaballero.proyectofinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;

import com.duhnnae.proyectofinal.R;
import com.icaballero.proyectofinal.CustomViews.HardLevel;
import com.icaballero.proyectofinal.CustomViews.HardLevel.Aranas;
import com.icaballero.proyectofinal.CustomViews.HardLevel.BotonesMovimiento;
import com.icaballero.proyectofinal.CustomViews.HardLevel.Prota;
import com.icaballero.proyectofinal.Utils.Movimientos;
import com.icaballero.proyectofinal.Utils.Telaranas;



/**
 * Esta es la Actividad que carga la view de la   HardLevelscreen
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */

public class GameActivity3 extends Activity {
	
	HardLevel prota;
	Prota rana;

	final int SPLASH_DISPLAY_LENGHT = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_activity2);
		
		
		prota = (HardLevel) findViewById(R.id.pantallaDos1);
		rana = prota.getRana();
	}


	

	
	@Override
	protected void onPause() {
		
		
		try{
		prota.destroyDrawingCache();
		}catch(Exception e){
			
		}
	
		

		
		//Si queda algun Thread Vivo lo Elimina
		System.exit(0);
		
		
		
		Log.d("aaaa", "onPause");
		//finish();
		super.onPause();
		
	}
	
	@Override
	protected void onStop() {
		

		
		Log.d("aaaa", "onStop");
		super.onStop();
		
	}
	
	@Override
	protected void onDestroy() {
		
		try{

			prota.destroyDrawingCache();
			//prota.finish();
			}catch(Exception e){
				
			}

		Log.d("aaaa", "onDestroy");
		finish();
		super.onDestroy();
	}

}
