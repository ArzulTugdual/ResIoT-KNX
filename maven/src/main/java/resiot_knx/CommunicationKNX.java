package resiot_knx;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.knxnetip.KNXnetIPConnection;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.medium.TPSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;

public class CommunicationKNX {
	
	/*Attributes*/
	private final InetSocketAddress local;
	private final InetSocketAddress server;
	private Chenillard c;
	long v;
	
	/*Constructeur */
	public CommunicationKNX(String destAdd) {
		/**
		 * Local endpoint, The local socket address is important for
		 * multi-homed clients (several network interfaces), or if the default route is not useful.
		 */
		this.local = new InetSocketAddress(0);
		/**
		 * Specifies the KNXnet/IP server to access the KNX network, insert your server's actual host name or IP address,
		 * e.g., "192.168.1.20". The default port is where most servers listen for new connection requests.
		 */
		this.server = new InetSocketAddress(destAdd,
				KNXnetIPConnection.DEFAULT_PORT);
		
		System.out.println("Establish a tunneling connection to the KNXnet/IP server " + server);
		
		try (KNXNetworkLinkIP knxLink = KNXNetworkLinkIP.newTunnelingLink(local, server, false, new TPSettings())) {

			System.out.println("Connection established to server " + knxLink.getName());
			
			ProcessCommunicator pc = new ProcessCommunicatorImpl(knxLink); //créé un ProcessCommunicator

			this.v = 1000;
			this.c = new Chenillard(pc, v);
			new ListenerKNX(pc, c);
			pc.close();
			knxLink.close();
		}
		catch (Exception e) {
			// KNXException: all Calimero-specific checked exceptions are subtypes of KNXException

			// InterruptedException: longer tasks that might block are interruptible, e.g., connection procedures. In
			// such case, an instance of InterruptedException is thrown.
			// If a task got interrupted, Calimero will clean up its internal state and resources accordingly.
			// Any deviation of such behavior, e.g., where not feasible, is documented in the Calimero API.

			System.out.println("Error creating KNXnet/IP tunneling link: " + e);
		}

	}

//	/**
//	 * Local endpoint, The local socket address is important for
//	 * multi-homed clients (several network interfaces), or if the default route is not useful.
//	 */
//	private static final InetSocketAddress local = new InetSocketAddress(0);
//
//	/**
//	 * Specifies the KNXnet/IP server to access the KNX network, insert your server's actual host name or IP address,
//	 * e.g., "192.168.1.20". The default port is where most servers listen for new connection requests.
//	 */
//	
//	final static String destAdd = "192.168.1.201";
//	private static final InetSocketAddress server = new InetSocketAddress(destAdd,
//			KNXnetIPConnection.DEFAULT_PORT);

//	public static void main(final String[] args) {
//		System.out.println("Establish a tunneling connection to the KNXnet/IP server " + server);
//
//		// KNXNetworkLink is the base interface of a Calimero link to a KNX network. Here, we create an IP-based link,
//		// which supports NAT (Network Address Translation) if required.
//		// We also indicate that the KNX installation uses twisted-pair (TP1) medium.
//		try (KNXNetworkLinkIP knxLink = KNXNetworkLinkIP.newTunnelingLink(local, server, false, new TPSettings())) {
//
//			System.out.println("Connection established to server " + knxLink.getName());
//			
//			ProcessCommunicator pc = new ProcessCommunicatorImpl(knxLink); //créé un ProcessCommunicator
//
//			long v = 1000;
//			Chenillard c = new Chenillard(pc, v);
//			/*
//			 * c.demarrer();
//			 * TimeUnit.SECONDS.sleep(10);
//			 * c.eteindre();
//			
//			
//			//lire
//			boolean led1 = pc.readBool(new GroupAddress("0/1/1"));
//			boolean led2 = pc.readBool(new GroupAddress("0/1/2"));
//			boolean led3 = pc.readBool(new GroupAddress("0/1/3"));
//			boolean led4 = pc.readBool(new GroupAddress("0/1/4"));
//			
//			System.out.println("valeur de la lampe 1: "+led1);
//			System.out.println("valeur de la lampe 2: "+led2);
//			System.out.println("valeur de la lampe 3: "+led3);
//			System.out.println("valeur de la lampe 4: "+led4);
//			
//			//écrire
//			//pc.write(new GroupAddress("0/0/2"), true);
//
//			boolean bouton1 = pc.readBool(new GroupAddress("1/0/1"));
//			System.out.println("valeur du bouton 1: "+bouton1);
//			*/
//			new ListenerKNX(pc, c);
//			TimeUnit.SECONDS.sleep(100);
//			pc.close();
//			knxLink.close();
//		}
//		catch (Exception e) {
//			// KNXException: all Calimero-specific checked exceptions are subtypes of KNXException
//
//			// InterruptedException: longer tasks that might block are interruptible, e.g., connection procedures. In
//			// such case, an instance of InterruptedException is thrown.
//			// If a task got interrupted, Calimero will clean up its internal state and resources accordingly.
//			// Any deviation of such behavior, e.g., where not feasible, is documented in the Calimero API.
//
//			System.out.println("Error creating KNXnet/IP tunneling link: " + e);
//		}
//	}
}