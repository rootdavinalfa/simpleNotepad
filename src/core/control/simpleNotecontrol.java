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


import core.command.copyTF_find;
import javafx.application.Platform;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.awt.Desktop;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class simpleNotecontrol {
    copyTF_find cpy = new copyTF_find();
    @FXML private TextArea main_editor;
    @FXML private TextField simple_find_textfield;
    //Status bar
    @FXML private Label statusbar_size;
    @FXML private Label statusbar_status;
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
            fileChooser.getExtensionFilters().clear();
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
        fileChooser.getExtensionFilters().clear();
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
                statusbar_status.setText("Load file: "+filename);
                records.add(line);

            }
            reader.close();
            statusbar_status.setText("READY");
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
    @FXML private void handle_action_find(ActionEvent event){
        try{
            Parent findwin = FXMLLoader.load(getClass().getResource("/core/ui/find.fxml"));
            Scene scene = new Scene(findwin);
            Stage findwins = new Stage();
            findwins.setTitle("Find on file");
            findwins.setScene(scene);
            findwins.show();


        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
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



    //GETTING DATA FROM main_editor
    public List<String> getMainData(){
        List<String> parts  = Arrays.asList(main_editor.getText().split(""));
        System.out.println(parts);
        return parts;
    }


    private void finding(){
        List<String> parts  = Arrays.asList(main_editor.getText().split(""));
        List<String> parts_find = Arrays.asList(cpy.getNeed2find().split(""));
        //array info[charnum][data_word]
        int counter_word = main_editor.getLength();
        int counter_info =0;
        int counter_data = 0;
        //String info[][]=new String[counter_word][counter_word];

        ArrayList<String> index = new ArrayList<String>();
        ArrayList<String> data = new ArrayList<String>();
        Object[] objparts = parts.toArray();
        Object[] objparts_find = parts_find.toArray();
        String[] strparts = Arrays.copyOf(objparts,objparts.length,String[].class);
        String[] strparts_find = Arrays.copyOf(objparts_find,objparts_find.length,String[].class);
        //Just For check
        int checked =0;
        int checked_find =0;
        int lengthFind = strparts_find.length-1;
        int lengthINdex = strparts.length-1;
        System.out.println("LENGTH: "+lengthFind);
        for(int i=0;i<strparts.length;i++){
            for(int j=checked;j<strparts_find.length;j++){

                if(strparts[i].equals(strparts_find[j])){
                    System.out.println("INDEX SIZE:"+index.size());
                    //System.out.println(i+"///STRPARTS:"+strparts[i]+"STRFIND:"+strparts_find[i]+"INDEX:"+index.size()+"LENGTH:"+strparts_find.length);
                    if(strparts[i].equals(strparts_find[checked_find])){
                        int akai = i+1;
                        int kami = checked_find+1;
                        if(index.size()<strparts_find.length-1){
                            if(strparts[akai].equals(strparts_find[kami])){

                                System.out.println(i+" ATRY:"+strparts[i]+"CHECK:"+strparts[akai]);
                                System.out.println(i+" BTRY:"+strparts[i]+"CHECK:"+strparts_find[kami]);
                                System.out.println("FOUND!!"+strparts[i]);
                                index.add(String.valueOf(i));
                                data.add(strparts[i]);
                                checked++;
                                checked_find++;

                            }
                        }
                        else if(index.size()==lengthFind){
                            if(strparts[i].equals(strparts_find[checked_find])){
                                //System.out.println(i+" ATRYC:"+strparts[i]+"CHECK:"+strparts[i]);
                                //System.out.println(i+" BTRYC:"+strparts[i]+"CHECK:"+strparts_find[i]);
                                //System.out.println("FOUND!!"+strparts[i]);
                                index.add(String.valueOf(i));
                                data.add(strparts[i]);
                                checked++;
                                checked_find++;
                            }

                        }
                        /*
                        else if(strparts[i].equals(strparts_find[checked_find])&&index.size() == strparts_find.length){
                        }*/
                    }
                    /*
                    else if(strparts[i].equals(strparts_find[checked_find])&&index.size() == lengthFind){
                        System.out.println(i+" ATRYC:"+strparts[i]+"CHECK:"+strparts[i]);
                        System.out.println(i+" BTRYC:"+strparts[i]+"CHECK:"+strparts_find[i]);
                        System.out.println("FOUND!!");
                        index.add(String.valueOf(i));
                        data.add(strparts[i]);
                        checked++;
                        checked_find++;
                    }*/
                }

            }
            //System.out.println(strparts[i]);
        }
        if(index.isEmpty()&& data.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Text Not Found");
            alert.setHeaderText("NOT FOUND");
            alert.setContentText("It's look like you wanted text is not found in this file.");

            alert.showAndWait();
        }

        for(int i=0;i<index.size();i++){
            System.out.println("found "+index.get(i)+"DATA: "+data.get(i));
        }
        if(!index.isEmpty()&&!data.isEmpty()){
            int last = index.size();
            main_editor.selectRange(Integer.valueOf(index.get(0)),Integer.valueOf(index.get(last-1))+1);
        }
        //parts.clear();
        //parts_find.clear();
        index.clear();
        data.clear();

    }
    @FXML private void handle_find_button(){
        String text = simple_find_textfield.getText();
        cpy.setNeed2find(text);
        finding();
    }
}
