/*
Esercizio 4.4.8 pag. 78
 */

package es448;

/**
 * @author erotundo
 */

//gerarchia dei TIPI
interface Sms {} //niente qui dentro? boh
interface SmsLungo extends Sms { 
    Sms[] spezza(); //spezza il testo lungo in array di sms normali
}

//gerarchia di IMPLEMENTAZIONE
class Msg {
    String destinatario; /*, data, ora, mittente; */ //troppo lazy per fare tutti i metodi set()
    void setDestinatario(String s){
        destinatario = s;
    }
    
}

class MsgAudio extends Msg{
    byte[] audio;

}

abstract class MsgTestuale extends Msg {
    abstract Boolean aggiungi(char c); //da implemetare
    @Override
    public abstract String toString(); //display 25 char per riga ??
    
}

class SmsLungoImpl extends MsgTestuale implements SmsLungo {
    String testoNoLimite;
    
    @Override
    Boolean aggiungi(char c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sms[] spezza() {
        //conto quanti smscorti ci stanno in smslungo
        //creo larray di smscorti
        //lo carico
        int numSms;
        numSms = testoNoLimite.length() / SmsImpl.limite;
        if((testoNoLimite.length() % SmsImpl.limite) != 0){ //ce un sms corto non "completo" ossia lungo meno di 160 char 
            numSms++;
        }
        if(numSms > 0){
            SmsImpl[] c = new SmsImpl[numSms];
            int index_sms = 0;
            
        for(int index_char=0; index_char < testoNoLimite.length(); ){
            c[index_sms].aggiungi(testoNoLimite.charAt(index_char));
            index_char++;
            if((index_char % SmsImpl.limite) == 0){
                index_sms++;
            }
        }
            return c;
        }
        else
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    
    }   
}

class SmsImpl extends MsgTestuale implements Sms {
    public static int limite = 160; //va nell implementazione o nell interfaccia?
    char[] testoLimitato = new char[limite];
    
    @Override
    Boolean aggiungi(char c) {
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

public class Es448 /*  */{


    public static void main(String[] args) {
       
    }
    
}
