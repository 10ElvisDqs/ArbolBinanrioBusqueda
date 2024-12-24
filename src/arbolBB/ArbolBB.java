/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolBB;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Quinteros Siles Elvis David
 */

public class ArbolBB implements Arbol {

    private Nodo raiz;
    int num_nodos;
    int alt;

    @Override
    public int contaHoja() {
        return contarHoja(raiz);
    }
    
    private int contarHoja(Nodo z){
        int ch;
        if (esVacio(z)) {
            ch=0;
        }else{
            if (esHoja(z)) {
                ch=1;
            }else{
                ch = contarHoja(z.HIzq) + contarHoja(z.HDer);
            }
        }
        return ch;
    }

    public void podar(){
        this.raiz = podar(this.raiz);
    }
    private Nodo podar(Nodo r){
        if(r == null){
            //no hacer nada
        }else{
            if(r.HIzq == null && r.HDer==null){
                // no tiene hijos
               r = null;
            }else{
                r.HIzq = podar(r.HIzq);
                r.HDer = podar(r.HDer);
            }
        }
        return r;
    }
    
    public void adicionarUno(){
        adicionarUno(this.raiz);
    }
    
    private void adicionarUno(Nodo p){
        if(p == null){
            //nada
        }else{
            p.dato = p.dato + 1;
            adicionarUno(p.HDer);
            adicionarUno(p.HIzq);
        }
    }
            

    public class Nodo{
        public int dato;
        public Nodo HIzq;
        public Nodo HDer;
        public static final int NULO = -9999;
        public Nodo(){
            dato=NULO;
            HIzq=null;
            HDer=null;
        }
        public Nodo getIzq(){
            return this.HIzq;
        }
        public Nodo getDer(){
            return this.HDer;
        }
        public int getDato(){
            return this.dato;
        }
    }

    public ArbolBB() {
        raiz = null;
        num_nodos=0;
    }
    private boolean esVacio(Nodo p){
        return p == null ? true : false;
    }
    private boolean esHoja(Nodo r){
        return ( r.HIzq == null && r.HDer == null);
    }
    public void adicionar(int dato) {
        raiz = adicionar(raiz, dato);
    }
    
    //Metodo para insertar un dato en el arbol...no acepta repetidos :)
    public Nodo adicionar(Nodo nuevo, int dato) {
        if ( esVacio(nuevo)) {
            Nodo p=new Nodo();
            p.dato=dato;
            nuevo = p ;
        } else {
            if (dato != nuevo.dato) {
                if ( dato <  nuevo.dato ) {
                    nuevo.HIzq = adicionar(nuevo.HIzq, dato);
                }else{
                    nuevo.HDer = adicionar(nuevo.HDer, dato);
                }
            }
            
        }
        return nuevo;
    }
    
    public int sumar(){
        return sumar(raiz);
    }
    
    private int sumar(Nodo p){
        int r;
        if (esVacio(p)) {
            r=0;
        }else{
            if (esHoja(p)) {
                r = p.dato;
            }else{
                r = p.dato + sumar(p.HIzq) + sumar(p.HDer);
            }
        }
        return  r;
    }
    
    public int mayorPar(){
        return mayorPar(this.raiz);
    }
    
    private int mayorPar(Nodo p){
        int mayor;
        if(p==null){
            mayor=0; 
        }else{
            int hizq=mayorPar(p.HIzq);
            int hder=mayorPar(p.HDer);
            if (p.dato % 2 == 0 ) {
                mayor=p.dato;
            }else{
                mayor = 0;
            }
            
            if (hizq>mayor) {
                mayor = hizq;
            }
            
            if (hder>mayor) {
                mayor = hder;
            }
        }
        return mayor;
    }
    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

     public JPanel getdibujo() {
        return new ArbolExpresionGrafico(this);
    }
     
    public void InOrden(JTextArea ta){
        InOrden(this.raiz,ta);
    }
    private void InOrden(Nodo r,JTextArea ta){
        if (r==null) {
            
        }else{
           InOrden(r.HIzq,ta);
           ta.append(r.dato+" -");
           InOrden(r.HDer,ta);
        }
    }
}
