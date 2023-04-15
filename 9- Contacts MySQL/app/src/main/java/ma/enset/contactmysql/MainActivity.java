package ma.enset.contactmysql;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Contact> contactList = new ArrayList<>();
    private ArrayAdapter<Contact> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.contact_list_view);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactList);
        listView.setAdapter(adapter);

        DBHelper dbHelper = new DBHelper(this);
        dbHelper.addContact(new Contact(0, "John", "Doe", "johndoe@example.com", "555-555-1234", null));
        dbHelper.addContact(new Contact(0, "Jane", "Doe", "janedoe@example.com", "555-555-5678", null));
        contactList.addAll(dbHelper.getAllContacts());
        adapter.notifyDataSetChanged();

        // Ajouter un nouveau contact
        findViewById(R.id.add_contact_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                intent.putExtra("action", "add");
                startActivityForResult(intent, 1);
            }
        });

        // Modifier un contact existant
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = (Contact) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                intent.putExtra("action", "edit");
                intent.putExtra("contact", (CharSequence) contact);
                startActivityForResult(intent, 2);
            }
        });

        // Supprimer un contact
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = (Contact) parent.getItemAtPosition(position);
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                dbHelper.deleteContact(contact);
                contactList.remove(contact);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                // Ajouter un nouveau contact
                Contact contact = (Contact) data.getSerializableExtra("contact");
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                dbHelper.addContact(contact);
                contactList.add(contact);
                adapter.notifyDataSetChanged();
            } else if (requestCode == 2) {
                // Modifier un contact existant
                Contact contact = (Contact) data.getSerializableExtra("contact");
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                dbHelper.updateContact(contact);
                int position = data.getIntExtra("position", -1);
                if (position != -1) {
                    contactList.set(position, contact);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
