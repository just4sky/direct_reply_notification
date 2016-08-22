package sky.directreplynotification;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;

public class DirectReplyService extends Service {

    private static final String KEY_TEXT_REPLY = "key_text_reply";

    private static final int NOTIFY_ID = 101;

    public DirectReplyService() {
    }

@Override
public int onStartCommand(Intent intent, int flags, int startId) {
    NotificationManager mNotificationManager =
            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher);

    mNotifyBuilder.setStyle(new NotificationCompat.MessagingStyle("Me")
            .addMessage("Hi, nice to meet you.", (int) System.currentTimeMillis(),
                    "Just met you")
            .addMessage(getMessageText(intent), (int) System.currentTimeMillis(),
                    "Me"));

    mNotificationManager.notify(
            NOTIFY_ID,
            mNotifyBuilder.build());

    return super.onStartCommand(intent, flags, startId);
}

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(KEY_TEXT_REPLY);
        }
        return null;
    }
}
