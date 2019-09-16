package image.com.myapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText password,fname,lname,email,phone,rpass;
    Button submit;
    private FirebaseAuth mAuth;
    RadioButton male,female;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();


        password=(EditText)findViewById(R.id.password);
        submit=(Button)findViewById(R.id.submit);
        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        rpass=(EditText)findViewById(R.id.rpassword);
        male=(RadioButton)findViewById(R.id.radioMale);
        female=(RadioButton)findViewById(R.id.radioFemale);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailID=email.getText().toString();
                String passID=password.getText().toString();



                if(fname.getText().toString().trim().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Enter First name",Toast.LENGTH_SHORT).show();

                }
                else if(lname.getText().toString().trim().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Enter Last name",Toast.LENGTH_SHORT).show();

                }
                else if(!((male.isChecked())||(female.isChecked())))
                {
                    Toast.makeText(Signup.this, "Select your Gender", Toast.LENGTH_SHORT).show();

                }

                else if (phone.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Enter your mobile number",Toast.LENGTH_SHORT).show();
                }
                else if(email.getText().toString().trim().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"you have not entered email",Toast.LENGTH_SHORT).show();

                }
                if (!emailID.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {

                    email.setError("Invalid Email Address");

                }
                else if (password.getText().toString().length()<8)
                {
                    Toast.makeText(getApplicationContext(), "Password must be atleast 8 characters",Toast.LENGTH_SHORT).show();
                }
                else if (rpass.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Retype your Password ",Toast.LENGTH_SHORT).show();
                }
                else if(!(rpass.getText().toString().equals(password.getText().toString())))
                {
                    Toast.makeText(getApplicationContext(), "Password doesn't match",Toast.LENGTH_SHORT).show();


                }

                else {

                    mAuth.createUserWithEmailAndPassword(emailID,passID).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Signup.this, "Signup Success.Login to Continue...", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Signup.this, MainActivity.class);
                                startActivity(i);

                            }
                            else
                            {




                            }


                        }
                    });



                }






            }
        });


    }
}

