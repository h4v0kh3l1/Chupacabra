package com.example.chupacabra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyInflow extends Activity implements OnClickListener {
	
	Button myInflowAddButton; //declared a reference variable of type Button
	Button myInflowDeleteButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inflow);
        
        myInflowAddButton = (Button) findViewById(R.id.myInflowAddButton);
        myInflowDeleteButton = (Button) findViewById(R.id.myInflowDeleteButton);
        
        myInflowAddButton.setOnClickListener(this);
        myInflowDeleteButton.setOnClickListener(this);
        
    }
    public void onClick(View v) {

		if(v.getId() == R.id.myInflowAddButton){
			
			handleInflowAddButtonClick();
		} 
		else if(v.getId() == R.id.myInflowDeleteButton){
			
			handleInflowDeleteButtonClick();
		}	
    }
	private void handleInflowAddButtonClick() {
     	 Intent intent = new Intent(this, inflowAdd.class);
		 startActivity(intent);
	     }
	private void handleInflowDeleteButtonClick() {
     	 Intent intent = new Intent(this, inflowDelete.class);
		 startActivity(intent);
	     }

}
