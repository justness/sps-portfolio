package com.google.sps.servlets;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

@WebServlet("/blogData")
public class BlogSentimentServlet extends HttpServlet{

    String filePath = "../../src/main/java/com/google/sps/blogposts/060620.txt";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<String> processed = sentiment(filePath);
        String newBlog = "";
        for (String s:processed){
            if(newBlog.equals("")){
                newBlog = newBlog + s;
                continue;
            }
            newBlog = newBlog + "<br><br>" + s;
        }
        response.setContentType("text/html;");
        response.getWriter().println(newBlog);
    }

    public ArrayList<String> sentiment(String filePath) throws IOException {
        String fP = new File(".").getAbsolutePath() + "/" + filePath;
        BufferedReader reader = new BufferedReader(new FileReader(fP));
        String line;
        ArrayList<String> processed = new ArrayList<String>();

        while ((line = reader.readLine()) != null) {
            processed.add(line);
        }
        reader.close();

        LanguageServiceClient lS = LanguageServiceClient.create();
        for(int i = 0; i < processed.size(); i++){
            Document doc = Document.newBuilder().setContent(processed.get(i)).setType(Document.Type.PLAIN_TEXT).build();
            Sentiment sent = lS.analyzeSentiment(doc).getDocumentSentiment();
            float score = sent.getScore();
            int intScore = Math.round(score);
            System.out.println(score);
            System.out.println(intScore);
            String newS = processed.get(i);
            if (intScore == -1){
                newS = "<p1 style=\"color:red;\">" + processed.get(i) + "</p1>";
            }
            if (intScore == 1){
                newS = "<p1 style=\"color:green;\">" + processed.get(i) + "</p1>";
            }
            processed.set(i,newS);
        }
        lS.close();

        return processed;
    }
}