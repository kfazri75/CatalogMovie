package com.d3mstudio.catalogmovie.stackWidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by kfazri75 on 2/16/18.
 */

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}