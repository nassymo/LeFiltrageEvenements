package com.monapplication.testlayout;

import android.view.View;
import android.widget.AdapterView;

/**
  Created by Nassymo on 22/03/2015.
*/

public class Element_spinner extends MainActivity implements AdapterView.OnItemSelectedListener {

//cette classe va traiter l'element choisi dans le spinner. Une méthode par statut : selectionné ou non.

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id_elem) {
       //dans cette classe, on va chosir de faire ce qu'on veut, quand un element est choisi par l'utilisateur (dès que selection faite par utilisateur, notre spinner reçoit comme "valeur actuelle" l'element choisi.

        //si on veut afficher le choix fait dans le spinner (à récupérer ailleurs i.e dans la query)
            String selection = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        // ce qu'on va faire quand rien n'est selectionné
        // on fait rien

    }
}
