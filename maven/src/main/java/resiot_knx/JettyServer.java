package resiot_knx;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;










public class JettyServer {
	
	public static void main(String[] args) throws Exception {
	
	Server server = new Server(8080);
	
	ServletHandler handler = new ServletHandler();
	handler.addServletWithMapping(BlockingServlet.class, "/ping");
	
	server.setHandler(handler);
	
	server.start();
	server.join();
	
	
	}
	
	
	/*
    public static void main(String[] args) throws Exception {
        
        Server server = new Server(8680);       
         
        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);
                 
        servletHandler.addServletWithMapping((Class<? extends Servlet>) HelloServlet.class, "/");
         
        server.start();
        server.join();
 
    }
     
    public static class HelloServlet extends HttpServlet 
    {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>New Hello Simple Servlet</h1>"); 
               } 
        }
    
    */

	/*
	
	private static Server server = new Server();




	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(8090);
		server.setConnectors(new Connector[] {connector});

		ServletHandler servletHandler = new ServletHandler();
		servletHandler.addServletWithMapping((Class<? extends Servlet>) BlockingServlet.class, "/status");
		server.start();
	}

	*/
		/*
    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        //handler.addFilterWithMapping(HelloServlet.class,"/hello", 0);
        handler.addServletWithMapping(HelloServlet.class, "/hello");//Set the servlet to run.
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

		 */


	
}
