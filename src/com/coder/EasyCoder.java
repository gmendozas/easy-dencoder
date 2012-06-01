/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coder;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author jmendoza
 */
public class EasyCoder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("LoggerFromMain");

        String cadena = "Mi soledad siempre he pertenecido a ti";
        EasyCoder m = new EasyCoder();

        String codificada = m.fusionEncode(cadena);
        logger.log(Level.INFO, codificada);
        logger.log(Level.INFO, m.fusionDecode(codificada));
    }

    public static String encode(String string) {
        return "";
    }

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

    public String decodeBinary(String param) {
        if("".equals(param))
            return "";
        if(param == null)
            return "";        
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokens = new StringTokenizer(param, " ");
        String caracter;
        while (tokens.hasMoreTokens()) {
            caracter = tokens.nextToken();
            sb.append((char) Integer.parseInt(caracter, 2));
        }
        return sb.toString();
    }

    public String encodeDecodeB64(String param, boolean encode) {
        String codificada = "";
        if(encode) {
             BASE64Encoder encoder = new BASE64Encoder();
            codificada = encoder.encode(param.getBytes());
        } else {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                codificada = new String(decoder.decodeBuffer(param));
            } catch (IOException ex) {
                Logger.getLogger(EasyCoder.class.getName()).log(Level.SEVERE, "OCURRIÓ UN ERROR", ex);
            }
        }
        return codificada;

    }

    public String fusionEncode(String param) {
        return encodeDecodeB64(encodeBinary(encodePlusOne(param)), true);
    }
    
    public String fusionDecode(String param) {
         String cadena = encodeDecodeB64(param, false);
         cadena = decodeBinary(cadena);
         cadena = decodePlusOne(cadena);
         return cadena;
    }
}
