
package serverhttp;

import com.fazecast.jSerialComm.SerialPort;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

/**
 *
 * @author MarcoMedina
 */
public class Concentradora implements HttpHandler
{
    SerialPort sp;
    
    public void imprime(String url)
    {
        System.out.println("Log->>> " + url);
    }
    
    public void handle(HttpExchange he) throws IOException
    {
        String URL = he.getRequestURI().getPath();
        String[] url = URL.split("/");
        imprime("modulo: " + url[1] + " metodo: " + url[2]);
        
        String requestedMethod = "";//Almacena el nombre del método solicitado
        String argumentos = "";//Almacena los argumentos que utilizara el método 
        
        imprime("metodo: " + url[2]);//Imprime el nombre del método con los argumentos
        
        //Se separa el nombre del método de sus argumentos método(a, 2) = [metodo] [(a, 2)]
        if(url[2].indexOf("(") >= 0)
        {
            //obtenemos el nombre del método
            int separador = url[2].indexOf("(");//obtenemos el valor de la posición donde se encuentra el caracter "("
            requestedMethod = url[2].substring(0, separador);//sustraemos el nombre del método desde la posición 0 hasta el valor de la posición hasta donde empieza el caracter "("
            
            imprime("método: " + requestedMethod);//imprime sólo el nombre del método
            
            //en esas líneas de lo que viene en el método dentro de los parentesis
            //obtengo los argumentos separandolos por las comas y los voy guardandolo en un arreglo
            argumentos = url[2].substring(separador + 1);//en argumentos obtengo el valor de lo que hay en la posición en donde se encuentra el caracter ")"
            int fin = argumentos.indexOf(")");//en la variable fin obtengo el final de los argumentos determinado por la posición en donde se encuentra el caracter ")"
            argumentos = argumentos.substring(0, fin);//ahora si ya en argumentos obtengo solo lo que hay dentro de los parentesis
            imprime("argumentos: " + argumentos);
            
            String[] argv = null;//este arreglo lo vamos a usar para almacenar cada valor de los argumentos
            
            int argc = 0;//Contiene el número de argumentos
            
            if(argumentos != "")//preguntamos si argumentos tiene algo
            {
                argv = argumentos.split(",");//con el split separamos los argumentos por comas
                imprime("arg1: " + argv[0] + " arg2: " + argv[1]);//imprimimos los valores de cada argumento
                argc = argv.length;//obtenemos el número de argumentos
                imprime("numero de argumentos: " + argc);
                turnOnActuador(argv[0]);
            }
            
            //AQUI HAY QUE CREAR UN SWICT O ALGUN CONTROL PARA
                //MANDAR LLAMAR EL METODO CORRESPONDIENTE
                //switch(metodo)
                //if
                //O ALGUN OTRO TIPO DE FLUJO DE OCNTROL 
                turnOnActuador(argumentos);          
                
                
                /*
                DESPUES DE RECIBIR Y PROCESAR EL REQUEST
                DEBEMOS ENVIAR UN RESPONSE A LA APP CLIENTE
                PUEDE SER UN SIMPLE "OK"
                */
        }
        
        throw new UnsupportedOperationException("NotSupported yet");//To change body of generated methods, choose Tools | Templates.
    }
    
    public void turnOnActuador(String tarjeta)
    {
        // este metodo es para encender un actuador
        // pero primero hay que ir al diccionario o a un archivo
        // a consultar el valor del nombre de la tarjeta proporcionada
        // para concatenarlo al string y completar el comando
        byte[] buffer = {0x00, 0x00, 0x34};
        sp = SerialPort.getCommPort("com3");
        sp.openPort();
        sp.writeBytes(buffer, buffer.length);
        imprime("Enciendo la tarjeta: " + tarjeta);
        sp.closePort();
    }
    
    public void turnOffActuador(String tarjeta)
    {
        // Esté metodo es para mandar el comando de apagar
        byte[] buffer={0x00,0x00,0x34};
        sp = SerialPort.getCommPort("com3");
        sp.openPort();
        sp.writeBytes(buffer, buffer.length);
        sp.closePort();
        imprime("Enciendo la tarjeta: "+tarjeta);
    }
    
    public void turnOnLedColor(String tarjeta, String color){
    
        // Esté metodo es para mandar el comando de encender en un color especifico
        byte[] buffer={0x00,0x00,0x34};
        sp = SerialPort.getCommPort("com3");
        sp.openPort();
        sp.writeBytes(buffer, buffer.length);
        sp.closePort();
        imprime("Enciendo la tarjeta: "+tarjeta);        
    }   
    
    public void sendComand(String comando)
    {        
        //este metodo es para enviar el comando a la tarjeta concentradora
        byte[] buffer={0x00,0x00,0x34};
        sp = SerialPort.getCommPort("com3");
        sp.openPort();
        sp.writeBytes(buffer, buffer.length);
        sp.closePort();
        imprime("envio comando: "+comando);        
    }
}
