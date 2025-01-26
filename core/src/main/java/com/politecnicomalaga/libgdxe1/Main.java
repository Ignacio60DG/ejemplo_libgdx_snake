package com.politecnicomalaga.libgdxe1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.politecnicomalaga.libgdxe1.modelo.Direccion;
import com.politecnicomalaga.libgdxe1.modelo.Serpiente;

/*  AAR
    Esta es la clase de partida de todos los videojuegos Libgdx. Desde esta clase, que es llamada desde el lanzador
    de escritorio, de android, de html, etc... se llamarán a los demás objetos de la capa de modelo, vista o controlador
 */



/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch; //Es la clase que representa una pantalla donde se pueden pintar imágenes

    private Texture endImage;
    //Aquí ponemos todas las Texture que necesitemos ahora mismo en el videojuego
    //Además todas las variables (son realmente atributos de Main) que necesitemos
    private int iPosXClicked;
    private int iPosYClicked;

    private float fVelPlayer;
    private Serpiente serpiente;

    private Direccion iDireccion;  //0 para arriba, 1 para abajo, 2 para izquierda, 3 para derecha
    private boolean bGanamos;

    private boolean bGameOver;

    private int ladoCuadrado = 10;

    private float tiempoDesdeUltimoMovimiento = 0;

    private float tiempoDesdeUltimoEngorde = 0;

    private float intervaloDeTiempoMovimiento = 0.5f;

    private float intervaloDeTiempoEngorde = 5.0f;

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
        iDireccion = Direccion.ARRIBA;
        fVelPlayer=10f;
        bGanamos = false;
        bGameOver = false;

        serpiente = new Serpiente(300, 300, new Texture("player.png"), ladoCuadrado);
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        tiempoDesdeUltimoMovimiento += delta;
        tiempoDesdeUltimoEngorde += delta;


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

        bGameOver = serpiente.buscaCuadrado(serpiente.getCuadrados()[0]);


        if (tiempoDesdeUltimoEngorde > intervaloDeTiempoEngorde){
            tiempoDesdeUltimoEngorde=0;
            serpiente.engordaSerpiente(iDireccion);
        }

        if (Gdx.input.justTouched()) {
            //Si entramos aquí es que se ha tocado/clicado la pantalla entre el anterior "render" y este
            iPosXClicked = Gdx.input.getX();
            iPosYClicked = Gdx.input.getY();

            if (iDireccion==Direccion.ARRIBA || iDireccion == Direccion.ABAJO) {
                if (iPosXClicked<serpiente.getXPlayer()) iDireccion=Direccion.IZQUIERDA; //Ibamos arriba o abajo, ahora a la izquierda
                else iDireccion=Direccion.DERECHA; //Han tocado por la derecha...
            } else {
                if (Gdx.graphics.getHeight()-iPosYClicked<serpiente.getYPlayer()) iDireccion=Direccion.ABAJO; //Ibamos izq o derecha, ahora abajo
                else iDireccion=Direccion.ARRIBA;  //vamos para arriba
            }
        }


        //------------------------------
        //Simulación del mundo
        //------------------------------
        //Dependiendo de la dirección, tenemos que actualizar las posiciones del jugador.
        if (!bGanamos && tiempoDesdeUltimoMovimiento >= intervaloDeTiempoMovimiento) {
            tiempoDesdeUltimoMovimiento = 0;
            switch (iDireccion) {
                case ARRIBA: //arriba
                    serpiente.setYPlayer(serpiente.getYPlayer() + fVelPlayer);
                    //fPosYPlayer+=fVelPlayer;
                    break;
                case ABAJO: //abajo
                    serpiente.setYPlayer(serpiente.getYPlayer() - fVelPlayer);
                    //fPosYPlayer-=fVelPlayer;
                    break;
                case IZQUIERDA: //izquierda
                    serpiente.setXPlayer(serpiente.getXPlayer() - fVelPlayer);
                    //fPosXPlayer-=fVelPlayer;
                    break;
                case DERECHA:
                    serpiente.setXPlayer(serpiente.getXPlayer() + fVelPlayer);
                   //fPosXPlayer+=fVelPlayer;
                    break;
            }
            //Evitamos que se salga
            // DE momento hemos puesto 50 el ancho de la imagen, mover esto a constante
            if (serpiente.getXPlayer()>Gdx.graphics.getWidth()-ladoCuadrado) serpiente.setXPlayer(Gdx.graphics.getWidth()-ladoCuadrado);
            if (serpiente.getYPlayer()>Gdx.graphics.getHeight()-ladoCuadrado) serpiente.setYPlayer(Gdx.graphics.getHeight()-ladoCuadrado);
            if (serpiente.getXPlayer()<0) serpiente.setXPlayer(0);
            if (serpiente.getYPlayer()<0) serpiente.setYPlayer(0);
        }


        //------------------------------
        // Dibujar
        //------------------------------

        //Dibujar. Es donde hacemos que el "mundo" del videojuego muestre sus datos al jugador
        //clear. Se trata de limpiar la pantalla. Siempre antes de empezar a dibujar cualquier cosa
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        //Rutina típica de dibujado. Hay que llamar obligatoriamente a begin y a end
        batch.begin();

        //Aquí los draw...
        //batch.draw(serpiente.getPlayerTexture(), serpiente.getXPlayer(), serpiente.getYPlayer());
        for(int i=0; i < serpiente.getCuadrados().length; i++)    {
            batch.draw(serpiente.getPlayerTexture(), serpiente.getCuadrados()[i].getX(), serpiente.getCuadrados()[i].getY());
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        endImage.dispose();
    }
}
