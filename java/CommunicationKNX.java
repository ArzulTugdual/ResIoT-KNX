import 
public class CommunicationKNX {
    public static void main(String[] args) {
        String srcAdd;
        String destAdd;
        KNXNetworkLinkIP netLinkIp = new KNXNetworkLinkIP(KNXNetworkLinkIP.TUNNEL, srcAdd, destAdd, false, new TPSettings(false));
    
        //KNXNetworkLinkIP netLinkIp = new KNXNetworkLinkIP (destAdd, new TPSettings(false));
    }
}