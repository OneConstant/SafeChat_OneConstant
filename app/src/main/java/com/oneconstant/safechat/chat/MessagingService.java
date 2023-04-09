package com.oneconstant.safechat.chat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.Constants;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.oneconstant.safechat.screens.fragments.Chat;

import java.util.Objects;
import java.util.Random;

public class MessagingService extends FirebaseMessagingService {
    public static final String REMOTE_MSG_TYPE = "type";
    public static final String REMOTE_MSG_INVITATION = "invitation";
    public static final String REMOTE_MSG_MEETING_TYPE = "meetingType";
    public static final String REMOTE_MSG_INVITER_TOKEN = "inviterToken";
    public static final String REMOTE_MSG_INVITATION_RESPONSE = "invitationResponse";
    public static final String REMOTE_MSG_INVITATION_ACCEPTED = "accepted";
    public static final String REMOTE_MSG_INVITATION_REJECTED = "rejected";
    public static final String REMOTE_MSG_INVITATION_CANCELLED = "cancelled";
    public static final String REMOTE_MSG_MEETING_ROOM = "meetingRoom";
    public static final String KEY_FCM_TOKEN = "fcmToken";
    public static final String KEY_NAME = "name";
    public static final String KEY_USER_ID = "userId";

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("FCM", "Token: " + token);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        String type = message.getData().get(REMOTE_MSG_TYPE);
        Log.d("FCM", "Message: " + Objects.requireNonNull(message.getNotification()).getBody());
        if (type != null) {
            if(type.equals(REMOTE_MSG_INVITATION)) {
                // pass
            }
            else if (type.equals(REMOTE_MSG_INVITATION_RESPONSE)) {
                Intent intent = new Intent(REMOTE_MSG_INVITATION_RESPONSE);
                intent.putExtra(
                        REMOTE_MSG_INVITATION_RESPONSE,
                        message.getData().get(REMOTE_MSG_INVITATION_RESPONSE)
                );
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
            }
            else {
                String id = message.getData().get(KEY_USER_ID);
                String name = message.getData().get(KEY_NAME);
                String token = message.getData().get(KEY_FCM_TOKEN);

                int notificationId = new Random().nextInt(200) + 100;
                String channelId = "chat_message";

                Intent intent = new Intent(this, Chat.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.putExtra(KEY_USER, );

                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence channelName = "Chat Message";
                    String channelDescription = "This notification channel is used for chat message notifications";
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
                    channel.setDescription(channelDescription);
                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                    notificationManagerCompat.notify(notificationId, builder.build());
                } else {
                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    manager.notify(notificationId, builder.build());
                }
            }
        }
    }
}
