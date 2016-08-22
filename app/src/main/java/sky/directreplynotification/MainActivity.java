package sky.directreplynotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Where should direct replies be put in the intent bundle (can be any string)
    private static final String KEY_TEXT_REPLY = "key_text_reply";

    // Notification id that allows you to update the notification later on.
    private static final int NOTIFY_ID = 101;

    public void onButtonClicked(View view) {
        // Create the PendingIntent to handle user Direct Reply input
        Intent intent = new Intent(this, DirectReplyService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this,
                (int) System.currentTimeMillis(), intent, 0);

        // When you're using Broadcast Receiver
//        Intent intent = new Intent(this, DirectReplyReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), intent, 0);

        // Create the RemoteInput specifying this key
        String replyLabel = "Reply";
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();

        // Add to your action, enabling Direct Reply for it
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, replyLabel,
                        pendingIntent)
                        .addRemoteInput(remoteInput)
                        .setAllowGeneratedReplies(true)
                        .build();

NotificationCompat.Builder mBuilder =
        new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Just met you")
                .setContentText("Hi, nice to meet you.")
                .addAction(action);

NotificationManager mNotificationManager =
        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

mNotificationManager.notify(NOTIFY_ID, mBuilder.build());
    }
}
