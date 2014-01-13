package com.example.ambulare;

import android.app.Application;

public class AmbulareApplication extends Application 
{
	
	private boolean _recording;
	
	@Override
	public void onCreate() 
	{
		super.onCreate();
		_recording = false;
	}

	public synchronized boolean isRecordingRoute()
	{
		return _recording;
	}
	
	public synchronized void setRecording(boolean state)
	{
		_recording = state;
	}

}
