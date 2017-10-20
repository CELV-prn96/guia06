/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uesocc.edu.sv.ingenieria.prn335_2017.datos.acceso;

import java.util.List;
import uesocc.edu.sv.ingenieria.prn335_2017.datos.definiciones.Usuario;

/**
 *
 * @author cesarlinares
 */
public interface AbstractInterface <T> {
    
    boolean create(T entity );

    boolean edit(T entity);

    boolean remove(T entity);

    T find(Object id);

    List<T> findAll();
    
    List<T> findRange(int first, int ultimate);

    int count();
    
    
    
}
