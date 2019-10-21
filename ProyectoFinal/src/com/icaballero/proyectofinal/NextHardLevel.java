package com.icaballero.proyectofinal;

import com.duhnnae.proyectofinal.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



/**
 * Esta es la pantalla de cambio entre el Segundo nivel y el tercer Nivel
 * @author Cristian Bautista, Ismael Caballero, Daniel Córdoba
 *
 */


public class NextHardLevel extends Activity {
	
	
	final int MAXSCORE=160;
	
	int i;
	TextView puntuacion;
	Button back,next;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next_hard_level);
		Bundle bundle=getIntent().getExtras();
		i=bundle.getInt("puntuacion");
		
		puntuacion=(TextView)findViewById(R.id.tv_nexthard_puntuacion);
		puntuacion.setText(getString(R.string.hard_points) +i+"/"+MAXSCORE);
		
		
		back=(Button)findViewById(R.id.bt_nexthard_back);
		next=(Button)findViewById(R.id.bt_nexthard);
		
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentLoading = new Intent(NextHardLevel.this, MainActivity.class);
				NextHardLevel.this.startActivity(intentLoading);
    			NextHardLevel.this.finish();
				
			}
		});
		
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentLoading = new Intent(NextHardLevel.this, LoadingActivity.class);
				intentLoading.putExtra("name", 2);
				NextHardLevel.this.startActivity(intentLoading);
    			NextHardLevel.this.finish();
				
			}
		});
		
		
		
		
	}


}
