public class CommunicationKNX {
    public static void main(String[] args) {
        String srcAdd;
        String destAdd;
        KNXNetworkLinkIP netLinkIp = new KNXNetworkLinkIP(KNXNetworkLinkIP.TUNNEL, srcAdd, destAdd, false, new TPSettings(false)); //ouvre une connexion KNX
    
        //KNXNetworkLinkIP netLinkIp = new KNXNetworkLinkIP(destAdd, new TPSettings(false));

        ProcessCommunicator pc = new ProcessCommunicatorImpl(netLinkIp); //créé un ProcessCommunicator

        Float temp = pc.readFloat(new GroupAddress("0/1/0")); //lire un float

        //lire température
        CommandDP temperature = new CommandDP(new GroupAddress("0/1/0"), "Température");
        temperature.setDPT(0, "9.001"); // DPT code available on KNX specifications
        System.out.println("Temperature: " + pc.read(temperature));

        pc.write(new GroupAddress("0/0/1"), true); //écrire un booléen

        netLinkIp.addLinkListener(new NetworkLinkListener(){
            public void confirmation(FrameEvent arg0) {
            }
            
            public void indication(FrameEvent arg0) {
            System.out.println("srcadress " + arg0.getSource());
            System.out.println("targetadress " +
            ((CEMILData)arg0.getFrame()).getDestination());
            }
            
            public void linkClosed(CloseEvent arg0) {
            }
            });
    }
}