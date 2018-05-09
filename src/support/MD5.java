/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;
import java.security.*;
import java.math.*;

public class MD5 {

    public static void main(String args[]) throws Exception {
        
        System.out.println(criptoSenha(""));
        System.out.println(criptoSenha(" "));
        System.out.println(criptoSenha("  "));
        System.out.println(criptoSenha("Lucas"));
        System.out.println(criptoSenha("LUCAS"));
        System.out.println(criptoSenha("Lucas_Lucian"));
        System.out.println(criptoSenha("LUCAS"));
        
    }

    public static String criptoSenha(String s) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(), 0, s.length());
        return (new BigInteger(1, m.digest()).toString(16));
    }
}