package ma.enset.exercice1_sommedeuxnombres;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Déclaration des variables pour les composants graphiques
    TextView resultat; // Affichage du résultat de la somme
    EditText nbr1, nbr2; // Entrées pour les deux nombres à sommer
    MaterialButton btnSomme, btnRenitialiser; // Boutons pour calculer la somme et réinitialiser les entrées

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Définition de la vue pour cette activité
        assignId(btnSomme,R.id.btnSomme); // Affectation d'un ID au bouton "Somme"
        assignId(btnRenitialiser,R.id.btnRenitialiser); // Affectation d'un ID au bouton "Réinitialiser"
        // Initialisation des composants graphiques
        resultat = findViewById(R.id.resultat);
        nbr1 =findViewById(R.id.nombre1);
        nbr2 =findViewById(R.id.nombre2);
    }
    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view; // Récupération du bouton qui a été cliqué
        String buttonText=button.getText().toString(); // Récupération du texte du bouton cliqué
        Float num1 = Float.parseFloat(nbr1.getText().toString()); // Récupération du premier nombre entré
        Float num2 = Float.parseFloat(nbr2.getText().toString()); // Récupération du deuxième nombre entré

        // Vérification du bouton cliqué
        if(buttonText.equals("Rénitialiser")){
            resultat.setText("0"); // Réinitialisation de l'affichage du résultat
            // Réinitialisation de la première entrée
            nbr1.clearFocus();
            nbr1.setText("");
            // Réinitialisation de la deuxième entrée
            nbr2.clearFocus();
            nbr2.setText("");
            return;
        }
        if(buttonText.equals("Somme")){
            Float somme = num1+num2; // Calcul de la somme
            resultat.setText(somme.toString()); // Affichage du résultat de la somme
            return;
        }

    }
    // Fonction pour affecter un ID à un bouton et ajouter un listener sur le clic
    void assignId (MaterialButton btn, int id){
        btn=findViewById(id); // Récupération du bouton depuis son ID
        btn.setOnClickListener(this); // Ajout d'un listener sur le clic
    }


}
