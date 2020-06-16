import ClientServers.ClServ;
import javafx.fxml.Initializable;

import java.io.*;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class SController implements Initializable {
    public String request;
    public String respons;
    private String res;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try (ServerSocket server = new ServerSocket(2654);) {
            System.out.println("Server started");

            while (true)
                try (ClServ module = new ClServ(server)) {
                    request = module.readerLine();
                    synchronized (this) {
                        if (request.equals("1")) {
                            module.writeLine(res);
                        } else {
                            System.out.println("" + request);
                            respons = (int) (Math.random() * 30 - 10) + "°";
                            module.writeLine(request);
                            module.writeLine(respons);
                            System.out.println("" + respons);
                            res = new String(request + respons);
                        }
                    }


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
