package Speech.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Tatapower SED
 *
 */
public class VrasiServer {

    public VrasiServer() {
    }

    public static void main(String args[]) {

        try {
            VrasImplimentation vras = new VrasImplimentation();
            VrasInterface varsStub = (VrasInterface) UnicastRemoteObject.exportObject(vras, 1099);
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.bind("rmivras", varsStub);
            System.out.println("Server is ready to listen...");

        } catch (RemoteException e) {
            System.err.println("Server exception thrown: " + e.toString());
        } catch (AlreadyBoundException e) {
            System.err.println("Server exception thrown: " + e.toString());
        } catch (Exception e) {
            System.err.println("Server exception thrown: " + e.toString());
        }
    }
}
