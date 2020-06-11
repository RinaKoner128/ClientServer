import ClientServers.ClServ;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public ImageView im;
    public TextField fsity;
    public Label name;
    public Label temp;
    public String sity;
    public String response;
    public String request;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        im.setImage(new Image("image.png"));
        name.setText(request);
        temp.setText(response);
    }

    public void clOk(ActionEvent actionEvent) {
        sity = fsity.getText();
        init();
        name.setText(request);
        temp.setText(response);
    }

    public void init() {

        try (ClServ module = new ClServ("127.0.0.1", 2654)) {
            System.out.println("Connected to server");
            request = sity;
            module.writeLine(request);
            request = module.readerLine();
            response = module.readerLine();
            System.out.println("" + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
