package ma.enset.tp2conceptsdebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private TextView mEmailTextView;
    private TextView mPasswordTextView;
    private Button btnQuitter ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mEmailTextView = findViewById(R.id.email_text_view);
        mPasswordTextView = findViewById(R.id.password_text_view);
        btnQuitter = findViewById(R.id.btnQuitter);


        btnQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créez un nouvel intent pour retourner à la MainActivity1
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);

                // Effacez les champs de mail et de mot de passe
                EditText mail = findViewById(R.id.textUsername);
                EditText password = findViewById(R.id.textPassword);
                mail.setText("");
                password.setText("");

                // Démarrez l'activité précédente et fermez l'activité actuelle
                startActivity(intent);
                finish();
            }
        });


        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        mEmailTextView.setText(email);
        mPasswordTextView.setText(password);
    }


}
