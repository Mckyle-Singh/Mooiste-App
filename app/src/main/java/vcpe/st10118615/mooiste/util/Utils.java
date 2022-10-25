package vcpe.st10118615.mooiste.util;

import android.app.Activity;
import android.os.Build;
import android.view.View;

import androidx.core.content.ContextCompat;

import vcpe.st10118615.mooiste.R;

public class Utils {
    private static final String TAG = "productArrayList";

    public static void statusBarColor(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, R.color.black));
        }
    }
}
