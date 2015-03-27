package com.monapplication.testlayout;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends FragmentActivity {

    //déclaration de mon spinner
    Spinner mon_spinner ;

    //déclaration des EditText
    EditText edit_text_date ;
    EditText edit_text_lieu ;

    //declaration des TextView
    TextView rechercher_date ;
    TextView rechercher_lieu ;
    TextView rechercher_categories ;
    TextView choisir_periode ;

    //delcaration du bouton lançant le dialogue avec selecteur de date
    Button bouton_date ;

   // autres variables internes à la classe
    private int annee ;
    private int mois ;
    private int jour;

    // declaration des radiobuttons

    RadioButton radioButton_date ;
    RadioButton radioButton_periode ;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //récupération des elements du layout dans des variables java
        edit_text_date = (EditText) findViewById(R.id.editText_date);
        edit_text_lieu = (EditText) findViewById(R.id.editText_lieu);

        rechercher_date = (TextView) findViewById(R.id.rechercher_date);
        rechercher_lieu = (TextView) findViewById(R.id.rechercher_lieu);
        rechercher_categories = (TextView) findViewById(R.id.rechercher_categories);

        mon_spinner = (Spinner) findViewById(R.id.ma_periode);

        bouton_date = (Button) findViewById(R.id.changer_date);

        choisir_periode = (TextView)findViewById(R.id.chosir_periode) ;

        radioButton_date = (RadioButton)findViewById(R.id.radioButton_date) ;
        radioButton_periode = (RadioButton)findViewById(R.id.radioButton_periode) ;

        // initialement, selection par date (par défaut)
        radioButton_date.setChecked(true) ;
        radioButton_periode.setChecked(false);

        choisir_periode.setEnabled(false);
        mon_spinner.setEnabled(false);
        radioButton_periode.setChecked(false);


        //rendre EditText inchangeable par utilisateur - seulement par bouton créer à cet effet
        edit_text_date.setKeyListener(null);


// SPINNER
        // on va utiliser un Adapter pour contenir sous le format "element de spinner" chaque entrée de la liste dates_array et qui contient les différents choix possibles
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dates_array, android.R.layout.simple_spinner_dropdown_item);

        // Specifier la représentation à utiliser pour avoir l'apparence d'une spinner deroulant
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // on affecte l'adapter precedemment défini de manière générique à notre objet java mon_spinner, issu du layout
        mon_spinner.setAdapter(adapter);

        // pour apeller les méthodes définissant ce qu'on fait de l'element choisi - crée un cast exception je sais pas pq
        //mon_spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // alternative à option précédente d'apeller des méthodes dans une autre classe : faire une action précise sur item selectionné
        // la ligne suivante récupère l'element selectionné - reste à appliquer des methodes dessus pour faire ce qu'on veut (ajout d'un filtre)
        mon_spinner.getItemAtPosition(mon_spinner.getSelectedItemPosition());


//SELECTION DATE

        // affichage de la date courante dans le EditText, par défaut
        Calendar calendrier = Calendar.getInstance();

        //format de l'Edit text choisi
        SimpleDateFormat format_chaine = new SimpleDateFormat("dd MMM yyyy");

        //mise de la date dans champs editText comme date courante
        try {
            //edit_text_date.setText(format.format(new Date()));
            edit_text_date.setText(format_chaine.format(new Date(calendrier.getTimeInMillis())));

        } catch (Exception e) {
            e.getMessage();
        }
   }


    public void showDatePickerDialog(View v) {
        //creation de la boite de dialogue avec le selecteur de date
        DialogFragment fragment_selecteur = new SelecteurDate();
        //affichage du fragment de date d'ID unique FRAGMENT_SELECTEUR_DATE
        fragment_selecteur.show(getFragmentManager(), "FRAGMENT_SELECTEUR_DATE");


    }

// RADIOBOUTONS POUR SELECTION DE DATE OU DE PERIODE (à partir d'aujourd'hui)

    public void bouttonRadioMarque(View view) {

    // test si le bouton est marqué
    boolean test_bouton = ((RadioButton) view).isChecked();

    // Check which radio button was clicked
    switch(view.getId()) {
        case R.id.radioButton_date:
            if (test_bouton) {
                // ce que fait quand bouton radio sur date est pressé (desactivation du textview sur selection periode et du spinner)
                choisir_periode.setEnabled(false);
                mon_spinner.setEnabled(false);
                radioButton_periode.setChecked(false);

                edit_text_date.setEnabled(true);
                bouton_date.setEnabled(true);

            }

         //quand pas pressé, aura comportement par défaut (bouton radio date coché et bouton radio periode non coché et boutons associés inactifs
               break;

        case R.id.radioButton_periode:
            if (test_bouton) {
                // ce que fait quand bouton radio sur période est pressé (desactivation de edit text date et bouton selection date)
                edit_text_date.setEnabled(false);
                bouton_date.setEnabled(false);
                radioButton_date.setChecked(false);

                choisir_periode.setEnabled(true);
                mon_spinner.setEnabled(true);
                radioButton_periode.setEnabled(true);

            }
        //quand pas pressé, aura comportement par défaut (bouton radio date coché et bouton radio periode non coché et boutons associés inactifs
                break;
    }
}







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }









}
