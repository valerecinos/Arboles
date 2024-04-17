package com.mycompany.arboles;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Nodo {
  private String dato;
  private Nodo izquierda, derecha;
  private final int id;
  private static int correlativo = 1;

  public Nodo(String dato) {
    this.dato = dato;
    this.izquierda = this.derecha = null;
    this.id = correlativo++;
  }

  public String getDato() {
    return dato;
  }

  public Nodo getIzquierda() {
    return izquierda;
  }

  public void setIzquierda(Nodo izquierda) {
    this.izquierda = izquierda;
  }

  public Nodo getDerecha() {
    return derecha;
  }

  public void setDerecha(Nodo derecha) {
    this.derecha = derecha;
  }

  public String imprimirDato() {
    System.out.println(this.getDato());
    return this.getDato();
  }
public void graficar(String path) {
    try {
        // Crear el archivo DOT
        FileWriter fichero = new FileWriter("aux_grafico.dot");
        PrintWriter escritor = new PrintWriter(fichero);
        escritor.print(getCodigoGraphviz());
        fichero.close(); // Cerrar el archivo DOT después de escribirlo

        // Ejecutar el comando 'dot' para generar la imagen JPEG
        ProcessBuilder builder = new ProcessBuilder("dot", "-Tjpg", "-o", path, "aux_grafico.dot");
        builder.redirectErrorStream(true); // Redirigir errores a la salida estándar
        Process proceso = builder.start(); // Iniciar el proceso
        BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String linea;
        while ((linea = reader.readLine()) != null) {
            System.out.println(linea); // Imprimir mensajes de salida del proceso
        }
        int resultado = proceso.waitFor(); // Esperar a que el proceso termine
        if (resultado == 0) {
            System.out.println("La imagen se ha generado correctamente.");
        } else {
            System.err.println("Error al generar la imagen.");
        }
    } catch (Exception ex) {
        System.err.println("Error al generar la imagen: " + ex.getMessage());
    }
}


  /**
     
Método que retorna el código que grapviz usará para generar la imagen 
   
del árbol binario de búsqueda.
@return */
  private String getCodigoGraphviz() {
    return "digraph grafica{\n" + "rankdir=TB;\n" + "node [shape = record, style=filled, fillcolor=seashell2];\n" +
      getCodigoInterno() + "}\n";
  }

    private String getCodigoInterno() {
        String etiqueta;
        if (izquierda == null && derecha == null) {
            etiqueta = "nodo" + id + " [ label =\"" + dato + "\"];\n";
        } else {
            etiqueta = "nodo" + id + " [ label =\"<C0>|" + dato + "|<C1>\"];\n";
        }
        if (izquierda != null) {
            etiqueta += izquierda.getCodigoInterno() +
                "nodo" + id + ":C0->nodo" + izquierda.id + "\n";
        }
        if (derecha != null) {
            etiqueta += derecha.getCodigoInterno() +
                "nodo" + id + ":C1->nodo" + derecha.id + "\n";
        }
        return etiqueta;
    }


}