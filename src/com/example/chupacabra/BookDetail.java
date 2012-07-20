package com.example.chupacabra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.shareResource.Resource;

public class BookDetail extends Activity implements OnClickListener{
	TextView bookTitle;
	TextView bookOwner;
	TextView bookDescription;
	
	Button back;
	Button reserve;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_detail);
		bookTitle = (TextView) findViewById(R.id.bookTitle);		
		bookOwner = (TextView) findViewById(R.id.bookOwner);
		bookDescription = (TextView) findViewById(R.id.bookDescription);
		Intent intent = getIntent();
		Resource resource = (Resource) intent.getSerializableExtra("BookResource");
		
		bookTitle.setText(resource.getResourceName());
		bookOwner.setText(resource.getOwner().getName());
		bookDescription.setText("This is the description field");
		
		back = (Button) findViewById(R.id.bookBack);
		reserve = (Button) findViewById(R.id.bookReserve);
		
		back.setOnClickListener(this);
		reserve.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if(v.getId()==R.id.bookBack)
			finish();
		else if (v.getId()==R.id.bookReserve)
			handleBookReserve();
		
	}


	private void handleBookReserve() {

		Intent intent = new Intent(this, ReservationConfirmation.class);
		
		intent.putExtra("BookTitle", ((TextView) findViewById(R.id.bookTitle)).getText() );
		startActivity(intent);
		
	}
}
