import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    public static void main(String[] args) {

        try {

            // Create remote object
            RemoteImplementation remoteObject =
                    new RemoteImplementation();

            // Create RMI Registry on port 1099
            Registry registry =
                    LocateRegistry.createRegistry(1099);

            // Bind remote object with a name
            registry.rebind("HelloService", remoteObject);

            System.out.println("RMI Server started...");
            System.out.println("RMI Registry running on port 1099");
            System.out.println("Waiting for client...");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
