package image.com.myapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button login,b2;
    int counter=3;
    TextView txt1;
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkConnection();

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);

        txt1=(TextView)findViewById(R.id.txt1);
        txt1.setVisibility(View.GONE);
        b2 = (Button)findViewById(R.id.b2);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){

            startActivity(new Intent(MainActivity.this, profile.class));
        }



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userID=username.getText().toString();
                String passID=password.getText().toString();

                if(passID.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }



                if(userID.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Enter Email Address", Toast.LENGTH_SHORT).show();
                }
                if (!userID.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {

                    username.setError("Invalid Email Address");

                }


                if(!(userID.isEmpty())&&!(passID.isEmpty()))
                {

                    mAuth.signInWithEmailAndPassword(userID,passID).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {

                                Toast.makeText(MainActivity.this, "Login Successful..", Toast.LENGTH_SHORT).show();
                                Intent ip=new Intent(MainActivity.this,profile.class);
                                startActivity(ip);

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "User not verified",Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                }
                else {

                }




            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Signup Now", Toast.LENGTH_SHORT).show();
                Intent in=new Intent(MainActivity.this,Signup.class);
                startActivity(in);
            }
        });
    }

    public void checkConnection()
    {
        ConnectivityManager manager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=manager.getActiveNetworkInfo();

        if(null!=activeNetwork)
        {
            if(activeNetwork.getType()==ConnectivityManager.TYPE_WIFI)
            {
                Toast.makeText(this, "You're Online", Toast.LENGTH_SHORT).show();
            }
            if(activeNetwork.getType()==ConnectivityManager.TYPE_MOBILE)
            {
                Toast.makeText(this, "You're online", Toast.LENGTH_SHORT).show();
            }


        }
        else
        {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();

        }

    }





}


