package net.lectusAPI.utils;

/**
 * This file is a part of the HAL project
 *
 * @author Aurele | com002 and Xavier | xamercier
 */
public class ErrorCatcher {

    /**
     * Catch an error with a """pretty""" (not so pretty but...) message
     *
     * @param e Error you want to catch
     */
    public static void catchError(Exception e) {
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        e.printStackTrace();
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    }


}
