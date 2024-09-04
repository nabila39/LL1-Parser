package com.example.ll1parser;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        try {
            stage.setTitle("FileChooser");
            FileChooser fil_chooser = new FileChooser();
            TextArea code = new TextArea("");
            code.setEditable(false);
            code.setMinHeight(300);
            Font font = Font.font("Monospaced");
            code.setFont(font);

            Label label = new Label("Console");
            TextArea consol = new TextArea("");
            consol.setEditable(false);
            consol.setMaxHeight(20);
            Button button = new Button("Load your file");
            Button run = new Button("Run");
            button.setOnAction(n -> {
                        File file = fil_chooser.showOpenDialog(stage);
                        if (file != null) {
                            try {
                                StringBuilder content = new StringBuilder();
                                BufferedReader reader = new BufferedReader(new FileReader(file));
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    content.append(line).append("\n");
                                }
                                reader.close();
                                code.setText(content.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            run.setOnAction(x -> {
                LL1Scanner scan = new LL1Scanner();
                LL1Parser parser = new LL1Parser();
                List<Tokens> tokens = new ArrayList<>();
                try {
                    tokens = scan.getTokens(code.getText());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                try {
                    String soso = parser.parse(tokens);

                    consol.setText(soso);


                } catch (Exception e) {
                    consol.setText(e.getMessage());
                }
            });
            VBox vbox = new VBox(15, code, button, run, label, consol);
            vbox.setAlignment(Pos.TOP_CENTER);
            Scene scene = new Scene(vbox, 800, 490);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}