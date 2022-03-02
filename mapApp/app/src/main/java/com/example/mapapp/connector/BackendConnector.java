package com.example.mapapp.connector;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


public class BackendConnector {
    static String FeedHistoryURL = "https://artemisbalance.herokuapp.com/api/feed/get_feed";
    static String RegisterURL = "https://artemisbalance.herokuapp.com/api/user/register";
    static String LoginURL = "https://artemisbalance.herokuapp.com/api/user/login";
    static String GetUserURL = "https://artemisbalance.herokuapp.com/api/user/get_user";
    static String UpdateUserURL = "https://artemisbalance.herokuapp.com/api/user/update_user";
    static String PostFeedURL = "https://artemisbalance.herokuapp.com/api/feed/post_feed";
    static String ContactURL = "https://artemisbalance.herokuapp.com/api/user/contact";
    static String PointsURL = "https://artemisbalance.herokuapp.com/api/user/get_points";



    public static LoginResponse login(Login login) throws IOException {
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

        LoginResponse loginResponse = new LoginResponse();
        if (con.getResponseCode()!=200){
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getErrorStream()));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            loginResponse.setStatus(false);
            loginResponse.setErrorText(response.toString());
            return loginResponse;
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        loginResponse.setJwt(response.toString());
        loginResponse.setStatus(true);

        return loginResponse;
    }

    public static RegisterResponse register(Register reg) throws IOException {
        URL url = new URL(RegisterURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true);
        String jsonInputString = new ObjectMapper().writeValueAsString(reg);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonInputString);
        wr.flush();
        wr.close();

        RegisterResponse registerResponse = new RegisterResponse();
        if (con.getResponseCode()!=200){
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getErrorStream()));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            registerResponse.setStatus(false);
            registerResponse.setErrorText(response.toString());
            return registerResponse;
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        Gson g = new Gson();
        registerResponse.setUser(g.fromJson(response.toString(), UserID.class));
        registerResponse.setStatus(true);

        return registerResponse;
    }

    public static ProfileResponse getUser (String jwt) throws IOException {
        URL url = new URL(GetUserURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("auth-token", jwt);

        ProfileResponse profileResponse = new ProfileResponse();
        if (con.getResponseCode()!=200){
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getErrorStream()));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            profileResponse.setStatus(false);
            profileResponse.setErrorText(response.toString());
            return profileResponse;
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        Gson g = new Gson();
        profileResponse.setProfile(g.fromJson(response.toString(), Profile.class));
        profileResponse.setStatus(true);

        return profileResponse;
    }

    public static UpdateUserResponse updateUser (String jwt, UpdateUser uUser) throws IOException {
        URL url = new URL(UpdateUserURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("auth-token", jwt);

        con.setDoOutput(true);
        String jsonInputString = new ObjectMapper().writeValueAsString(uUser);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonInputString);
        wr.flush();
        wr.close();

        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        if (con.getResponseCode()!=200){
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getErrorStream()));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            updateUserResponse.setStatus(false);
            updateUserResponse.setResultText(response.toString());
            return updateUserResponse;
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();


        updateUserResponse.setResultText(response.toString());
        updateUserResponse.setStatus(true);

        return updateUserResponse;
    }

    public static PostFeedResponse postFeed(String jwt, PostFeed pointInf) throws IOException {
        URL url = new URL(PostFeedURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("auth-token", jwt);

        con.setDoOutput(true);
        String jsonInputString = new ObjectMapper().writeValueAsString(pointInf);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonInputString);
        wr.flush();
        wr.close();

        PostFeedResponse postFeedResponse = new PostFeedResponse();
        if (con.getResponseCode()!=200){
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getErrorStream()));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            postFeedResponse.setStatus(false);
            postFeedResponse.setErrorText(response.toString());
            return postFeedResponse;
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        Gson g = new Gson();
        postFeedResponse.setFeed(g.fromJson(response.toString(), PostFeedResponse.FeedResponse.class));
        postFeedResponse.setStatus(true);

        return postFeedResponse;


    }

    public static GetFeedResponse getFeedHistory(String jwt) throws IOException {
        URL url = new URL(FeedHistoryURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("auth-token", jwt);

        GetFeedResponse getFeedResponse = new GetFeedResponse();
        if (con.getResponseCode()!=200){
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getErrorStream()));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            getFeedResponse.setStatus(false);
            getFeedResponse.setErrorText(response.toString());
            return getFeedResponse;
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();


        Type listType = new TypeToken<ArrayList<Feed>>(){}.getType();
        List<Feed> feedList = new Gson().fromJson(response.toString(), listType);
        getFeedResponse.setFeedList(feedList);
        getFeedResponse.setStatus(true);
        return getFeedResponse;


    }

    public static ContactResponse postContact(String jwt, PostContact postContact) throws IOException{

        URL url = new URL(ContactURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("auth-token", jwt);

        con.setDoOutput(true);
        String jsonInputString = new ObjectMapper().writeValueAsString(postContact);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonInputString);
        wr.flush();
        wr.close();

        ContactResponse contactResponse = new ContactResponse();
        if (con.getResponseCode()!=200){
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getErrorStream()));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            contactResponse.setStatus(false);
            contactResponse.setText(response.toString());
            return contactResponse;
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        contactResponse.setText(response.toString());
        contactResponse.setStatus(true);

        return contactResponse;
    }

    public static GetPointResponse getPoints(String jwt) throws IOException{

        URL url = new URL(PointsURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("auth-token", jwt);

        GetPointResponse getPointResponse = new GetPointResponse();
        if (con.getResponseCode()!=200){
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getErrorStream()));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            getPointResponse.setStatus(false);
            getPointResponse.setErrorText(response.toString());
            return getPointResponse;
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();


        Type listType = new TypeToken<ArrayList<Point>>(){}.getType();
        List<Point> pointList = new Gson().fromJson(response.toString(), listType);
        getPointResponse.setPointList(pointList);
        getPointResponse.setStatus(true);
        return getPointResponse;

    }
}
