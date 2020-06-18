// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.sps.data.Comment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query("Comment").addSort("time", SortDirection.ASCENDING);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    List<Comment> comments = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
      Date time = (Date) entity.getProperty("time");
      String username = (String) entity.getProperty("username");
      String content = (String) entity.getProperty("comment");

      Comment comment = new Comment(time, username, content);
      comments.add(comment);
    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String rawString = gson.toJson(comments);
    String finalString = rawString.replace("[\n", "").replace("]", "").replace(",", "").replace("{\n", "").replace("}", "");

    response.setContentType("text/html;");
    response.getWriter().println(finalString);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Date time = new Date();
    String username = getParameter(request, "username", "") + ": ";
    if (username.equals(": ")){
        username = "N/A: ";
    }
    String comment = getParameter(request, "comment", "");
    if (comment.equals("")){ //TO-DO: Tell user that empty comments are not allowed.
        return;
    }

    Entity comEntity = new Entity("Comment");
    comEntity.setProperty("time", time);
    comEntity.setProperty("username", username);
    comEntity.setProperty("comment", comment);
    
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(comEntity);

    response.sendRedirect("/blog.html");
  }

  private String convertToJsonUsingGson(ArrayList aList) {
    Gson gson = new Gson();
    String json = gson.toJson(aList);
    return json;
  }

  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}
