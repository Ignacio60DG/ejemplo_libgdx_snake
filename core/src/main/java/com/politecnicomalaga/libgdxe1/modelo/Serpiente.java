package com.politecnicomalaga.libgdxe1.modelo;

import com.badlogic.gdx.graphics.Texture;

public class Serpiente {
    // Hacer: Informar esto desde el constructor
    private float ladoCuadradoSerpiente;

    private Texture player;

    private Cuadrado[] cuadrados;

    // Implementa un constructor con una posicion incial
    // Acepta como prametros la posicion incial
    // E inicializa el asset con el png del player
    public Serpiente(float xPlayer, float yPlayer, Texture player, float ladoCuadrado) {
        // Inicialmente creamos una serpiente de dos cuadrados y el primero 50 menos en horizontal
        // que la posicion central
        this.cuadrados = new Cuadrado[2];
        this.cuadrados[0] = new Cuadrado(xPlayer, yPlayer); // 0 es la cabeza de la serpiente
        this.cuadrados[1] = new Cuadrado(xPlayer - this.ladoCuadradoSerpiente, yPlayer);
        this.ladoCuadradoSerpiente = ladoCuadrado;

        this.player = player;
    }

    // Propiedaes pra hacer get y set de la posicíon X y la Y

    private void addNuevoCuadrado(float x, float y) {
        Cuadrado nuevoCuadrado = new Cuadrado(x, y);
        Cuadrado[] nuevoArrayCuadrados = new Cuadrado[cuadrados.length];

        // Copiamos el array original
        // Desde la posición 0 hasta la penultima (la ultima celda la descartamos)ç
        // Y la posición cerro dejamos el hueco
        System.arraycopy(cuadrados ,0, nuevoArrayCuadrados, 1, cuadrados.length-1);
        nuevoArrayCuadrados[0] = nuevoCuadrado;
        this.cuadrados = nuevoArrayCuadrados;
    }

    public void setXPlayer(float xPlayer){
        this.addNuevoCuadrado(xPlayer,cuadrados[0].getY());
    }
    public void setYPlayer(float yPlayer){
        this.addNuevoCuadrado(cuadrados[0].getX(), yPlayer);
    }
    public float getXPlayer(){
        return cuadrados[0].getX();
    }
    public float getYPlayer(){
        return cuadrados[0].getY();
    }

    public Cuadrado[] getCuadrados() {
        return cuadrados;
    }

    // Propiedad solo get para el player
    public Texture getPlayerTexture(){
        return player;
    }

    public void dispose() {
        player.dispose();
    }
}
