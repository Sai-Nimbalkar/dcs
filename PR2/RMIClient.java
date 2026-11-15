import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class RMIClient {
    public static void main(String[] args) {
        try {
            // Connect to RMI Registry
            Registry registry =
                    LocateRegistry.getRegistry(
                            "localhost", 1099);
            // Look up remote object
            RemoteInterface remoteObject =
                    (RemoteInterface)
                    registry.lookup("HelloService");
            // Call remote method
            String response =
                    remoteObject.sayHello("Sai");

            System.out.println("Response from Server:");
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
