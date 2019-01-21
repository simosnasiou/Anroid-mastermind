package com.example.mastermind;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SolveActivity extends ActionBarActivity {

	//Define kwdikos2
	int [] kwdikos2 = new int[4]; 
	int reds=0, whites=0,reds_temp=0,whites_temp=0;
	int [] S = new int[1296];
	int guess = 1122;
	int [] scores =new int [1296];
	boolean [] isinS =new boolean [1296];
	boolean [] isinP =new boolean [1296];
	//
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solve);
		
		//lhpsh kwdikou
		Intent eiserxomeno = getIntent();
		kwdikos2 = eiserxomeno.getIntArrayExtra(SetActivity.EXTRA_CODE);
		
		
		//intialize logikwn pinakwn
		for (int i=0;i<1296;i++){
			isinS[i]=true;
			isinP[i]=true;
		}
		//anathesh xrwmatwn sth lysh
		setcolors();
		
		//anathesh xrwmatwn sthn ypothesh
		setcolorsG();
		//gemisma pinaka me oles tis pithanes lyseis
		fillS(S);
	}
	
	
	//otan parame to ok
	public void okPress(View view){
		//Afairoume apo to S osa apokleiei h ypothesh antistoixo bhma 5 wikipedia
		for (int i=0;i<1296;i++) {
			if (reds!= calc_reds (S[i],guess) || whites != calc_whites(S[i],guess)) isinS[i]=false;
		}
		//Isws edw na mpei ena Pop-up h notification an broume th lysh?mallon oxi 
		
		//Ypologismos neas timhs gia upothesh
		
		// O pinakas twn score xreiazetai gia na ypologisoume to epomeno guess ton gemizoume bhma 6 a wikipedia sthn ousia
		fillScores();
		
		//Brisdkoume epomeno guess
		guess = nextGuess();
		
		//Update twn grafikwn gia na tairiazoun me to epomeno guess
		setcolorsG();
	}
	
	//otan patame to koubi gia ta kokkina
	public void redsPress(View view){
		reds++;
		if (reds>4)reds=0;
		//gia na mhn exoume akyro feeedback apo to xrhsth
		if (reds+whites>4) {
			whites--;
			if(whites<0)whites=0;
			Button but_w = (Button) findViewById(R.id.whites_btn);
			but_w.setText(Integer.toString(whites));
		}
		Button but_r = (Button) view ;
		but_r.setText(String.valueOf(reds));
	}
	
	//otan patame to koubi gia ta aspra
		public void whitesPress(View view){
			whites++;
			if (whites>4)whites=0;
			//gia na mhn exoume akyro feeedback apo to xrhsth
			if (reds+whites>4) {
				reds--;
				if(reds<0)reds=0;
				Button but_r = (Button) findViewById(R.id.reds_btn);
				but_r.setText(Integer.toString(reds));
			}
			Button but_w = (Button) view ;
			but_w.setText(String.valueOf(whites));
		}
	
	//gemisma pinaka
 	public void fillS (int[] ss) {
		int i,j,k,l,z=0;
		for (i=1;i<=6;i++){
			for (j=1;j<=6;j++){
				for (k=1;k<=6;k++){
					for (l=1;l<=6;l++) {					
						ss[z]=i*1000+j*100+k*10+l;
						z++;
					}
				}
			}
		}			
	}

	//gemisma pinaka twn score pou xrhsipmopoieitai gia thn epomenh ypothesh
	public void fillScores() {
		//Ypologismos score gia kathe stoixeio tou P
		int score_t=0,min_score=0;
		for (int i=0;i<1296;i++) {
			if (isinP[i]){
				score_t=0;
				//alrithmos megalyteros apo to magalytero dynato score etsi to prwto score pou pypologizetai panta ton antikathista
				min_score=5000;
				//oloi oi syndiasmoi kokkinwn asprwn
				for (int r=0;r<=4;r++) {
					for (int w=0;w<=4-i;w++) {
						//ola ta stoixeia tou S
						for (int j=0;j<1296;j++) {
							if (isinS[j]){
								if(calc_whites(S[i],S[j])!=w || r != calc_reds(S[i],S[j])){
								score_t++;
								}
							}
						}
						//Prpspathoume na borume ton elaxisto score gia metaxy twn syndiasmwn kokkinwn asprwn
						if (min_score>score_t)min_score =score_t;
						
					}
				}
				//enas pinakas me ola ta elaxista score
				scores[i]=min_score;
			}
		}
	
	}

	//epilogh ths epomenhs ypotheshs
	public int nextGuess(){
		int nxt=guess;
		int max_score=0;
		for (int i=1;i<1296;i++){
				if (scores[i]>max_score){
					max_score=scores[i];
				}
		}
		for (int i=1;i<1296;i++){
			if (scores[i]==max_score && isinS[i]){
				nxt=S[i];
				isinP[i]=false;
			}
		}
		return nxt;
	}
	
	//posa kokkina metaxy dyo syndiasmwn (idio xrwma idia thesh)
	public int calc_reds (int number_a , int number_b) {
		int result = 0;
		//to ekastote pshfio poy elegxoume
		int digit_a,digit_b;
	
		for (int i=1; i<=4;i++) {			
			digit_a=number_a % 10;
			digit_b=number_b%10;
			if (digit_a== digit_b) result++;
			number_a/=10;
			number_b/=10;
		
		};
		
		return result;			
	}
	
	
	//posa aspra metaxy dyo syndiasmwn (idio xrwma allh thesh
	public int calc_whites (int number_a , int number_b) {
		int result = 0;
		//to ekastote pshfio poy elegxoume
		int digit_a,digit_b;
		//prosorinh apothikeysh twn nuber a kai b gia na mh xanonatai oii times tous
		int na=number_a; int nb=number_b;
		boolean found = false;
		// pinakas pou dixnei an me mia timh tou number_b exei hdh antostoixistei pshfio toy number_a
		boolean [] b_used = new boolean [4];
		//init b-used
		for (int i=0;i<=3;i++) b_used[i]=false;
		
		for (int i=1; i<=4; i++) {	
			digit_a = na%10;
			for (int j=1; j<=4;j++) {			
				digit_b =nb%10;
				if (digit_b == digit_a && found == false && b_used[j-1]==false) {
					found = true;
					b_used[j-1]=true;
				}
				nb/=10;
			};
			
			if (found ==true ) result++;
			nb=number_b;
			na/=10;
			found = false;
		};
		
		
		result -= calc_reds(number_a,number_b);
		return result;		
		
	}
	
	//Orismos ths anatheshs xrwmatwn sth lysh
	public void setcolors() {
		
		ImageView ans1 =(ImageView) findViewById(R.id.answer1);
		switch(kwdikos2[0]) {
		case 1: ans1.setImageResource(R.drawable.red_ball);
			break;
		case 2: ans1.setImageResource(R.drawable.blue_ball);
		break;
		case 3 :ans1.setImageResource(R.drawable.green_ball);
			break;
		case 4 :ans1.setImageResource(R.drawable.purple_ball);
			break;
		case 5 :ans1.setImageResource(R.drawable.yellow_ball);
			break;
		case 6 :ans1.setImageResource(R.drawable.white_ball);
			break;
		}
	
		ImageView ans2 =(ImageView) findViewById(R.id.answer2);
		switch(kwdikos2[1]) {
		case 1: ans2.setImageResource(R.drawable.red_ball);
			break;
		case 2: ans2.setImageResource(R.drawable.blue_ball);
		break;
		case 3 :ans2.setImageResource(R.drawable.green_ball);
			break;
		case 4 :ans2.setImageResource(R.drawable.purple_ball);
			break;
		case 5 :ans2.setImageResource(R.drawable.yellow_ball);
			break;
		case 6 :ans2.setImageResource(R.drawable.white_ball);
			break;
		}
		
		ImageView ans3 =(ImageView) findViewById(R.id.answer3);
		switch(kwdikos2[2]) {
		case 1: ans3.setImageResource(R.drawable.red_ball);
			break;
		case 2: ans3.setImageResource(R.drawable.blue_ball);
		break;
		case 3 :ans3.setImageResource(R.drawable.green_ball);
			break;
		case 4 :ans3.setImageResource(R.drawable.purple_ball);
			break;
		case 5 :ans3.setImageResource(R.drawable.yellow_ball);
			break;
		case 6 :ans3.setImageResource(R.drawable.white_ball);
			break;
		}
		
		ImageView ans4 =(ImageView) findViewById(R.id.answer4);
		switch(kwdikos2[3]) {
		case 1: ans4.setImageResource(R.drawable.red_ball);
			break;
		case 2: ans4.setImageResource(R.drawable.blue_ball);
		break;
		case 3 :ans4.setImageResource(R.drawable.green_ball);
			break;
		case 4 :ans4.setImageResource(R.drawable.purple_ball);
			break;
		case 5 :ans4.setImageResource(R.drawable.yellow_ball);
			break;
		case 6 :ans4.setImageResource(R.drawable.white_ball);
			break;
		} 
	}

	
	//Orismos ths anatheshs xrwmatwn sth Ypothesh
	public void setcolorsG() {
		
		ImageView g1 =(ImageView) findViewById(R.id.guess1);
		switch((guess/1000)%10) {
		case 1: g1.setImageResource(R.drawable.red_ball);
			break;
		case 2: g1.setImageResource(R.drawable.blue_ball);
		break;
		case 3 :g1.setImageResource(R.drawable.green_ball);
			break;
		case 4 :g1.setImageResource(R.drawable.purple_ball);
			break;
		case 5 :g1.setImageResource(R.drawable.yellow_ball);
			break;
		case 6 :g1.setImageResource(R.drawable.white_ball);
			break;
		}
	
		ImageView g2 =(ImageView) findViewById(R.id.guess2);
		switch((guess/100)%10) {
		case 1: g2.setImageResource(R.drawable.red_ball);
			break;
		case 2: g2.setImageResource(R.drawable.blue_ball);
		break;
		case 3 :g2.setImageResource(R.drawable.green_ball);
			break;
		case 4 :g2.setImageResource(R.drawable.purple_ball);
			break;
		case 5 :g2.setImageResource(R.drawable.yellow_ball);
			break;
		case 6 :g2.setImageResource(R.drawable.white_ball);
			break;
		}
		
		ImageView g3 =(ImageView) findViewById(R.id.guess3);
		switch((guess/10)%10) {
		case 1: g3.setImageResource(R.drawable.red_ball);
			break;
		case 2: g3.setImageResource(R.drawable.blue_ball);
		break;
		case 3 :g3.setImageResource(R.drawable.green_ball);
			break;
		case 4 :g3.setImageResource(R.drawable.purple_ball);
			break;
		case 5 :g3.setImageResource(R.drawable.yellow_ball);
			break;
		case 6 :g3.setImageResource(R.drawable.white_ball);
			break;
		}
		
		ImageView g4 =(ImageView) findViewById(R.id.guess4);
		switch(guess%10) {
		case 1: g4.setImageResource(R.drawable.red_ball);
			break;
		case 2: g4.setImageResource(R.drawable.blue_ball);
		break;
		case 3 :g4.setImageResource(R.drawable.green_ball);
			break;
		case 4 :g4.setImageResource(R.drawable.purple_ball);
			break;
		case 5 :g4.setImageResource(R.drawable.yellow_ball);
			break;
		case 6 :g4.setImageResource(R.drawable.white_ball);
			break;
		} 
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.solve, menu);
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
}
