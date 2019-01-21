package com.example.mastermind;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class SetActivity extends ActionBarActivity {
	
	
	//-----
		public static final String EXTRA_CODE ="com.example.mastermind.CODE";
	
	//O kwdikos me ta xrwmata
	
		int [] kwdikos = new int [4];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set);
		
		//Initialize ton kwdiko
		kwdikos[0]=1;
		kwdikos[1]=1;
		kwdikos[2]=1;
		kwdikos[3]=1;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	//Otan patame to tick - OK - koubi epibebaiwshs
	public void confirmClick (View view) {
		Intent toStep3 = new Intent(this,SolveActivity.class);
		toStep3.putExtra(EXTRA_CODE,kwdikos);
		startActivity(toStep3);
	}
	
	
	//Otan patame ta koubia me ta belakia
	
	public void arrow_bt_click(View view) {
		int position =0;
		ImageView pic1= (ImageView)findViewById(R.id.symbol1);
		
		//Poio koubi paththke?
		switch (view.getId()) {
		case R.id.bt1up : 
			position=0;
			pic1 = (ImageView)findViewById(R.id.symbol1);
			kwdikos[position]++;
			break;
		case R.id.bt1down:
			position=0;
			pic1 = (ImageView)findViewById(R.id.symbol1);
			kwdikos[position]--;
			break;
		case R.id.bt2up:
			position=1;
			pic1 = (ImageView)findViewById(R.id.symbol2);
			kwdikos[position]++;
			break;
		case R.id.bt2down:
			position=1;
			pic1 = (ImageView)findViewById(R.id.symbol2);
			kwdikos[position]--;
			break;
		case R.id.bt3up:
			position=2;
			pic1 = (ImageView)findViewById(R.id.symbol3);
			kwdikos[position]++;
			break;
		case R.id.bt3down:
			position=2;
			pic1 = (ImageView)findViewById(R.id.symbol3);
			kwdikos[position]--;
			break;
		case R.id.bt4up:
			position=3;
			pic1 = (ImageView)findViewById(R.id.symbol4);
			kwdikos[position]++;
			break;
		case R.id.bt4down:
			position=3;
			pic1 = (ImageView)findViewById(R.id.symbol4);
			kwdikos[position]--;
			break;
		}
		
			
			
		//Allaxe ta xrwmata analogws
			switch(kwdikos[position]) {
			case 1: pic1.setImageResource(R.drawable.red_ball);
				break;
			case 2: pic1.setImageResource(R.drawable.blue_ball);
			break;
			case 3 :pic1.setImageResource(R.drawable.green_ball);
				break;
			case 4 :pic1.setImageResource(R.drawable.purple_ball);
				break;
			case 5 :pic1.setImageResource(R.drawable.yellow_ball);
				break;
			case 6 :pic1.setImageResource(R.drawable.white_ball);
				break;
			case 7 :pic1.setImageResource(R.drawable.red_ball);
				kwdikos[position]=1;
				break;
			case 0 :pic1.setImageResource(R.drawable.white_ball);
				kwdikos[position]=6;
			break;
			}
		
		
	}
}
