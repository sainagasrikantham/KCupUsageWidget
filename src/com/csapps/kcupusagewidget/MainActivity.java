package com.csapps.kcupusagewidget;

import android.os.Bundle;
import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Set the configuration result as "Cancelled" at the beginning
		setResult(RESULT_CANCELED);

		Button buttonAgreeTerms = (Button) findViewById(R.id.button_agree_terms);
		buttonAgreeTerms.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
				final Context widgetContext = MainActivity.this;
				
				Intent intent = getIntent();
				Bundle extras = intent.getExtras();
				if (extras != null) {
					
					// Get the Widget ID
					mAppWidgetId = extras.getInt(
							AppWidgetManager.EXTRA_APPWIDGET_ID, 
							AppWidgetManager.INVALID_APPWIDGET_ID);
				}

				// Grab the AppWidgetManager instance and make the first "update" widget call
				AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(widgetContext);
				KCupWidget.updateAppWidget(widgetContext, appWidgetManager, mAppWidgetId);

				// Set the configuration result as "OK" as the user has pushed the button
				Intent resultValue = new Intent();
				resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
				setResult(RESULT_OK, resultValue);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
