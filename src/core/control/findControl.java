/*
 *
 *  * Copyright 2018,Davin Alfarizky Putra Basudewa
 *  * Davin OpenSource Project
 *  * 2018(c) DVNLABS.ml (DVNLABS)
 *  * ============================
 *  * mail : moshi_moshidavin@dvnlabs.ml / dbasudewa@gmail.com / rootdavin@yahoo.co.jp
 *  * ============================
 *  * Please remind this source only for educational purposes only.
 *  * Any breakthrough of rule on http://dvnlabs.ml/licenses.html may violation of crime
 *  * action on Indonesia or other respective countries.
 *
 */

package core.control;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import core.command.*;
public class findControl {
    copyTF_find cpy = new copyTF_find();
    simpleNotecontrol snc = new simpleNotecontrol();
    @FXML private TextField find_textfield;
    @FXML private void handle_find_action_find(){
        String finder = find_textfield.getText();

        //cpy.setNeed2find(finder);
        //snc.finding();
        //System.out.println(snc.getMainData());

    }
}
