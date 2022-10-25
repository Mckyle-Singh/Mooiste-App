package vcpe.st10118615.mooiste.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;
import vcpe.st10118615.mooiste.R;
import vcpe.st10118615.mooiste.util.Utils;

public class AdminLoginActivity extends AppCompatActivity {

    private static final String TAG = "AdminsigninTag";
    private String userEmail, userPassword;
    private EditText email, password;
    private Button loginBtn;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference myRootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        Paper.init(AdminLoginActivity.this);
        initAll();
        settingUpListners();
    }

    private void settingUpListners() {

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmail = email.getText().toString().trim();
                userPassword = password.getText().toString().trim();
                if (TextUtils.isEmpty(userEmail)) {
                    email.setError("Enter email");
                    email.requestFocus();
                } else if (TextUtils.isEmpty(userPassword)) {
                    password.setError("Enter password");
                    password.requestFocus();
                } else {
                    //call the signin function here
                    progressBar.setVisibility(View.VISIBLE);
                    loginBtn.setVisibility(View.GONE);
                    //Check authentication by using email and password

                    mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                        myRootRef.child("Admin").child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                //place data in datasnapshot that we can show
                                                if (dataSnapshot.exists()) {
                                                    progressBar.setVisibility(View.GONE);
                                                    Paper.book().write("active","admin");
                                                    Intent intent = new Intent(AdminLoginActivity.this, AdminHome.class);
                                                    Toast.makeText(AdminLoginActivity.this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    mAuth.signOut();
                                                    Toast.makeText(AdminLoginActivity.this, "Incorrect credentials", Toast.LENGTH_SHORT).show();
                                                    progressBar.setVisibility(View.INVISIBLE);
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    } else {
                                        //sign in Failed
                                        //setting progress bar
                                        progressBar.setVisibility(View.GONE);
                                        loginBtn.setEnabled(true);
                                        loginBtn.setVisibility(View.VISIBLE);
                                        //show message
                                        Toast.makeText(AdminLoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void initAll() {
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_pass);
        loginBtn = findViewById(R.id.login_btn);

        progressBar = findViewById(R.id.login_progress_bar);
        progressBar.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();
        myRootRef = FirebaseDatabase.getInstance().getReference();
        Utils.statusBarColor(AdminLoginActivity.this);
    }
}