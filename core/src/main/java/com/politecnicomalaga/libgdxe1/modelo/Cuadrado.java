package com.politecnicomalaga.libgdxe1.modelo;

import com.badlogic.gdx.graphics.Texture;

public class Cuadrado {
    private float x;
    private float y;

    public Cuadrado(float x, float y) {
        this.x = x;
        this.y = y;

    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}


