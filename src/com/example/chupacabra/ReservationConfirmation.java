package com.example.chupacabra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ReservationConfirmation extends Activity implements OnClickListener {

	Button acceptButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		
		String bookTitle = intent.getStringExtra("BookTitle");
		
		setContentView(R.layout.reservationconfirmation);
		
		((TextView) findViewById(R.id.congratsText)).setText("Reservation Successful");
		((TextView) findViewById(R.id.congratsMessage)).setText("You may pick up " + bookTitle + " any time you are ready.")	;
		
		acceptButton = (Button) findViewById(R.id.acceptButton);
		
		acceptButton.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {

		if(v.getId() == R.id.acceptButton)
			this.finish();
	}
	
}
