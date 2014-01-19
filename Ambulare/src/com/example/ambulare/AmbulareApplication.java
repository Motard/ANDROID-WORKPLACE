package com.example.ambulare;

import android.app.Application;

public class AmbulareApplication extends Application 
{
	
	private boolean _recording;
	private long _rota_id;
	
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

	public long get_rota_id() {
		return _rota_id;
	}

	public void set_rota_id(long _rota_id) {
		this._rota_id = _rota_id;
	}

	
}
