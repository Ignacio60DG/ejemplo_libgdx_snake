package com.politecnicomalaga.libgdxe1.modelo;

import com.badlogic.gdx.graphics.Texture;

public class Serpiente {
    private float XPlayer;
    private float YPlayer;

    private Texture player;

    // Implementa un constructor con una posicion incial
    // Acepta como prametros la posicion incial
    // E inicializa el asset con el png del player
    public Serpiente(float XPlayer, float YPlayer, Texture player) {
        this.XPlayer = XPlayer;
        this.YPlayer = YPlayer;
        this.player = player;
    }

    // Propiedaes pra hacer get y set de la posicíon X y la Y

    public void setXPlayer(float XPlayer){
        this.XPlayer = XPlayer;
    }
    public void setYPlayer(float YPlayer){
        this.YPlayer = YPlayer;
    }
    public float getXPlayer(){
        return XPlayer;
    }
    public float getYPlayer(){
        return YPlayer;
    }

    // Propiedad solo get para el player
    public Texture getPlayerTexture(){
        return player;
    }

    public void dispose() {
        player.dispose();
    }
}
