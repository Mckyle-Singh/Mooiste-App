package vcpe.st10118615.mooiste.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;
import vcpe.st10118615.mooiste.R;
import vcpe.st10118615.mooiste.model.User;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    //related profile image
    private int PICK_IMAGE_REQUEST = 111;
    private Uri filePath;

    private ProgressBar progressBar;
    private CircleImageView UserProfileImg;
    private CircleImageView ChangeProfileBtn;
    private TextView UserNameDrawer;
    private String downloadImageUrl;
    // Firebase
    private StorageReference storageRef;
    private DatabaseReference myRootRef;
    private FirebaseAuth mAuth;

    private User user;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}