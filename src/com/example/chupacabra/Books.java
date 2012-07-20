package com.example.chupacabra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Books extends Activity implements OnClickListener{
	
	    Button myAddButton; //declared a reference variable of type Button
	    Button myDeleteButton;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.books);
	        
	        myAddButton = (Button) findViewById(R.id.myAddButton);
	        myDeleteButton = (Button) findViewById(R.id.myDeleteButton);
	        
	        myAddButton.setOnClickListener(this);
	        myDeleteButton.setOnClickListener(this);
	        
	    }
	    public void onClick(View v) {

			if(v.getId() == R.id.myAddButton){
				
				handleAddButtonClick();
			} 
			else if(v.getId() == R.id.myDeleteButton){
				
				handleDeleteButtonClick();
			}	
	    }
		private void handleAddButtonClick() {
	     	 Intent intent = new Intent(this, Add.class);
			 startActivity(intent);
		     }
		private void handleDeleteButtonClick() {
	     	 Intent intent = new Intent(this, Delete.class);
			 startActivity(intent);
		     }
}
