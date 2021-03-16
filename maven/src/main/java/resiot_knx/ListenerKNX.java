package resiot_knx;

import tuwien.auto.calimero.DetachEvent;
import tuwien.auto.calimero.KNXException;
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
				
				// bouton 1: allumer/éteindre le chenillard 
				if(e.getDestination().toString().equals("1/0/1")) {
					long t1 = System.currentTimeMillis();
					System.out.println("t1:"+t1);
					System.out.println("t0 "+t0);
					if((t1-t0) > 1000) {
						if(!running) {
							lancerChenillard();
							running = true;
						}
						else {
							arreterChenillard();
							running = false;
						}
					}
					t0=t1;
				}
				else if(e.getDestination().toString().equals("1/0/2")) {
					System.out.println("bouton 2" + value);
				}
				else if(e.getDestination().toString().equals("1/0/3")) {
					System.out.println("bouton 3"  + value);
				}
				else if(e.getDestination().toString().equals("1/0/4")) {
					System.out.println("bouton 4"  + value);
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
	
	private void lancerChenillard() {
		this.c.demarrer();
	}
	
	private void arreterChenillard() {
		this.c.eteindre();
	}
}
