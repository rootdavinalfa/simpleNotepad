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
 *  ==============================
 *  Main.java
 *
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    String title_ui = "untitled | simpleNotepad BETA CANARY BUILDS";
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("core/ui/simpleNotepad.fxml"));
        primaryStage.setTitle(getTitle_ui());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void setTitle(String titles){
        title_ui = titles;
    }
    private String getTitle_ui(){
        return title_ui;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
