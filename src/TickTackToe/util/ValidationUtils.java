package TickTackToe.util;

public class ValidationUtils {
    private ValidationUtils(){

    }
    public static void require(boolean condition, String errorMessage){
        if(!condition){
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
