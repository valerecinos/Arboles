package com.mycompany.arboles;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JPanel;

public class Arbol {
    private Nodo raiz;
    private int alt;

    public Arbol() {
        this.raiz = null;
        this.alt = 0;
    }

    public Nodo getRaiz() {
        return raiz;
    }
    
    public void graficar (String path) {
        raiz.graficar(path);
    }
    public void leerArchivo(SimuladorArbol simulador) {
        String nombreArchivo = "C:\\Users\\valer\\Downloads\\Arboles\\Arboles\\src\\main\\java\\com\\mycompany\\arboles\\lectura.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split(",");
                for (String palabra : palabras) {
                    simulador.insertar(palabra.trim());
             
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    public void escribir(String contenido,String nombreArchivo){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(nombreArchivo))){
            writer.write(contenido);
        }catch(IOException e){
            System.err.println("Error al escribir en el archivo: "+e.getMessage());
        }
           
        
    }
    public int existe(String busqueda) {
        return existe(this.raiz, busqueda,0);
    }
    private int existe(Nodo n, String busqueda,int altura) {
        if (n == null) {
            return -1;
        }
        if (n.getDato().equals(busqueda)) {
            return altura;
        } else if (busqueda.compareTo(n.getDato()) < 0) {
            return existe(n.getIzquierda(), busqueda,altura+1);
        } else {
            return existe(n.getDerecha(), busqueda,altura+1);
        }
    }

    public boolean insertar(String dato) {
        if (this.raiz == null) {
            this.raiz = new Nodo(dato);
            return true;
        } else {
            insertar(this.raiz, dato);
            return true;
        }
    }

    private void insertar(Nodo padre, String dato) {
        if (dato.compareTo(padre.getDato()) > 0) {
            if (padre.getDerecha() == null) {
                padre.setDerecha(new Nodo(dato));
            } else {
                insertar(padre.getDerecha(), dato);
            }
        } else {
            if (padre.getIzquierda() == null) {
                padre.setIzquierda(new Nodo(dato));
            } else {
                insertar(padre.getIzquierda(), dato);
            }
        }
    }


    private void preorden(Nodo n,LinkedList recorrido) {
        if (n != null) {
            //n.imprimirDato();
            recorrido.add(n.getDato());
            preorden(n.getIzquierda(),recorrido);
            preorden(n.getDerecha(),recorrido);
        }
    }

    private void inorden(Nodo n,LinkedList recorrido) {
        if (n != null) {
            inorden(n.getIzquierda(),recorrido);
            //n.imprimirDato();
            recorrido.add(n.getDato());
            inorden(n.getDerecha(),recorrido);
        }
    }

    private void postorden(Nodo n,LinkedList recorrido) {
        if (n != null) {
            postorden(n.getIzquierda(),recorrido);
            postorden(n.getDerecha(),recorrido);
            recorrido.add(n.getDato());
            //n.imprimirDato();
        }
    }

    public LinkedList preorden() {
        LinkedList rec = new LinkedList();
        this.preorden(this.raiz,rec);
        return rec;
    }

    public LinkedList inorden() {
        LinkedList rec = new LinkedList();
        this.inorden(this.raiz,rec);
        return rec;
    }

    public LinkedList postorden() {
        LinkedList rec=new LinkedList();
        this.postorden(this.raiz,rec);
        return rec;
    }

    private void altura(Nodo aux, int nivel) {
        if (aux != null) {
            altura(aux.getIzquierda(), nivel + 1);
            alt = Math.max(alt, nivel); 
            altura(aux.getDerecha(), nivel + 1);
        }
    }
    public int getAltura() {
        altura(raiz, 1);
        return alt;
    }
}
