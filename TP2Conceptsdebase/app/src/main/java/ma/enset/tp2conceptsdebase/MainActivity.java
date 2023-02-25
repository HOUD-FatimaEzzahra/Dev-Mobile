package ma.enset.tp2conceptsdebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout EmailTextInput;
    private TextInputLayout PasswordTextInput;
    private Button btnEnvoyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmailTextInput = findViewById(R.id.textEmail);
        PasswordTextInput = findViewById(R.id.textPassword);
        btnEnvoyer = findViewById(R.id.btnQuitter);

        btnEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EmailTextInput.getEditText().getText().toString();
                String password = PasswordTextInput.getEditText().getText().toString();

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });
    }
}

