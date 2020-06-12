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

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  public ArrayList<String> coelho;

  public void init(){
      coelho = new ArrayList<String>();
      coelho.add("I can choose either to be a victim of the world or an adventurer in search of treasure.");
      coelho.add("Life is too short, or too long, for me to allow myself the luxury of living it so badly.");
      coelho.add("When we strive to become better than we are, everything around us becomes better, too.");
      coelho.add("At every moment of our lives, we all have one foot in a fairy tale and the other in the abyss.");
      coelho.add("And, when you can\'t go back, you have to worry only about the best way of moving forward.");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*fetch-walkthrough*/
    //response.setContentType("text/html;");
    //response.getWriter().println("<h1>Hello Ness!</h1>\n");
        /*JSON-walkthrough*/
    //response.setContentType("application/json;");
    //response.getWriter().println(convertToJsonUsingGson(coelho));
  }

  private String convertToJsonUsingGson(ArrayList coelho) {
    Gson gson = new Gson();
    String json = gson.toJson(coelho);
    return json;
  }
}
