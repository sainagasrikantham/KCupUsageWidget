package com.csapps.kcupusagewidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class KCupWidget extends AppWidgetProvider {
	
	    private static final String KCUP_SMALL_CLICKED  = "KCupWidgetSmallClick";
	    private static final String KCUP_MEDIUM_CLICKED = "KCupWidgetMediumClick";
	    private static final String KCUP_LARGE_CLICKED  = "KCupWidgetLargeClick";
	    private static final String KCUP_RESET_CLICKED  = "KCupWidgetResetClick";
	    
	    private static boolean kCupSmallUsed  = false;
		private static boolean kCupMediumUsed = false;
		private static boolean kCupLargeUsed  = false;
		
		@Override
		public void onUpdate(Context context, AppWidgetManager appWidgetManager,
				int[] appWidgetIds) {
			// TODO Auto-generated method stub
			super.onUpdate(context, appWidgetManager, appWidgetIds);

			final int N = appWidgetIds.length;
			for (int i=0; i<N; i++) {
				int appWidgetId = appWidgetIds[i];
				updateAppWidget(context, appWidgetManager, appWidgetId);
			}
		}
		
		public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
			RemoteViews remoteViews;
			ComponentName watchWidget;

			remoteViews = new RemoteViews(context.getPackageName(), R.layout.kcup_widget);
			watchWidget = new ComponentName(context, KCupWidget.class);

			remoteViews.setOnClickPendingIntent(R.id.widget_icon_kcup_small,  getPendingSelfIntent(context, KCUP_SMALL_CLICKED));
			remoteViews.setOnClickPendingIntent(R.id.widget_icon_kcup_medium, getPendingSelfIntent(context, KCUP_MEDIUM_CLICKED));
			remoteViews.setOnClickPendingIntent(R.id.widget_icon_kcup_large,  getPendingSelfIntent(context, KCUP_LARGE_CLICKED));
			remoteViews.setOnClickPendingIntent(R.id.button_reset_usage,      getPendingSelfIntent(context, KCUP_RESET_CLICKED));

			appWidgetManager.updateAppWidget(watchWidget, remoteViews);
		}

	    @Override
	    public void onReceive(Context context, Intent intent) {
	        // TODO Auto-generated method stub
	        super.onReceive(context, intent);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            RemoteViews   remoteViews = new RemoteViews(context.getPackageName(), R.layout.kcup_widget);
            ComponentName watchWidget = new ComponentName(context, KCupWidget.class);

	        if (KCUP_SMALL_CLICKED.equals(intent.getAction())) {

	            if ( !kCupSmallUsed ) {
	            	
					kCupSmallUsed = true;
					
					Toast.makeText(context, "Small Cup Used", Toast.LENGTH_LONG).show();
					
					remoteViews.setImageViewResource(R.id.widget_icon_kcup_small, R.drawable.ic_kcup_small_used);
				}
	            
	        } else if (KCUP_MEDIUM_CLICKED.equals(intent.getAction())) {

	            if ( !kCupMediumUsed ) {
	            
					kCupMediumUsed = true;
					
					Toast.makeText(context, "Medium Cup Used", Toast.LENGTH_LONG).show();
					
					remoteViews.setImageViewResource(R.id.widget_icon_kcup_medium, R.drawable.ic_kcup_medium_used);
				}
	            
	        } else if (KCUP_LARGE_CLICKED.equals(intent.getAction())) {

	            if ( !kCupLargeUsed ) {
	            	
					kCupLargeUsed = true;
					
					Toast.makeText(context, "Large Cup Used", Toast.LENGTH_LONG).show();
					
					remoteViews.setImageViewResource(R.id.widget_icon_kcup_large, R.drawable.ic_kcup_large_used);
				}
	            
	        } else if (KCUP_RESET_CLICKED.equals(intent.getAction())) {     

	        	remoteViews.setImageViewResource(R.id.widget_icon_kcup_small,  R.drawable.ic_kcup_small);
	        	remoteViews.setImageViewResource(R.id.widget_icon_kcup_medium, R.drawable.ic_kcup_medium);
	        	remoteViews.setImageViewResource(R.id.widget_icon_kcup_large,  R.drawable.ic_kcup_large);

	        	kCupSmallUsed = kCupMediumUsed = kCupLargeUsed = false;

	        	Toast.makeText(context, "Usage has been reset", Toast.LENGTH_LONG).show();
	        }
	        
	        appWidgetManager.updateAppWidget(watchWidget, remoteViews);
	    }

	    protected static PendingIntent getPendingSelfIntent(Context context, String action) {
	        Intent intent = new Intent(context, KCupWidget.class);
	        intent.setAction(action);
	        return PendingIntent.getBroadcast(context, 0, intent, 0);
	    }
}
