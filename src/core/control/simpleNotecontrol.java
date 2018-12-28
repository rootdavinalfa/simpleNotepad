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


import javafx.application.Platform;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import java.awt.Desktop;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class simpleNotecontrol {

    @FXML private TextArea main_editor;

    //Status bar
    @FXML private Label statusbar_size;
    //toolbar
    private int sizeX = 12;
    //Menu item section
    @FXML private MenuItem file_open;
    @FXML private MenuItem file_save;
    @FXML private MenuItem file_exit;
    private Desktop desktop = Desktop.getDesktop();
    final FileChooser fileChooser = new FileChooser();
    Stage stage;
    //stage.setTitle("File Chooser Sample");
    @FXML private void action_file_save(){
        try {
            fileChooser.setTitle("Save As");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text File Format", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")

            );
            File file = fileChooser.showSaveDialog(stage);


            if (file != null) {
                PrintWriter savedText = new PrintWriter(file);
                BufferedWriter out = new BufferedWriter(savedText);
                out.write(main_editor.getText());
                out.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Save File Status");
                alert.setHeaderText("File Has Been Saved!");
                alert.setContentText("File : "+file.getName()+" Successfully save!");

                alert.showAndWait();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML private void action_file_open(){
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                                new FileChooser.ExtensionFilter("Text File Format", "*.txt"),
                                new FileChooser.ExtensionFilter("All Files", "*.*")

        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFile(file);
        }
    }
    private void openFile(File file) {
        try {

            main_editor.clear();
            //desktop.open(file);
            String path = file.getAbsolutePath();
            String filename = file.getName();
            String titles = filename + " | simpleNotepad BETA CANARY BUILDS";
            Stage primStage = (Stage) main_editor.getScene().getWindow();
            primStage.setTitle(titles);
            //String listString = Arrays.toString(copy_buffer_to_editor(path).toArray());
            Object[] objText = copy_buffer_to_editor(path).toArray();
            String[] strText = Arrays.copyOf(objText,objText.length,String[].class);
            //String liststring = String.join("<br>",copy_buffer_to_editor(path));
            //main_editor.set(liststring);
            for(int i=0;i<strText.length;i++){
                main_editor.appendText(strText[i]+"\n");
            }

            String file_size = getFileSize(file);
            statusbar_size.setText(file_size);

        } catch (Exception ex) {
            Logger.getLogger(
                    simpleNotecontrol.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }
    private static String getFileSize(File file) {
        System.out.println(file.length());
        if(file.length()<1024){
            return (double) file.length()+" Bytes / "+(double)file.length()/1024+" KB";
        }
        else if(file.length()>=1024 && file.length()<1024*1024){
            return (double) file.length()/(1024*1024)+" MB";
        }
        return null;
    }
    private List<String> copy_buffer_to_editor(String filename)
    {
        List<String> records = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);

            }
            reader.close();
            return records;
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }
    @FXML private void action_file_exit(){
        Platform.exit();
    }

    //Action item Section
    //TODO must have number and string parser sompiler.So number can sorted correctly
    //TODO make if statement,and fix some logic
    @FXML private void handle_action_sort_asc(){
        String tmp;
        String[] rows = main_editor.getText().split("\n");

        for(int i=0;i<rows.length;i++){
            for(int j=i+1;j<rows.length;j++){
                if(rows[i].compareTo(rows[j])>0){
                    tmp = rows[i];
                    rows[i] = rows[j];
                    rows[j] = tmp;
                }

            }
        }
        System.out.println("Sorted:");
        main_editor.appendText("\nSorted result:\n");
        for(int i=0;i<=rows.length-1;i++){
            System.out.println(i+1+"."+rows[i]);
            main_editor.appendText(rows[i]+",");
        }
    }
    @FXML private void handle_action_sort_dsc(){
        String tmp;
        String[] rows = main_editor.getText().split("\n");

        for(int i=0;i<rows.length;i++){
            for(int j=i+1;j<rows.length;j++){
                if(rows[i].compareTo(rows[j])<rows.length){
                    tmp = rows[i];
                    rows[i] = rows[j];
                    rows[j] = tmp;
                }

            }
        }
        System.out.println("Sorted:");
        main_editor.appendText("\nSorted result:\n");
        for(int i=0;i<=rows.length-1;i++){
            System.out.println(i+1+"."+rows[i]);
            main_editor.appendText(rows[i]+",");
        }
    }
    //
    @FXML private void action_help_about(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About this program");
        alert.setHeaderText("Copyright(c) 2018,DVNLABS.ml");
        alert.setContentText("This program coded by Davin,with some material in internet.");
        TextArea textArea = new TextArea("Davin OpenSource Project\n" +
                "2018(c) DVNLABS.ml (DVNLABS)\n" +
                "============================\n" +
                "mail : moshi_moshidavin@dvnlabs.ml / dbasudewa@gmail.com / rootdavin@yahoo.co.jp\n" +
                "============================\n" +
                "Please remind this source only for educational purposes only.\n" +
                "Any breakthrough of rule on http://dvnlabs.ml/licenses.html may violation of crime\n" +
                "action on Indonesia or other respective countries.");
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);

        expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }
    //Toolbar
    @FXML private void toolbar_action_sizeup(){
        main_editor.setStyle("-fx-font-size:"+(sizeX+1));
        sizeX++;
    }
    @FXML private void toolbar_action_sizedown(){
        main_editor.setStyle("-fx-font-size:"+(sizeX-1));
        sizeX--;
    }

}
