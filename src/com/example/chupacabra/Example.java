/*
 * Copyright 2010 Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.chupacabra;

//Jeremy Colwick was Here!


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chupacabra.SessionEvents.AuthListener;
import com.example.chupacabra.SessionEvents.LogoutListener;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intuit.codejam.charts.BudgetPieChart;

public class Example extends Activity {

	// Your Facebook Application ID must be set before running this example
	// See http://www.facebook.com/developers/createapp.php
	public static final String APP_ID = "175729095772478";

	private LoginButton mLoginButton;
	private TextView mText;
	private Button mRequestButton;
	private Button mPostButton;
	private Button mDeleteButton;
	private Button mUploadButton;

	private Button getBudgetGraph;
	private Button getSplitBill;
	private Button getLoanBorrow;
	private Button getMarketButton;

	private ImageView chart;
	ArrayList<Friend> listFriend;
	
	private Facebook mFacebook;
	private AsyncFacebookRunner mAsyncRunner;

	private Context context;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;

		
		
		if (APP_ID == null) {
			Util.showAlert(this, "Warning", "Facebook Applicaton ID must be " +
			"specified before running this example: see Example.java");
		}

		setContentView(R.layout.main);
		mLoginButton = (LoginButton) findViewById(R.id.login);
		mText = (TextView) Example.this.findViewById(R.id.txt);
		mRequestButton = (Button) findViewById(R.id.requestButton);
		mPostButton = (Button) findViewById(R.id.postButton);
		mDeleteButton = (Button) findViewById(R.id.deletePostButton);
		mUploadButton = (Button) findViewById(R.id.uploadButton);
		
		chart = (ImageView) findViewById(R.id.chartImage);
		
		
		
		getBudgetGraph = (Button) findViewById(R.id.getBudgetGraph);
		getSplitBill = (Button) findViewById(R.id.getSplitBill);
		getLoanBorrow = (Button) findViewById(R.id.getLoanBorrow);
		getMarketButton = (Button) findViewById(R.id.getMarketPage);


		mFacebook = new Facebook(APP_ID);
		mAsyncRunner = new AsyncFacebookRunner(mFacebook);

		SessionStore.restore(mFacebook, this);
		SessionEvents.addAuthListener(new SampleAuthListener());
		SessionEvents.addLogoutListener(new SampleLogoutListener());
		mLoginButton.init(this, mFacebook);

		getBudgetGraph.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				Log.e("Example", "Testing getBudgetGraph()");
				
				Intent intent = new Intent(context, BudgetOverview.class);
				startActivity(intent);	
			}

		});
		
		getMarketButton.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				Log.e("Example", "Testing getBudgetGraph()");
				
				Intent intent = new Intent(context, MyMarket.class);
				intent.putExtra("friendList", listFriend);
				startActivity(intent);	
			}

		});
		getBudgetGraph.setVisibility(mFacebook.isSessionValid() ?
				View.VISIBLE :
					View.INVISIBLE);
		getMarketButton.setVisibility(mFacebook.isSessionValid() ?
				View.VISIBLE :
					View.INVISIBLE);
		chart.setVisibility(mFacebook.isSessionValid() ?
				View.VISIBLE :
					View.INVISIBLE);
		//        
		//        getSplitBill
		//        getLoanBorrow

		getSplitBill.setOnClickListener(new OnClickListener(){

			public void onClick(View v){
				Intent intent = new BudgetPieChart().execute(context);
				startActivity(intent);	
			}

		});

		getSplitBill.setVisibility(mFacebook.isSessionValid() ?
				View.VISIBLE :
					View.INVISIBLE);

		if (mFacebook.isSessionValid()) 
			fillFriendList();
		getLoanBorrow.setOnClickListener(new OnClickListener(){

			public void onClick(View v){
				Intent intent = new BudgetPieChart().execute(context);
				startActivity(intent);	
			}

		});
		getLoanBorrow.setVisibility(mFacebook.isSessionValid() ?
				View.VISIBLE :
					View.INVISIBLE);

		mRequestButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mAsyncRunner.request("me", new SampleRequestListener());
			}
		});
//		mRequestButton.setVisibility(mFacebook.isSessionValid() ?
//				View.VISIBLE :
//					View.INVISIBLE);

		mUploadButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Bundle params = new Bundle();
				params.putString("method", "photos.upload");

				URL uploadFileUrl = null;
				try {
					uploadFileUrl = new URL(
					"http://www.facebook.com/images/devsite/iphone_connect_btn.jpg");
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				try {
					HttpURLConnection conn= (HttpURLConnection)uploadFileUrl.openConnection();
					conn.setDoInput(true);
					conn.connect();
					int length = conn.getContentLength();

					byte[] imgData =new byte[length];
					InputStream is = conn.getInputStream();
					is.read(imgData);
					params.putByteArray("picture", imgData);

				} catch  (IOException e) {
					e.printStackTrace();
				}

				mAsyncRunner.request(null, params, "POST",
						new SampleUploadListener(), null);
			}
		});
//		mUploadButton.setVisibility(mFacebook.isSessionValid() ?
//				View.VISIBLE :
//					View.INVISIBLE);

		mPostButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mFacebook.dialog(Example.this, "feed",
						new SampleDialogListener());
			}
		});
//		mPostButton.setVisibility(mFacebook.isSessionValid() ?
//				View.VISIBLE :
//					View.INVISIBLE);
	}

	private void fillFriendList() {

		String friendsList = null;
		
		try{
			  
			  friendsList = mFacebook.request("me/friends");

			}catch(Exception e){
			    Log.e("Error on Auth prob", " " + "SampleAuthListener" + " Exception = "+e.getMessage());
			}
		
		Log.d("Friend List Check: ",friendsList);
		
		JSONObject json = null;
		
		
        String friends = null;
		try {
			json = new JSONObject(friendsList);	
			friends =  json.getString("data");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String jsonOutput = friends;
		Type listType = new TypeToken<ArrayList<Friend>>(){}.getType();
		listFriend = (ArrayList<Friend>) gson.fromJson(jsonOutput, listType);
		
		
			Log.d("Name: " , "Got Friend List");
				
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent data) {
		mFacebook.authorizeCallback(requestCode, resultCode, data);
	}

	public class SampleAuthListener implements AuthListener {

		public void onAuthSucceed() {
			mText.setText("You have logged in! ");
//			mRequestButton.setVisibility(View.VISIBLE);
//			mUploadButton.setVisibility(View.VISIBLE);
//			mPostButton.setVisibility(View.VISIBLE);
			getBudgetGraph.setVisibility(View.VISIBLE);
			getSplitBill.setVisibility(View.VISIBLE);
			getLoanBorrow.setVisibility(View.VISIBLE);
			chart.setVisibility(View.VISIBLE);
			getMarketButton.setVisibility(View.VISIBLE);
			
			fillFriendList();
		}

		public void onAuthFail(String error) {
			mText.setText("Login Failed: " + error);
		}
	}

	public class SampleLogoutListener implements LogoutListener {
		public void onLogoutBegin() {
			mText.setText("Logging out...");
		}

		public void onLogoutFinish() {
			mText.setText("You have logged out! ");
			mRequestButton.setVisibility(View.INVISIBLE);
			mUploadButton.setVisibility(View.INVISIBLE);
			mPostButton.setVisibility(View.INVISIBLE);
			getBudgetGraph.setVisibility(View.INVISIBLE);
			getSplitBill.setVisibility(View.INVISIBLE);
			getLoanBorrow.setVisibility(View.INVISIBLE);
			chart.setVisibility(View.INVISIBLE);
			getMarketButton.setVisibility(View.INVISIBLE);

		}
	}

	public class SampleRequestListener extends BaseRequestListener {

		public void onComplete(final String response, final Object state) {
			try {
				// process the response here: executed in background thread
				Log.d("Facebook-Example", "Response: " + response.toString());
				JSONObject json = Util.parseJson(response);
				final String name = json.getString("name");

				// then post the processed result back to the UI thread
				// if we do not do this, an runtime exception will be generated
				// e.g. "CalledFromWrongThreadException: Only the original
				// thread that created a view hierarchy can touch its views."
				Example.this.runOnUiThread(new Runnable() {
					public void run() {
						mText.setText("Hello there, " + name + "!");
					}
				});
			} catch (JSONException e) {
				Log.w("Facebook-Example", "JSON Error in response");
			} catch (FacebookError e) {
				Log.w("Facebook-Example", "Facebook Error: " + e.getMessage());
			}
		}
	}

	public class SampleUploadListener extends BaseRequestListener {

		public void onComplete(final String response, final Object state) {
			try {
				// process the response here: (executed in background thread)
				Log.d("Facebook-Example", "Response: " + response.toString());
				JSONObject json = Util.parseJson(response);
				final String src = json.getString("src");

				// then post the processed result back to the UI thread
				// if we do not do this, an runtime exception will be generated
				// e.g. "CalledFromWrongThreadException: Only the original
				// thread that created a view hierarchy can touch its views."
				Example.this.runOnUiThread(new Runnable() {
					public void run() {
						mText.setText("Hello there, photo has been uploaded at \n" + src);
					}
				});
			} catch (JSONException e) {
				Log.w("Facebook-Example", "JSON Error in response");
			} catch (FacebookError e) {
				Log.w("Facebook-Example", "Facebook Error: " + e.getMessage());
			}
		}
	}
	public class WallPostRequestListener extends BaseRequestListener {

		public void onComplete(final String response, final Object state) {
			Log.d("Facebook-Example", "Got response: " + response);
			String message = "<empty>";
			try {
				JSONObject json = Util.parseJson(response);
				message = json.getString("message");
			} catch (JSONException e) {
				Log.w("Facebook-Example", "JSON Error in response");
			} catch (FacebookError e) {
				Log.w("Facebook-Example", "Facebook Error: " + e.getMessage());
			}
			final String text = "Your Wall Post: " + message;
			Example.this.runOnUiThread(new Runnable() {
				public void run() {
					mText.setText(text);
				}
			});
		}
	}

	public class WallPostDeleteListener extends BaseRequestListener {

		public void onComplete(final String response, final Object state) {
			if (response.equals("true")) {
				Log.d("Facebook-Example", "Successfully deleted wall post");
				Example.this.runOnUiThread(new Runnable() {
					public void run() {
						mDeleteButton.setVisibility(View.INVISIBLE);
						mText.setText("Deleted Wall Post");
					}
				});
			} else {
				Log.d("Facebook-Example", "Could not delete wall post");
			}
		}
	}

	public class SampleDialogListener extends BaseDialogListener {

		public void onComplete(Bundle values) {
			final String postId = values.getString("post_id");
			if (postId != null) {
				Log.d("Facebook-Example", "Dialog Success! post_id=" + postId);
				mAsyncRunner.request(postId, new WallPostRequestListener());
				mDeleteButton.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						mAsyncRunner.request(postId, new Bundle(), "DELETE",
								new WallPostDeleteListener(), null);
					}
				});
				mDeleteButton.setVisibility(View.VISIBLE);
			} else {
				Log.d("Facebook-Example", "No wall post made");
			}
		}
	}

	
}
