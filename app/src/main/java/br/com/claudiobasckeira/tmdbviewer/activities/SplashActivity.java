package br.com.claudiobasckeira.tmdbviewer.activities;

import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import br.com.claudiobasckeira.tmdbviewer.R;
import br.com.claudiobasckeira.tmdbviewer.activities.base.EventAwareActivity;
import br.com.claudiobasckeira.tmdbviewer.events.GetConfigurationAndGenresEvent;
import de.greenrobot.event.EventBus;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends EventAwareActivity {
    @AfterViews
    protected void init() {
        EventBus.getDefault().post(new GetConfigurationAndGenresEvent.Request());
    }

    public void onEventMainThread(GetConfigurationAndGenresEvent.Response response) {
        if(response.isError()) {
            Toast.makeText(
                    this,
                    R.string.network_error,
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        UpcomingMoviesActivity_.intent(this).start();
        finish();
    }
}
