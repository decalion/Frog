package com.icaballero.proyectofinal;

import com.duhnnae.proyectofinal.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Esta es la DeadScreen que aparece cuando la Rana Muerto.
 * La DeadScreen esta Comunicada con todas las Pantallas
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */


public class DeadScreen extends Activity {
	
	
	private final int MAX_SCORE=120;
	TextView score;
	Button tryagain;
	int pantalla;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dead_pantalla_tress);
		
		
		//Recibir el Bundle con la puntuacion de la LoadingScreen
		Bundle bundle = DeadScreen.this.getIntent().getExtras();
		int i = bundle.getInt("puntuacion");
		pantalla=bundle.getInt("pantalla");
		
		score =(TextView)findViewById(R.id.deadtryscore);
		score.setText(getString(R.string.dead_score) + i);
		
		tryagain=(Button)findViewById(R.id.deadtryhard);
		
		tryagain.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(pantalla==1){
					final Intent intentLoading = new Intent(DeadScreen.this, LoadingActivity.class);
					intentLoading.putExtra("name", 1);
					startActivity(intentLoading); 
					finish();
					
				}else if(pantalla==2){
					final Intent intentLoading = new Intent(DeadScreen.this, LoadingActivity.class);
					intentLoading.putExtra("name", 3);
					startActivity(intentLoading); 
					finish();
				}
				
				else if(pantalla==3){
					final Intent intentLoading = new Intent(DeadScreen.this, LoadingActivity.class);
					intentLoading.putExtra("name", 2);
					startActivity(intentLoading); 
					finish();
				}
				
				
				
			}
		});
		
		
		
	}

	
	




}
