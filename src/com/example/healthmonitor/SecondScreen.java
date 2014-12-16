package com.example.healthmonitor;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.buddy.BuddyMain;
import com.example.graphs.AnotherBarActivity;
import com.example.notification.NotificationMainActivity;

public class SecondScreen extends Activity implements OnClickListener{

	private Button notificationButton;
	private Button graphButton;
	private Button buddyButton;
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_screen);
		notificationButton = (Button) findViewById(R.id.nfButton);
		graphButton = (Button) findViewById(R.id.graphButton);
		buddyButton = (Button) findViewById(R.id.buddyButton);
		notificationButton.setOnClickListener(this);
		graphButton.setOnClickListener(this);
		buddyButton.setOnClickListener(this);
		
		
	}
	
	public void buttonClicked(View v){

      
       


    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		  if(v.getId() == R.id.nfButton){
	        	Intent in =  new Intent(SecondScreen.this,NotificationMainActivity.class);
				startActivity(in);
	        }
	        else if(v.getId() == R.id.graphButton){
	        	Intent in =  new Intent(SecondScreen.this,AnotherBarActivity.class);
				startActivity(in);
	           
	        }
	        else if(v.getId() == R.id.buddyButton){
	        	Intent in =  new Intent(SecondScreen.this,BuddyMain.class);
				startActivity(in);
	           
	        }
	}
	
}
