package sky.directreplynotification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;

public class DirectReplyReceiver extends BroadcastReceiver {

    private static final String KEY_TEXT_REPLY = "key_text_reply";

private static final int NOTIFY_ID = 101;

@Override
public void onReceive(Context context, Intent intent) {
    NotificationManager mNotificationManager =
            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(context)
            .setSmallIcon(R.mipmap.ic_launcher);

mNotifyBuilder.setStyle(new NotificationCompat.MessagingStyle("Me")
        .addMessage("Hi, nice to meet you.", (int) System.currentTimeMillis(),
                "Just met you")
        .addMessage(getMessageText(intent), (int) System.currentTimeMillis(),
                "Me"));

    mNotificationManager.notify(
            NOTIFY_ID,
            mNotifyBuilder.build());
}

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(KEY_TEXT_REPLY);
        }
        return null;
    }
}
