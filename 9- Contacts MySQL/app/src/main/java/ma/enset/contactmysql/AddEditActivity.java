package ma.enset.contactmysql;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class AddEditActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etEmail, etPhone;
    private ImageView ivPhoto;
    private Contact contact;
    private String action;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_activity);

        etFirstName = findViewById(R.id.first_name_edit_text);
        etLastName = findViewById(R.id.last_name_edit_text);
        etEmail = findViewById(R.id.email_edit_text);
        etPhone = findViewById(R.id.phone_edit_text);
        ivPhoto = findViewById(R.id.ivPhoto);

        action = getIntent().getStringExtra("action");
        if (action.equals("edit")) {
            contact = (Contact) getIntent().getSerializableExtra("contact");
            etFirstName.setText(contact.getFirstName());
            etLastName.setText(contact.getLastName());
            etEmail.setText(contact.getEmail());
            etPhone.setText(contact.getPhone());
            if (contact.getPhoto() != null) {
                ivPhoto.setImageBitmap(BitmapFactory.decodeByteArray(contact.getPhoto(), 0, contact.getPhoto().length));
            }
        }

        // Choisir une photo depuis la galerie
        findViewById(R.id.btnChoosePhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });

        // Enregistrer le contact
        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                byte[] photo = null;
                if (ivPhoto.getDrawable() != null) {
                    Bitmap bitmap = ((BitmapDrawable) ivPhoto.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    photo = stream.toByteArray();
                }

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(AddEditActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    if (action.equals("add")) {
                        contact = new Contact(0, firstName, lastName, email, phone, photo);
                        Intent intent = new Intent();
                        intent.putExtra("contact", (CharSequence) contact);
                        setResult(RESULT_OK, intent);
                    } else if (action.equals("edit")) {
                        contact.setFirstName(firstName);
                        contact.setLastName(lastName);
                        contact.setEmail(email);
                        contact.setPhone(phone);
                        contact.setPhoto(photo);
                        Intent intent = new Intent();
                        intent.putExtra("contact", (CharSequence) contact);
                        intent.putExtra("position", getIntent().getIntExtra("position", -1));
                        setResult(RESULT_OK, intent);
                    }
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 3 && data != null) {
            Uri uri = data.getData();
            ivPhoto.setImageURI(uri);
        }
    }
}

