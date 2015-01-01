package controller;

import entity.Event;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import session.EventFacade;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by tomas on 30-12-14.
 */
@WebServlet(name = "SearchServlet", loadOnStartup = 1, urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

    @EJB
    public EventFacade eventFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = (String)request.getParameter("type");
        String query = (String)request.getParameter("q");

        switch (type) {
            case "events":{
                searchEvents(request, response, query);
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
}
