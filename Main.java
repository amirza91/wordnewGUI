package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main extends Application {
    Button button;
    Label label;

    @Override
    public void start(Stage primaryStage) throws Exception{

        button = new Button();
        label = new Label("GO");
        button.setText("START ");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    wordOccurrences();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        });

        VBox vbox = new VBox();
        vbox.getChildren().add(button);
        vbox.getChildren().add(label);

        Scene scene = new Scene(vbox, 800,800);




        primaryStage.setTitle("Word Occurrences ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void wordOccurrences() throws IOException {
        BufferedReader inputs = new BufferedReader(new FileReader("input.txt"));
        HashMap<String, Integer> words = new HashMap<>();
        ArrayList<String> order = new ArrayList<>();
        String line = "";
        while((line = inputs.readLine())!= null){
//            line = line.replaceAll("(\\.|,|;|:|\\?|\\!|—)", "");
            line = line.replaceAll("([^A-Za-z ]+)", "");
            line = line.replaceAll("( )+", " ");
            line = line.toLowerCase(Locale.ROOT);
            String[] lineWords = line.split(" ");
            for(String word: lineWords){
                if(words.containsKey(word)){
                    words.put(word, words.get(word) + 1);

                }
                else
                    words.put(word, 1);

            }
        }
        List<Map.Entry<String, Integer> >
                list = new LinkedList<>(words.entrySet());

        // Sort the list
        Collections.sort(list, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));
        String message = "";
        int count = 0;
        for (Map.Entry<String, Integer> map : list
             ) {
            message += map.getKey()+"=" +map.getValue();
            count++;
            if(count == 10){
                message += "\n";
                count = 0;
            }


        }
        System.out.println(list);

        label.setText(message);


    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
