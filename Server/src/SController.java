import ClientServers.ClServ;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.*;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SController implements Initializable {
    public String request;
    public String respons;
    private ObservableList<String> sity;
    private ObservableList<String> temperature;
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
                            respons = module.readerLine() + "°";
                            if(sity != null){
                            for (int i = 0; i < sity.size(); i++) {
                                if (request == sity.get(i)) {
                                    request = sity.get(i);
                                    respons = temperature.get(i);

                                    module.writeLine(request);
                                    module.writeLine(respons);
                                    System.out.println("" + request);
                                    System.out.println("" + respons);
                                    res = new String(request + respons);
                                    System.out.println(res);
                                }
                                else {
                                    {
                                        String str = request;
                                        if (str != null) {
                                            sity.add(str);
                                        }
                                    }
                                    {
                                        String str = respons;
                                        if (str != null) {
                                            temperature.add(str);
                                        }
                                    }
                                    System.out.println("" + sity);
                                    module.writeLine(request);
                                    module.writeLine(respons);
                                    System.out.println("" + request);
                                    System.out.println("" + respons);
                                    res = new String(request + respons);
                                    System.out.println(res);
                                }
                            }
                        }
                            else { {
                                String str = request;
                                if (str != null) {
                                    sity.add(str);
                                }
                            }
                                {
                                    String str = respons;
                                    if (str != null) {
                                        temperature.add(str);
                                    }
                                }
                                System.out.println("" + sity);
                                module.writeLine(request);
                                module.writeLine(respons);
                                System.out.println("" + request);
                                System.out.println("" + respons);
                                res = new String(request + respons);
                                System.out.println(res);}
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
