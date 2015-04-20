package com.pantech.aabsync.service.aidl;

interface ISyncService{
	void startSync();
	void stopSync();
	void cancelSync(); 
}