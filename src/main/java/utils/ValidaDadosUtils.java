/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author gabri
 */
public class ValidaDadosUtils {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_CPF_REGEX
            = Pattern.compile("([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PHONE_REGEX
            = Pattern.compile("(^[0-9]{2})?(\\s|-)?(9?[0-9]{4})-?([0-9]{4}$)", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_CEP_REGEX
            = Pattern.compile("(^[0-9]{5})-?([0-9]{3}$)", Pattern.CASE_INSENSITIVE);

    public static boolean ValidaTexto(String texto) {

        if (texto.isEmpty() || texto.isBlank()) {
            return false;
        }

        return true;
    }

    public static boolean VerificaEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    public static boolean VerificaCPF(String cpfStr) {
        Matcher matcher = VALID_CPF_REGEX.matcher(cpfStr);
        return matcher.matches();
    }

    public static boolean VerificaPhone(String phoneStr) {
        Matcher matcher = VALID_PHONE_REGEX.matcher(phoneStr);
        return matcher.matches();
    }

    public static boolean VerificaCEP(String cepStr) {
        Matcher matcher = VALID_CEP_REGEX.matcher(cepStr);
        return matcher.matches();
    }

}
