package resiot_knx;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.eclipse.jetty.client.HttpClient;
//import org.eclipse.jetty.client.api.ContentResponse;

import javax.servlet.annotation.WebServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import tuwien.auto.calimero.process.ProcessCommunicator;












public class JettyServer {
	
	
	static CommunicationKNX comm;
	static String destAdd;
	
	
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        
        destAdd = "192.168.1.201";
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(HelloServlet.class, "/hello");//Set the servlet to run, page default.
        handler.addServletWithMapping(StartServlet.class, "/start");
        handler.addServletWithMapping(StopServlet.class, "/stop");
        handler.addServletWithMapping(SpeedUpServlet.class, "/speedup");
        handler.addServletWithMapping(SpeedDownServlet.class, "/speeddown");
        handler.addServletWithMapping(StopServerServlet.class, "/stopserver");
        
        comm = new CommunicationKNX(destAdd);
        
        server.setHandler(handler);    
        server.start();
        server.join();
    }
    
    

    @SuppressWarnings("serial")
    public static class HelloServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Hello SimpleServlet</h1>");
            
        }
    }
    
    @SuppressWarnings("serial")
    public static class StartServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Demarrage2 du chenillard</h1>");
            System.out.println("Demarrage chenillard");
            Chenillard c = JettyServer.comm.getC();
            c.demarrer();

    }
    }
    

    
    
    @SuppressWarnings("serial")
    public static class StopServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Arr�t du chenillard</h1>");
            System.out.println("Arret chenillard");
            Chenillard c = JettyServer.comm.getC();
            c.eteindre();
        }
    }
    
    
    @SuppressWarnings("serial")
    public static class SpeedUpServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Acc�l�ration defilement du chenillard</h1>");
            System.out.println("Acceleration chenillard");
            Chenillard c = JettyServer.comm.getC();
            c.accelere();
        }
    }
    
    @SuppressWarnings("serial")
    public static class SpeedDownServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Ralentissement du chenillard</h1>");
            System.out.println("Ralentissement chenillard");
            Chenillard c = JettyServer.comm.getC();
            c.ralenti();
        }
    }
    
    
    @SuppressWarnings("serial")
    public static class StopServerServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Arret Serveur</h1>");
            //server.stop();
        }
    }
    
}

/*public class MyPostServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Get POST parameters
        String val = req.getParameter("foo");
        // Do something with 'foo'
        String result = backend.setValue("foo", val);
        // Write a response
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().printf("foo = %s (result: %s)%n",val,result);
    }*/
