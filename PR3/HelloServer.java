
import HelloApp.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;

class HelloImpl extends HelloPOA {

    public String sayHello(String name) {
        return "Hello " + name + "! Welcome to CORBA.";
    }
}

public class HelloServer {

    public static void main(String[] args) {

        try {

            // Create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // Get Root POA
            POA rootpoa =
                    POAHelper.narrow(
                            orb.resolve_initial_references(
                                    "RootPOA"));

            rootpoa.the_POAManager().activate();

            // Create servant
            HelloImpl helloImpl = new HelloImpl();

            // Get object reference
            org.omg.CORBA.Object ref =
                    rootpoa.servant_to_reference(
                            helloImpl);

            Hello href =
                    HelloHelper.narrow(ref);

            // Get Naming Service
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references(
                            "NameService");

            NamingContextExt ncRef =
                    NamingContextExtHelper.narrow(
                            objRef);

            // Register the object
            String name = "HelloService";

            NameComponent path[] =
                    ncRef.to_name(name);

            ncRef.rebind(path, href);

            System.out.println(
                    "CORBA Server ready...");

            System.out.println(
                    "Waiting for client...");

            // Keep server running
            orb.run();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
