package com.icaballero.proyectofinal;

import android.os.Bundle;

import com.duhnnae.proyectofinal.R;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



/**
 * Esta es la pantalla de cambio entre el primer nivel y el  segundo nivel
 * 
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */

public class NextNormalLevel extends Activity {

	
	final int MAXSCORE=375;
	
	int i;
	TextView puntuacion;
	Button back,next;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next_normal_level);
		
		
		
		Bundle bundle=getIntent().getExtras();
		i=bundle.getInt("puntuacion");
		
		puntuacion=(TextView)findViewById(R.id.tv_nextnormal_puntuacion);
		puntuacion.setText(getString(R.string.tv_nextnormal_score) +i+"/"+MAXSCORE);
		
		
		back=(Button)findViewById(R.id.bt_nextnormal_back);
		next=(Button)findViewById(R.id.bt_nextnormal);
		
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentLoading = new Intent(NextNormalLevel.this, MainActivity.class);
				NextNormalLevel.this.startActivity(intentLoading);
    			NextNormalLevel.this.finish();
				
			}
		});
		
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentLoading = new Intent(NextNormalLevel.this, LoadingActivity.class);
				intentLoading.putExtra("name", 3);
				NextNormalLevel.this.startActivity(intentLoading);
    			NextNormalLevel.this.finish();
				
			}
		});
		
		
		
		
	}


}
