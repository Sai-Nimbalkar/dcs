import HelloApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
public class HelloClient {
    public static void main(String[] args) {
        try {
            // Initialize ORB
            ORB orb = ORB.init(args, null);
            // Get Naming Service
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references(
                            "NameService");
            NamingContextExt ncRef =
                    NamingContextExtHelper.narrow(
                            objRef);
            // Find the remote object
            String name = "HelloService";
            Hello hello =
                    HelloHelper.narrow(
                            ncRef.resolve_str(name));
            // Call remote method
            String response =
                    hello.sayHello("Sai");
            System.out.println(
                    "Response from CORBA Server:");
            System.out.println(response);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
