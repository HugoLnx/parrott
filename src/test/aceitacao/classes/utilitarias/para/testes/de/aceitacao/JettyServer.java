package classes.utilitarias.para.testes.de.aceitacao;

import java.net.BindException;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;


public class JettyServer {

	static final int PORT = 8085;
	private Server server;
	
	public interface TesteAceitacao {
	    static final JettyServer SERVER = new JettyServer(); 
	}

	private JettyServer() {
		server = new Server();
		
	    Connector connector = new SelectChannelConnector();
	    connector.setPort(PORT);
	    connector.setHost("127.0.0.1");
	    server.addConnector(connector);
	
	    WebAppContext wac = new WebAppContext();
	    wac.setContextPath("/");
	    
	    wac.setWar("./build/pagpag.test.war");
	    server.setHandler(wac);
	    server.setStopAtShutdown(true);
	
	    try {
	    	server.start();
	    } catch (BindException e){
	    	System.out.println("Porta " + PORT + " já está ocupada");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String urlFor(String path) {
		return "http://localhost:" + PORT + "/" + path;
	}
}
