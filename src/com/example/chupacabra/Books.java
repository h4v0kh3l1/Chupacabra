package com.example.chupacabra;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.shareResource.Person;
import com.shareResource.Resource;

public class Books extends Activity implements OnClickListener{

	Button myAddButton; //declared a reference variable of type Button
	Button myDeleteButton;

	ArrayList<Friend> friendList;
	Intent intent;
	ArrayList<Resource> myBookList;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.books);

		myAddButton = (Button) findViewById(R.id.myAddButton);
		myDeleteButton = (Button) findViewById(R.id.myDeleteButton);

		myAddButton.setOnClickListener(this);
		myDeleteButton.setOnClickListener(this);

		intent = getIntent();

		friendList = (ArrayList<Friend>) intent.getSerializableExtra("friendList");

		createStaticBookContent();

	}

	//Create bunch of static books and output those items as children to tablelayout in .xml layout
	private void createStaticBookContent() {

		myBookList = new ArrayList<Resource>();


		String[] titles = {
				"Grapes of Wrath",
				"P vs NP",
				"Calculus I",
				"Calculus II",
				"Data Structures",
				"Physics for Presidents",
				"Fear and Loathing in Las Vegas",
				"Design for Delight",
				"Green Eggs and Ham",
				"Harry Potter",
				"1984",
				"Thermodynamics",
				"Adv Udwtr Basket Weaving"
		};

		for(int i = 0; i < titles.length; i++)
			myBookList.add(new Resource(titles[i % titles.length], new Person( (friendList.get( (int) ((Math.random() * 582) % friendList.size())).getName()))));



		createRows(myBookList);


	}
	private void createRows(ArrayList<Resource> myBookList) {

		TableLayout tl=(TableLayout)findViewById(R.id.myResourceTable);    
		tl.removeAllViews();
		
		TableRow titleRow = new TableRow(this);
		titleRow.setLayoutParams(new LayoutParams( LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		titleRow.setBackgroundColor(Color.BLUE);
		TextView bookTitle = new TextView(this);
		bookTitle.setText("Book Title");
		bookTitle.setTextColor(Color.WHITE);
		bookTitle.setTypeface(null, Typeface.BOLD);
		//textview.getTextColors(R.color.)
		//textview.setTextColor(Color.YELLOW);
		titleRow.addView(bookTitle);
		
		TextView bookOwner = new TextView(this);
		bookOwner.setText("Book Owner");
		bookOwner.setTextColor(Color.WHITE);
		bookOwner.setTypeface(null, Typeface.BOLD);
		//textview.getTextColors(R.color.)
		//textview.setTextColor(Color.YELLOW);
		titleRow.addView(bookOwner);
		
		tl.addView(titleRow);

		for(int i = 0; i < myBookList.size(); i++){

			TableRow tr1 = new TableRow(this);
			tr1.setLayoutParams(new LayoutParams( LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
			TextView textview = new TextView(this);
			textview.setTag("BookTitle");
			textview.setText(myBookList.get(i).getResourceName());
			textview.setTextColor(Color.BLACK);
			//textview.getTextColors(R.color.)
			//textview.setTextColor(Color.YELLOW);
			tr1.addView(textview);
			
			TextView textview2 = new TextView(this);
			textview2.setTag("BookOwner");
			textview2.setText(myBookList.get(i).getOwner().getName());
			textview2.setTextColor(Color.BLACK);
			
			textview.setOnClickListener(this);
			textview2.setOnClickListener(this);

			//textview.getTextColors(R.color.)
			//textview.setTextColor();
			tr1.addView(textview2);
			
			
			tl.addView(tr1, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		}

	}

	public void onClick(View v) {

		if(v.getId() == R.id.myAddButton){

			handleAddButtonClick();
		} 
		else if(v.getId() == R.id.myDeleteButton){

			handleDeleteButtonClick();
		}
		else if(v.getTag().equals("BookTitle")){
			Intent intent = new Intent(this, BookDetail.class);
			
			for (Resource r : myBookList){
				if (r.getResourceName().equals(((TextView)v).getText())){
					intent.putExtra("BookResource",r);
				}
			}
			startActivity(intent);
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
