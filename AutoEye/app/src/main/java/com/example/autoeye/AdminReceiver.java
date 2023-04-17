package com.example.autoeye;

import static android.app.Activity.RESULT_OK;


import androidx.core.app.ActivityCompat;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class AdminReceiver extends DeviceAdminReceiver {
        private static final String TAG = "AdminReceiver";
        private static final int REQUEST_IMAGE_CAPTURE = 1;

        @Override
        public void onPasswordFailed(Context context, Intent intent) {
            Log.d(TAG, "Wrong password entered");

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default")
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle("Wrong password entered")
                    .setContentText("Someone has entered the wrong password to unlock your device.")
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(0, builder.build());
        }



    @Override
        public void onActivityResult(Context context, int requestCode, int resultCode, Intent data) {
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                // Save the image or do something with it
            }
        }
    }


