package ru.pizza;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PizzaServer {
    // ингридиенты
    public static final String A = "А";
    public static final String B = "Б";
    public static final String C = "В";
    public static final String D = "Г";
    public static final String E = "Д";
    public static final String F = "Е";
    public static final String J = "Ж";
    public static final String H = "З";
    public static final String I = "И";
    public static final String G = "К";
    public static final String K = "Л";

    public static void main(String[] args) throws Exception {
        try {
            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(8765), 0);

            server.createContext("/makepizza", new EchoHandler());

            server.setExecutor(null);
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(PizzaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static class EchoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange he) throws IOException {
            System.out.println("Serving the request");

            // Serve for POST requests only
            if (he.getRequestMethod().equalsIgnoreCase("POST")) {

                try {
                    InputStream is = he.getRequestBody();
                    JSONParser jsonParser = new JSONParser();
                    JSONObject request = (JSONObject) jsonParser.parse(
                            new InputStreamReader(is, "UTF-8"));

                    String response;
                    ArrayList array = (ArrayList) request.get("ingredients");

                        if (array.contains(B) && array.contains(C) || array.contains(B) && array.contains(D)) {
                            response = "{\"result\": false, \"message\": \"Ingredients are incompatible, choose other ingredients!\"}";
                        } else response = "{\"result\": true, \"message\": \"Pizza is done!\"}";

                    he.sendResponseHeaders(200, response.length());
                    OutputStream os = he.getResponseBody();
                    os.write(response.getBytes());
                    os.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
