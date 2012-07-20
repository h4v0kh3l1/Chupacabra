package com.example.chupacabra;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyMarket extends Activity implements OnClickListener {
	
		Button myBooksButton; //declared a reference variable of type Button
		Button myCdsButton;
		Button myApparelButton;
		Button myOthersButton;
		
		ArrayList<Friend> friendList;
		
		Intent thisIntent;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.market); 
	        thisIntent = getIntent();
	        myBooksButton = (Button) findViewById(R.id.myBooksButton);
	        myBooksButton.setOnClickListener(this);
	        myCdsButton = (Button) findViewById(R.id.myCdsButton);
	        myCdsButton.setOnClickListener(this);
	        myApparelButton = (Button) findViewById(R.id.myApparelButton);
	        myApparelButton.setOnClickListener(this);
	        myOthersButton = (Button) findViewById(R.id.myOthersButton);
	        myOthersButton.setOnClickListener(this);
	        
	        friendList = (ArrayList<Friend>) thisIntent.getSerializableExtra("friendList");
	        for(Friend friend : friendList )
	        	Log.d("Friend Market", friend.getName());
	 	}
	 	
	 @Override
		public void onClick(View v) {

			if(v.getId() == R.id.myBooksButton){
				
				handleBooksButtonClick();
			} 
			/*else if(v.getId() == R.id.myCdsButton){
				
				handlePurchaseButtonClick();
			}
			else if(v.getId() == R.id.myApparelButton){
				
				handleSalesButtonClick();
			}
			else if(v.getId() == R.id.myOthersButton){
				
				handleBackButtonClick();
			}*/
		}
	    
	     private void handleBooksButtonClick() {
	    	 Intent intent = new Intent(this, Books.class);
	    	 intent.putExtra("friendList", friendList);
	 		 startActivity(intent);
	     }
	 
	 

}
