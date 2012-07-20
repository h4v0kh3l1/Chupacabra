package com.example.chupacabra;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Add extends Activity implements OnClickListener{
	
	Button mySaveButton; //declared a reference variable of type Button
    Button myCancelButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        
        mySaveButton = (Button) findViewById(R.id.mySaveButton);
        myCancelButton = (Button) findViewById(R.id.myCancelButton);
        
        mySaveButton.setOnClickListener(this);
        myCancelButton.setOnClickListener(this);
	}
	public void onClick(View v) {

		if(v.getId() == R.id.mySaveButton){
			
			handleSaveButtonClick();
		} 
		else if(v.getId() == R.id.myCancelButton){
			
			handleCancelButtonClick();
		}	
    }
	private void handleSaveButtonClick() {
		 //Need to handle the new book object here
     	 finish();
	     }
	private void handleCancelButtonClick() {
     	 finish(); //takes you to the previous page
	     }

}
