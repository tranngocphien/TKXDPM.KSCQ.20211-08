package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

/**
 * Class cung cap cac phuong thuc giup gui request len server va nhan du lieu tra ve
 * Date: 10/12/2021
 * @author PhienTran
 *
 */
public class API {
    /**
     * Thuoc tinh giup format ngay thang theo dinh dang
     */
    public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * Phuong thuc giup goi cac api dang GET
     * @param url: duong dan to server can request
     * @param token: doan ma ban can cung cap de xac thuc nguoi dung
     * @return response: phan hoi tu server (dang string)
     * @throws Exception
     */
    public static String get(String url, String token) throws Exception {
        HttpURLConnection conn = setupConnection(url, token, "GET");

        String responseString =  readRespone(conn);

        return responseString;
    }


    int var;
    /**
     * Phuong thuc giup goi cac api dang POST (thanh toan,...)
     * @param url: duong dan toi server can request
     * @param data: du lieu dua len server de xu ly (dang JSON)
     * @return response: phan hoi tu server(dang string)
     * @throws IOException
     */
    public static String post(String url, String data
                              //,String token
    ) throws IOException {
        allowMethods("PATCH");

        HttpURLConnection conn = setupConnection(url, "", "PATCH");


        Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        writer.write(data);
        writer.close();

        String response = readRespone(conn);
        return response;
    }
    /**
     * Phuong thuc doc du lieu tra ve tu server
     * @param conn: connection to server
     * @return response: phan hoi tra ve tu server
     * @throws IOException
     */
    private static String readRespone(HttpURLConnection conn) throws IOException {
        BufferedReader in;
        String inputLine;
        if (conn.getResponseCode() / 100 == 2) {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder respone = new StringBuilder(); // ising StringBuilder for the sake of memory and performance
        while ((inputLine = in.readLine()) != null){
            respone.append(inputLine + "\n");
            System.out.println(inputLine);

        }
        in.close();
        return respone.substring(0, respone.length() - 1).toString();
    }

    /**
     * Thiet lap connection toi server
     * @param url: duong dan toi server can request
     * @param token: doan ma ban can cung cap de xac thuc nguoi dung
     * @param method: giao thuc api
     * @return connection
     * @throws MalformedURLException
     * @throws IOException
     * @throws ProtocolException
     */
    private static HttpURLConnection setupConnection(String url, String token, String method)
            throws MalformedURLException, IOException, ProtocolException {
        URL line_api_url = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + token);
        return conn;
    }

    /**
     *
     * @param methods
     */
    private static void allowMethods(String... methods) {
        try {
            Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
            methodsField.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

            String[] oldMethods = (String[]) methodsField.get(null);
            Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
            methodsSet.addAll(Arrays.asList(methods));
            String[] newMethods = methodsSet.toArray(new String[0]);

            methodsField.set(null/* static field */, newMethods);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

}
