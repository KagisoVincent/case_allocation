package acsse.csc03a3.obj;
import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Clients.Reports;
/*
 * holder class is for both clients to have access in one blockchain
 */
public class Holder {
    public static Blockchain<Reports> container = new Blockchain<>();
}

