package com.example.scame.testingsamples

import android.app.IntentService
import android.content.Intent
import org.greenrobot.eventbus.EventBus
import java.util.*


class FileDownloadService: IntentService(FileDownloadService::class.java.canonicalName) {

    override fun onHandleIntent(p0: Intent?) {
        Thread.sleep(5000)
        EventBus.getDefault().post(FileDownloadedEvent(Random().nextInt(100)))
    }
}