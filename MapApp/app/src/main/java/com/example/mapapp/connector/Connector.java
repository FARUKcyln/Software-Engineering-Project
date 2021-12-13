package com.example.mapapp.connector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.io.IOException;
import java.net.HttpURLConnection;

public class Connector {
    String FeedHistoryURL = "https://artemisbalanceapi.herokuapp.com/api/feed/get_feed";
    String RegisterURL = "https://artemisbalanceapi.herokuapp.com/api/user/register";
    String LoginURL = "https://artemisbalanceapi.herokuapp.com/api/user/login";
    String GetUserURL = "https://artemisbalanceapi.herokuapp.com/api/user/get_user";
    String UpdateUserURL = "https://artemisbalanceapi.herokuapp.com/api/user/update_user";
    String PostFeedURL = "https://artemisbalanceapi.herokuapp.com/api/feed/post_feed";

    public RegisterResponse register(Register register) throws IOException {

        URL url = new URL(RegisterURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json; utf-8");

        con.setDoOutput(true);
        String jsonInputString = new ObjectMapper().writeValueAsString(register);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonInputString);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        Gson g = new Gson();
        return g.fromJson(response.toString(), RegisterResponse.class);
    }

    public LoginResponse login(Login login) throws IOException {

        URL url = new URL(LoginURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true);
        String jsonInputString = new ObjectMapper().writeValueAsString(login);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonInputString);
        wr.flush();
        wr.close();
        BufferedReader in;
        if (con.getResponseCode() == 400){
            in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }else{
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        }



        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setResponseCode(con.getResponseCode());
        loginResponse.setJwt(response.toString());
        return loginResponse;
    }
}
