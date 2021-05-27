package resiot_knx;

import tuwien.auto.calimero.DetachEvent;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessEvent;
import tuwien.auto.calimero.process.ProcessListener;

public class ListenerKNX {
	
	private Chenillard c; //classe chenillard
	private boolean running; //chenillard est en cours
	private long t0;
	private String value;
	
	public ListenerKNX(ProcessCommunicator pc, Chenillard chenille) {
		
		this.c = chenille;
		this.running = false;
		this.t0 = System.currentTimeMillis();
		
		pc.addProcessListener(new ProcessListener() {
			
			@Override
			public void groupWrite(ProcessEvent e) {
				
				value = e.toString();
				
				// bouton 1: allumer/Ã©teindre le chenillard 
				if(e.getDestination().toString().equals("1/0/1")) {
					long t1 = System.currentTimeMillis();
					System.out.println("t1:"+t1);
					System.out.println("t0 "+t0);
					if((t1-t0) > 1000) {
						if(!running) {
							lancerChenillard();
							running = true;
							System.out.println("bouton 1 - démarrage chenillard");
						}
						else {
							arreterChenillard();
							running = false;
							System.out.println("bouton 1 - Arrêt chenillard");
						}
					}
					t0=t1;
				}
				else if(e.getDestination().toString().equals("1/0/2")) {
					if(running) {
						accelererChenillard();
					}
					System.out.println("bouton 2 - accelerer");
				}
				else if(e.getDestination().toString().equals("1/0/3")) {
					if(running) {
						ralentirChenillard();
					}
					System.out.println("bouton 3 - ralentir " );
				}
				else if(e.getDestination().toString().equals("1/0/4")) {
					randomChenillard();
					System.out.println("bouton 4 - random");
				}
			}
			
			@Override
			public void groupReadResponse(ProcessEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void groupReadRequest(ProcessEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void detached(DetachEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e);
			}
		});
	}
	
	void lancerChenillard() {
		this.c.demarrer();
	}
	
	private void arreterChenillard() {
		this.c.eteindre();
	}
	
	void accelererChenillard() {
		this.c.accelere();
	}
	
	void ralentirChenillard() {
		this.c.ralenti();
	}
	
	void randomChenillard() {
		this.c.random();
	}
}
