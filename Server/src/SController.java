import ClientServers.ClServ;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.*;
import java.net.ServerSocket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SController implements Initializable {
    public String request;
    public String respons;
    public Map<String, Integer> map = new HashMap();
    private String res;
    public String mark;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try (ServerSocket server = new ServerSocket(2654);) {
            System.out.println("Server started");

            while (true)
                try (ClServ module = new ClServ(server)) {
                    mark = module.readerLine();

                    synchronized (this) {
                        if (mark.equals("1")) {
                            module.writeLine(res);
                        } else {
                            if (mark.equals("#")) {
                                request = module.readerLine();
                                if (map.containsKey(request)) {
                                    respons = map.get(request).toString();
                                    module.writeLine(request);
                                    module.writeLine(respons+"°");
                                    System.out.println("" + request);
                                    System.out.println("" + respons);
                                    res = new String(request + respons);
                                    System.out.println(res);
                                } else {
                                    module.writeLine(request);
                                    module.writeLine("Нет данных");
                                }
                            } else {
                                if (mark.equals("?")) {
                                    request = module.readerLine();
                                    respons = module.readerLine();
                                    map.put(request, Integer.valueOf(respons));
                                    module.writeLine(request);
                                    module.writeLine(respons+"°");
                                    System.out.println(map);
                                    res = new String(request + respons);
                                    System.out.println(res);
                                }
                            }
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
