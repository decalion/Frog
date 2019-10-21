package com.icaballero.proyectofinal;

import com.duhnnae.proyectofinal.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;




/**
 * Esta es la pantalla de Carga, esta pantalla se llama antes  de cargar cualquier actividad.
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */
public class LoadingActivity extends Activity {

	LoadingView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_loading);

		


		
		final int SPLASH_DISPLAY_LENGHT = 3000;

		 
		//Creamos un Handle con un delay de 3 segundos para hacer la LoadingScreen entre pantallas.
		    new Handler().postDelayed(new Runnable(){
	            @Override
	            public void run() {

	        		 Intent intentpantalla1 = new Intent(LoadingActivity.this, GameActivity.class);
	        		 Intent intentpantalla2 = new Intent(LoadingActivity.this, GameActivity2.class);
	        		 Intent intentpantalla3 = new Intent(LoadingActivity.this, GameActivity3.class);
	        		 Intent deadscreen = new Intent(LoadingActivity.this, DeadScreen.class);
	        		 Intent creditos = new Intent(LoadingActivity.this, Creditos.class);
	        		 Intent normalevel = new Intent(LoadingActivity.this, NextNormalLevel.class);
	        		 Intent hardlevel = new Intent(LoadingActivity.this, NextHardLevel.class);
	        		
	        		//Recogemos en un bundle el numero enviado para saber que Activida Cambiar
	        		Bundle b= LoadingActivity.this.getIntent().getExtras();
	        		 int i= b.getInt("name");
	        		 int j = b.getInt("puntuacion");
	        		 int pantalla=b.getInt("pantalla");

	        		if(i==1){
	        			LoadingActivity.this.startActivity(intentpantalla1);
	        		}
	        		else if(i==2){
	        			LoadingActivity.this.startActivity(intentpantalla3);
	        		
	        		}else if(i==3){
	        			LoadingActivity.this.startActivity(intentpantalla2);
	        			
	        		}
	        		
	        		else if(i==4){
	        			//Recibir el Bundle con la Puntuacion de la pantalla Hard Level y enviarlo a la panttala DeadHardLevel
	        			deadscreen.putExtra("puntuacion", j);
	        			deadscreen.putExtra("pantalla", pantalla);
	        			LoadingActivity.this.startActivity(deadscreen);
	        		}
	        		else if(i==5){
	        			
	        			creditos.putExtra("puntuacion", j);
	        			LoadingActivity.this.startActivity(creditos);
	        
	        		}else if(i==6){
	        			normalevel.putExtra("puntuacion", j);
	        			LoadingActivity.this.startActivity(normalevel);
	        		}else if(i==7){
	        			hardlevel.putExtra("puntuacion", j);
	        			LoadingActivity.this.startActivity(hardlevel);
	        		}
	        		
	                LoadingActivity.this.finish();
	            }
	        }, SPLASH_DISPLAY_LENGHT);
	    
//		
	}
	
	
	




}
