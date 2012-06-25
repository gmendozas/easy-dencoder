package com.coder;

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;

/**
 * <p>This class implements a low level encryption </p>
 * @author jmendoza
 */
public class EasyCoder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("LoggerFromMain");

        StringBuilder cadena = new StringBuilder("Waiting in my room and i lock the door\n");
        cadena.append("I watch the coloured animals across the floor\n");
        cadena.append("And im looking from a distance\n");
        cadena.append("And im listening to the whispers\n");
        cadena.append("And oh it aint the same, when your falling out of feeling and your\n");
        cadena.append("Falling in and caught again\n");
        EasyCoder m = new EasyCoder();

        String codificada = m.fusionEncode(cadena.toString());
        logger.log(Level.INFO, codificada);
        logger.log(Level.INFO, m.fusionDecode(codificada));
    }

    /**
     * Method that add to each character one unity to its ASCII value
     * @param param String to convert
     * @return A String "coded"
     */
    public String encodePlusOne(String param) {
        if ("".equals(param)) {
            return "";
        }
        if (param == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char car;

        for (char c : param.toCharArray()) {
            car = (char) (c + 1);
            sb.append(car);
        }
        return sb.toString();
    }

    /**
     * Method that decode a String, it decrements in one unity the value of each ASCII character
     * @param param String to decode
     * @return String "decoded"
     */
    public String decodePlusOne(String param) {
        if ("".equals(param)) {
            return "";
        }
        if (param == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char car;
        for (char c : param.toCharArray()) {
            car = (char) (c - 1);
            sb.append(car);
        }
        return sb.toString();
    }

    /**
     * Method that encode a String converts it to binary mode.
     * @param param String to encode
     * @return String "coded"
     */
    public String encodeBinary(String param) {
        if("".equals(param))
            return "";
        if(param == null)
            return "";
        int binario;
        StringBuilder sb = new StringBuilder();
        for (char c : param.toCharArray()) {
            binario = (int) c;
            sb.append(Integer.toBinaryString(binario));
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * Method that decode a String transforms it from its binary mode to ASCCI mode
     * @param param String to decode
     * @return String "decoded"
     */
    public String decodeBinary(String param) {
        if("".equals(param))
            return "";
        if(param == null)
            return "";        
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokens = new StringTokenizer(param, " ");
        String caracter;
        try {
        while (tokens.hasMoreTokens()) {
            caracter = tokens.nextToken();
            sb.append((char) Integer.parseInt(caracter, 2));
        }
        } catch(NumberFormatException nfe) {
            Logger.getLogger(EasyCoder.class.getName()).log(Level.SEVERE, "String isn't coded, so, it coudn't be decoded", nfe);
        }
        return sb.toString();
    }

    /**
     * Method that applies Base64 encode/decode
     * @param param String to be encode/decode
     * @param encode True for encode and false to decode
     * @return String coded/decoded
     */
    public String encodeDecodeB64(String param, boolean encode) {
        String codificada = "";
        if(encode) {
             byte[] codedBytes =  Base64.encodeBase64(param.getBytes());
            codificada = new String(codedBytes);
        } else {
            byte[] decodedBytes = Base64.decodeBase64(param);
            codificada = new String(decodedBytes);
           
        }
        return codificada;

    }

    /**
     * Method that use encodePlusOne, encodeBinary and encodeDecodeN64 to encode a String
     * @param param String to encode
     * @return String "coded"
     */
    public String fusionEncode(String param) {
        return encodeDecodeB64(encodeBinary(encodePlusOne(param)), true);
    }

    /**
     * Method that use decodePlusOne, decodeBinary and decodeDecodeN64 to decode a String
     * @param param String to decode
     * @return String "decoded"
     */
    public String fusionDecode(String param) {
         String cadena = encodeDecodeB64(param, false);
         cadena = decodeBinary(cadena);
         cadena = decodePlusOne(cadena);
         return cadena;
    }
}
