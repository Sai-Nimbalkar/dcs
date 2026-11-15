import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
public class RemoteImplementation
        extends UnicastRemoteObject
        implements RemoteInterface {

    public RemoteImplementation() throws RemoteException {
        super();
    }
    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello " + name + "! Message received from RMI Server.";
    }
}
