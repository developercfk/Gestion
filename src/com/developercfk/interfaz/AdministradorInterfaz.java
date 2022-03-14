
package com.developercfk.interfaz;

import com.developercfk.pojo.Administrador;
import java.util.List;

/**
 *
 * @author CFK Local
 */
public interface AdministradorInterfaz {
    
    public void createAdministrator(Administrador administrador);
    public Administrador readAdministrator(int id);
    public void updateAdministrator(Administrador administrador);
    public void deleteAdministrator(Administrador administrador);
    public List<Administrador> ListAdministrator();
   


}
