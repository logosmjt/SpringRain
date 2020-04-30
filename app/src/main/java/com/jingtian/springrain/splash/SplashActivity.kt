package com.jingtian.springrain.splash

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil.setContentView
import com.jingtian.springrain.IMyAidlInterface
import com.jingtian.springrain.MainActivity
import com.jingtian.springrain.R
import com.jingtian.springrain.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    private lateinit var mIntent: Intent
    private lateinit var myAidlInterface: IMyAidlInterface
    private lateinit var mManager: NotificationManager

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {

        }

        override fun onServiceConnected(p0: ComponentName?, iBinder: IBinder?) {
            myAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<FragmentHomeBinding>(this,
            R.layout.activity_splash
        )

        mIntent = Intent()
        mIntent.component =
            ComponentName("com.jingtian.springrain", "com.jingtian.springrain.MyService")
        bindService(mIntent, connection, Context.BIND_AUTO_CREATE)

        mManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        aidlBtn.setOnClickListener{
            Toast.makeText(this, "myAidlInterface name:" + myAidlInterface.name,
                Toast.LENGTH_SHORT).show()
        }

        notificationBtn.setOnClickListener{
            val NOTIFICATION_ID = 234
            mManager.notify(NOTIFICATION_ID, buildNotification())
        }
    }

    fun buildNotification(): Notification? {
        val CHANNEL_ID = "1"
        val manager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channelName = "channel_name"
            val channel =
                NotificationChannel(
                    CHANNEL_ID,
                    channelName,
                    NotificationManager.IMPORTANCE_HIGH
                )

            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        var builder =
            NotificationCompat.Builder(this, CHANNEL_ID)
                //小图标
                .setSmallIcon(R.mipmap.ic_launcher_round)
                //标题
                .setContentTitle("标题")
                //正文
                .setContentText("正文...")
                .setContentIntent(pendingIntent)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText("正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文" +
                                "正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文" +
                                "正文正文正文...")
                )

        return builder.build()
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }
}