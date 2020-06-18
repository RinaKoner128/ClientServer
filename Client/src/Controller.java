import ClientServers.ClServ;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public ImageView im;
    public TextField fsity;
    public Label name;
    public Label temp;
    public String sity;
    public String temperature;
    public String response;
    public String request;
    public String IP;
    public AnchorPane ind;
    public TextField insity;
    public TextField intemp;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        im.setImage(new Image("image.png"));
        getIP();
    }

    public void clOk(ActionEvent actionEvent) {
        sity = fsity.getText();
        inits();
        name.setText(request);
        temp.setText(response);
    }

    public void init() {

        try (ClServ module = new ClServ(IP, 2654)) {
            System.out.println("Connected to server");
            module.writeLine("?");
            request = sity;
            response = temperature;
            module.writeLine(request);
            module.writeLine(response);
            request = module.readerLine();
            response = module.readerLine();
            System.out.println("" + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inits() {

        try (ClServ module = new ClServ(IP, 2654)) {
            System.out.println("Connected to server");
            module.writeLine("#");
            request = sity;
            module.writeLine(request);
            request = module.readerLine();
            response = module.readerLine();
            System.out.println("" + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getIP() {
        InetAddress myIP = null;
        try {
            myIP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println(" ошибка доступа ->" + e);
        }
        IP = myIP.getHostAddress();
        return IP;
    }

    public void clNow(ActionEvent actionEvent) {
        ind.setVisible(true);
    }

    public void newOk(ActionEvent actionEvent) {
        sity = insity.getText();
        temperature = intemp.getText();
        init();
        name.setText(request);
        temp.setText(response);
        ind.setVisible(false);
    }
}
