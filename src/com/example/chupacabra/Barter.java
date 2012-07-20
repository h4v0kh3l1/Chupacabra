package com.example.chupacabra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Barter extends Activity implements OnClickListener {

	Button myMarketButton; //declared a reference variable of type Button
	Button myInflowButton;
	Button myOutflowButton;
	Button myBackButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barter_up);
        myMarketButton = (Button) findViewById(R.id.myMarketButton);
        myMarketButton.setOnClickListener(this);
        myInflowButton = (Button) findViewById(R.id.myInflowButton);
        myInflowButton.setOnClickListener(this);
        myOutflowButton = (Button) findViewById(R.id.myOutflowButton);
        myOutflowButton.setOnClickListener(this);
        myBackButton = (Button) findViewById(R.id.myBackButton);
        myBackButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {

		if(v.getId() == R.id.myMarketButton){
			
			handleMarketButtonClick();
		} 
		else if(v.getId() == R.id.myInflowButton){
			
			handleInflowButtonClick();
		}
		else if(v.getId() == R.id.myOutflowButton){
			
			handleOutflowButtonClick();
		}
		else if(v.getId() == R.id.myBackButton){
			
			handleBackButtonClick();
		}
	}

	private void handleMarketButtonClick() {

		Intent intent = new Intent(this, MyMarket.class);
		startActivity(intent);
	}
	
	private void handleInflowButtonClick() {

		Intent intent = new Intent(this, MyInflow.class);
		startActivity(intent);
	}
	
	private void handleOutflowButtonClick() {

		Intent intent = new Intent(this, MyOutflow.class);
		startActivity(intent);
	}
	private void handleBackButtonClick() {

		Intent intent = new Intent(this, MyMarket.class); //Wrong logic
		startActivity(intent);
	}
}
