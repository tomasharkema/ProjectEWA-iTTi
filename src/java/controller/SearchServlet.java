package controller;

import entity.Event;
import entity.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import searching.Search;
import session.EventFacade;
import utils.ListUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by tomas on 30-12-14.
 */
@WebServlet(name = "SearchServlet", loadOnStartup = 1, urlPatterns = {"/search", "/wild"})
public class SearchServlet extends HttpServlet {

    @EJB
    public EventFacade eventFacade;

    @EJB
    public Search search;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = (String)request.getParameter("type");
        String query = (String)request.getParameter("q");

        switch (type) {
            case "events":{
                searchEvents(request, response, query);
                break;
            }
            case "wild":{
                searchWild(request, response, query);
            }
        }
    }

    private void searchEvents(HttpServletRequest request, HttpServletResponse response, String q) throws IOException {
        List<Event> eventsList = eventFacade.findByQuery(q);
        PrintWriter out = response.getWriter();
        response.setContentType("application/javascript");
        JSONArray array = new JSONArray();

        for (Event event : eventsList) {
            array.add(event.toJSONObject());
        }

        JSONObject obj = new JSONObject();
        obj.put("events", array);

        try {
            out.println(obj.toJSONString());
        } finally {
            out.close();
        }
    }

    private void searchWild(HttpServletRequest request, HttpServletResponse response, String q) throws IOException {
        PrintWriter out = response.getWriter();
        Search.WildSearchResult wildSearchResult = search.wildSearch(q);

        JSONObject ret = new JSONObject();

        JSONObject usersObj = new JSONObject();
        JSONArray users = wildSearchResult.getUsers().stream()
                .map(User::getJSONObject)
                .reduce(new JSONArray(), (arr, obj) -> {
                    System.out.println("OBJ: " + obj.toJSONString());
                    arr.add(obj);
                    return arr;
                }, (a, b) -> null);
        usersObj.put("users", users);
        usersObj.put("count", users.size());

        ret.put("users", usersObj);

        try {
            out.println(ret.toJSONString());
        } finally {
            out.close();
        }
    }

}
