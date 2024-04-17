
package com.mycompany.arboles;

import java.util.LinkedList;
import javax.swing.JPanel;


public class SimuladorArbol {

    Arbol miArbol = new Arbol();
    
    public SimuladorArbol() {
    }
    
    public SimuladorArbol(Arbol arbol) {
        this.miArbol = arbol;
    }
    public void graficar(String path) {
        this.miArbol.graficar(path);
        
    }
    public boolean insertar(String dato) {
        return (this.miArbol.insertar(dato));
    }
    public String postorden(){
        LinkedList it=this.miArbol.postorden();
        return recorrido(it,"Recorrido PostOrden:");
    }
    public String preorden(){
        LinkedList it= this.miArbol.preorden();
        return recorrido(it,"Recorrido PreOrden:");
    }
    public String inorden(){
        LinkedList it = this.miArbol.inorden();
        return (recorrido(it, "Recorrido InOrden:"));
    }
    public int existe(String busqueda) {
        return this.miArbol.existe(busqueda);
    }
    private String recorrido(LinkedList it, String msg) {
        int i = 0;
        String r = msg + "\n";
        while (i < it.size()) {
            r += "\t" + it.get(i).toString() + "";
            i++;
        }
        return (r);
    }
}
