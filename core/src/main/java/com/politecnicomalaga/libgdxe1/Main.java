package com.politecnicomalaga.libgdxe1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.politecnicomalaga.libgdxe1.modelo.Serpiente;

import java.util.Random;

/*  AAR
    Esta es la clase de partida de todos los videojuegos Libgdx. Desde esta clase, que es llamada desde el lanzador
    de escritorio, de android, de html, etc... se llamarán a los demás objetos de la capa de modelo, vista o controlador
 */

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch; //Es la clase que representa una pantalla donde se pueden pintar imágenes
    //private Texture image;  //Esta es una instancia/objeto imagen
    //private Texture player;
    private Texture endImage;
    //Aquí ponemos todas las Texture que necesitemos ahora mismo en el videojuego
    //Además todas las variables (son realmente atributos de Main) que necesitemos
    private int iPosXClicked;
    private int iPosYClicked;

    //private float iPosXImagen;
    //private float iPosYImagen;

    //private float fPosXPlayer;
    //private float fPosYPlayer;
    private float fVelPlayer;
    private Serpiente serpiente;

    private int iDireccion;  //0 para arriba, 1 para abajo, 2 para izquierda, 3 para derecha
    private boolean bGanamos;

    @Override
    public void create() {
        /* AAR
            Método de inicialización de los atributos. Hace la función de "constructor", pero sin serlo. El constructor
            ApplicationAdapter es el encargado de llamar a este método. Lo veremos en profundidad cuando estudiemos herencia
         */
        batch = new SpriteBatch();
        //image = new Texture("mouse.png");
        //player = new Texture("player.png");
        endImage = new Texture("end.png");
        iDireccion =0;
        //iPosXImagen=200;
        //iPosYImagen=200;

        //fPosXPlayer=300;
        //fPosYPlayer=300;
        fVelPlayer=0.5f;
        bGanamos = false;

        serpiente = new Serpiente(300, 300, new Texture("player.png"));
    }

    @Override
    public void render() {

        /*  AAR
            Método corazón de todos los videojuegos Libgdx
            Aquí solemos tener:
             - control de entrada
             - simulación del mundo
             - control de cambios
             - dibujar el mundo

            Sabemos que se va a ejecutar una y otra vez, una y otra vez, hasta que alguién cierre la app
         */

        //------------------------------
        //Control de entrada
        //------------------------------

        if (Gdx.input.justTouched()) {
            //Si entramos aquí es que se ha tocado/clicado la pantalla entre el anterior "render" y este
            iPosXClicked = Gdx.input.getX();
            iPosYClicked = Gdx.input.getY();

            if (iDireccion==0 || iDireccion ==1) {
                if (iPosXClicked<serpiente.getXPlayer()) iDireccion=2; //Ibamos arriba o abajo, ahora a la izquierda
                else iDireccion=3; //Han tocado por la derecha...
            } else {
                if (Gdx.graphics.getHeight()-iPosYClicked<serpiente.getYPlayer()) iDireccion=0; //Ibamos izq o derecha, ahora abajo
                else iDireccion=1;  //vamos para arriba
            }
            /*
            if (bGanamos) {
                iDireccion =0;
                iPosXImagen=200;
                iPosYImagen=200;

                serpiente.setXPlayer(300);
                serpiente.setYPlayer(300);

                fVelPlayer=0.5f;
                bGanamos = false;
            }*/
        }

        //------------------------------
        //Simulación del mundo
        //------------------------------
        //Dependiendo de la dirección, tenemos que actualizar las posiciones del jugador.
        if (!bGanamos) {
            switch (iDireccion) {
                case 0: //arriba
                    serpiente.setYPlayer(serpiente.getYPlayer() + fVelPlayer);
                    //fPosYPlayer+=fVelPlayer;
                    break;
                case 1: //abajo
                    serpiente.setYPlayer(serpiente.getYPlayer() - fVelPlayer);
                    //fPosYPlayer-=fVelPlayer;
                    break;
                case 2: //izquierda
                    serpiente.setXPlayer(serpiente.getXPlayer() - fVelPlayer);
                    //fPosXPlayer-=fVelPlayer;
                    break;
                case 3:
                    serpiente.setXPlayer(serpiente.getXPlayer() + fVelPlayer);
                   //fPosXPlayer+=fVelPlayer;
                    break;
            }
            //Evitamos que se salga
            // DE momento hemos puesto 50 el ancho de la imagen, mover esto a constante
            if (serpiente.getXPlayer()>Gdx.graphics.getWidth()-50) serpiente.setXPlayer(Gdx.graphics.getWidth()-50);
            if (serpiente.getYPlayer()>Gdx.graphics.getHeight()-50) serpiente.setYPlayer(Gdx.graphics.getHeight()-50);
            if (serpiente.getXPlayer()<0) serpiente.setXPlayer(0);
            if (serpiente.getYPlayer()<0) serpiente.setYPlayer(0);
        }

        //También simulamos el "cambio" o "salto" de la imagen a perseguir
        /*
        if (!bGanamos && Math.random()>0.999) {//0,1% de posibilidades de "saltar" en cada frame
            Random dado = new Random();
            iPosXImagen = dado.nextInt(Gdx.graphics.getWidth());
            iPosYImagen = dado.nextInt(Gdx.graphics.getHeight());

        }*/

        //------------------------------
        //Control de cambios
        //------------------------------

        //Si han colisionado, hemos ganado
        /*
        if (colisionan(iPosXImagen,iPosYImagen,serpiente.getXPlayer(),serpiente.getYPlayer(), image.getWidth())) {
            //ganamos
            bGanamos = true;

        }*/



        //------------------------------
        // Dibujar
        //------------------------------

        //Dibujar. Es donde hacemos que el "mundo" del videojuego muestre sus datos al jugador
        //clear. Se trata de limpiar la pantalla. Siempre antes de empezar a dibujar cualquier cosa
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        //Rutina típica de dibujado. Hay que llamar obligatoriamente a begin y a end
        batch.begin();

        //Aquí los draw...
        /*if(bGanamos) {
            batch.draw(endImage, 80, 0);
        } else {*/
            //batch.draw(image, iPosXImagen, iPosYImagen);
            batch.draw(serpiente.getPlayerTexture(), serpiente.getXPlayer(), serpiente.getYPlayer());
        //}

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        //image.dispose();
        //player.dispose();
        endImage.dispose();
    }


    //Función colisiona. Determina cuando dos rectángulos están solapados en un espacio 2D
    public boolean colisionan(float fPosX1, float fPosY1, float fPosX2, float fPosY2, float fLado) {
        //Lado es el ancho y alto de los cuadrados que representan al jugador y a la imagen.
        //dos cuadrados se solapan parcial o totalmente si se solapan en el eje X y en el eje Y a la vez
        //un solapamiento en X implica que x1 y x2 no estén más lejos que el tamaño del lado
        //En Y, es lo mismo.
        return (Math.abs(fPosX1-fPosX2)<fLado && Math.abs(fPosY1-fPosY2)<fLado);
    }
}
