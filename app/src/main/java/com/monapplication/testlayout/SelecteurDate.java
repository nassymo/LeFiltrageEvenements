package com.monapplication.testlayout;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nassymo on 24/03/2015.
 */
public class SelecteurDate extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // La date par défaut du dialogue avec le selecteur de date est la date courante
        final Calendar c = Calendar.getInstance();
        int annee = c.get(Calendar.YEAR);
        int mois = c.get(Calendar.MONTH);
        int jour = c.get(Calendar.DAY_OF_MONTH);

        //créer une instance de Date, initialisée avec la date courante
        Date date_actuelle = new Date() ;

        // Créer et retourner, une instance nouvelle de DatePickerDialog (fragment) initialisée avec les paramètres jours, mois, année
       // return new DatePickerDialog(getActivity(), this, annee, mois, jour);

        DatePickerDialog mon_dialogue = new DatePickerDialog(getActivity(), this, annee, mois, jour);

        // définir la date minimum du Dialog à date courante (on peut pas rechercher des évènements passés)
        DatePicker date_selecteur = mon_dialogue.getDatePicker() ;
        date_selecteur.setMinDate(date_actuelle.getTime()) ;


        //mon_dialogue.getDatePicker().setMinDate(new Date().getDate());

        return mon_dialogue ;

    }

    public void onDateSet(DatePicker view, int annee_set, int mois_set, int jour_set) {

        SimpleDateFormat format_chaine = new SimpleDateFormat("dd MMM yyyy");

        // creation d'une instance de Date avec données récupérées - dans la classe Date, année numérotée à partir de 1900
        Date ma_date_set = new Date(annee_set-1900, mois_set, jour_set) ;

        // récupération de date mise sous une chaine de caractères
        String date_to_string = format_chaine.format(ma_date_set);


        //mise à jour EditText de date
        ((EditText) getActivity().findViewById(R.id.editText_date)).setText(date_to_string);

            //((EditText) getActivity().findViewById(R.id.editText_date)).setText(new StringBuilder().append(jour_set).append(" / ").append(mois_set + 1).append(" / ").append(annee_set).append(" "));

        // Autres instructions à faire quand une date est selectionnée - mettre en relation avec recherche dans appli (query)
    }

}
