package com.d3mstudio.catalogmovie.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.d3mstudio.catalogmovie.DetailActivity;
import com.d3mstudio.catalogmovie.R;
import com.d3mstudio.catalogmovie.model.MovieModel;
import com.d3mstudio.catalogmovie.model.MovieResponse;
import com.d3mstudio.catalogmovie.networking.ApiBuilder;
import com.d3mstudio.catalogmovie.networking.ApiService;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import com.google.gson.Gson;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.d3mstudio.catalogmovie.BuildConfig.API_KEY;

/**
 * Created by kfazri75 on 2/17/18.
 */

public class UpComingReminderMovie extends GcmTaskService {

    public static String TAG_TASK_UPCOMING = "upcoming movies";

    private Call<MovieModel> apiCall;
    private ApiBuilder apiClient = new ApiBuilder();

    @Override
    public int onRunTask(TaskParams taskParams) {
        int result = 0;
        if (taskParams.getTag().equals(TAG_TASK_UPCOMING)) {
            loadData();
            result = GcmNetworkManager.RESULT_SUCCESS;
        }

        return result;
    }

    private void loadData() {
        ApiService apiService = ApiBuilder.getClient(this).create(ApiService.class);
        Call<MovieResponse> call = apiService.getUpcoming(API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
//                final List<MovieModel> movies = response.body().getResults();
                if (response.isSuccessful()) {
                    List<MovieModel> items = response.body().getResults();
                    int index = new Random().nextInt(items.size());

                    MovieModel item = items.get(index);
                    String title = items.get(index).getTitle();
                    String message = items.get(index).getOverview();
                    String time = items.get(index).getReleaseDate();
                    String poster = items.get(index).getPosterPath();
                    int notifId = 200;

                    showNotification(getApplicationContext(), title, message, notifId, item);

                } else loadFailed();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }

    private void loadFailed() {
        Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show();
    }

    private void showNotification(Context context, String title, String message, int notifId, MovieModel item) {
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        String time = item.getReleaseDate();
        String poster = item.getPosterPath();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_TITLE, title);
        intent.putExtra(DetailActivity.EXTRA_OVERVIEW, message);
        intent.putExtra(DetailActivity.EXTRA_TIME, time);
        intent.putExtra(DetailActivity.EXTRA_POSTER, poster);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notifId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound);

        notificationManagerCompat.notify(notifId, builder.build());
    }
}

