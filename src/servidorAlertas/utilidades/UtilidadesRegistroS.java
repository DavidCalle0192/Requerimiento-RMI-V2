
package servidorAlertas.utilidades;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class UtilidadesRegistroS
{
	public static void arrancarNS(int numPuertoRMI) throws RemoteException 
	{
		try
		{
                        
                    Registry registro = LocateRegistry.getRegistry(numPuertoRMI);  
                    String[] vector=registro.list();
                    for (String idNS : vector) {
                        System.out.println("ns : "+idNS );
                    }
                    System.out.println("El rmiRegistry se ha obtenido y se encuentra escuchando en el puerto: " + numPuertoRMI); 
			
		}
		catch(RemoteException e)
		{
			System.out.println("El rmiRegistry no se localizó en el puerto: " + numPuertoRMI);
			
			Registry registro = LocateRegistry.createRegistry(numPuertoRMI);
			System.out.println("El registro se ha creado en el puerto: " + numPuertoRMI);
		}
		
	}
        
        	
	public static void RegistrarObjetoRemoto(Remote objetoRemoto, String dirIP, int numPuerto, String nombreObjeto)
	{
		String UrlRegistro = "rmi://"+dirIP+":"+numPuerto+"/"+nombreObjeto;
		try
		{
			Naming.rebind(UrlRegistro, objetoRemoto);
			System.out.println("Se realizó el registro del objeto remoto en el ns ubicado en la dirección: " +dirIP+" y "+ "puerto"+numPuerto);
		} catch (RemoteException e)
		{
			System.out.println("Error en el registro del objeto remoto");
			e.printStackTrace();
		} catch (MalformedURLException e)
		{
			System.out.println("Error url inválida");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
}
