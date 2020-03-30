package net.lectusAPI.utils;

import java.util.HashMap;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;

/**
 * 
 * Manage the time
 *
 * @author com002|Aur√®le
 * @version 1.4
 */
public class TimeManager {

    private static TimeManager instance = new TimeManager();
    private static HashMap<String, Long> units = new HashMap<>();

    private TimeManager() {
    }

    /*
    Unit List
    -----------
    Y - Year
    M - Month
    W - Week
    D - Day
    H - Hour
    m - Minute
    S - Second
     */

    static {
        units.put("Y", 31540000000L);
        units.put("M", 2628000000L);
        units.put("W", 604800000L);
        units.put("D", 86400000L);
        units.put("H", 3600000L);
        units.put("m", 60000L);
        units.put("S", 1000L);
    }

    public Long convert(String time) {
        return getTimeFromUnit(getUnit(time)) * getMultiplier(time);
    }

    public Long convertFromNow(String time) {
        return System.currentTimeMillis() + convert(time);
    }

    public String getUnit(String time) {
        return Pattern.compile("(?<=[0-9])(?=[a-zA-Z])").split(time)[1];
    }

    public String getUnitAndTime(String time) {
    	return getMultiplier(time) + " " + getUnit(time); 
    }
    
    public Long getMultiplier(String time) {
        return Long.valueOf(Pattern.compile("(?<=[0-9])(?=[a-zA-Z])").split(time)[0]);
    }

    public Long getTimeFromUnit(String unit) {
        return units.get(unit);
    }

    public String unitToString(String time) {
        String unit = getUnit(time);
        String string;
        switch (unit) {
            case "Y":
                string = "annÈe";
                break;
            case "M":
                string = "mois";
                break;
            case "W":
                string = "semaine";
                break;
            case "D":
                string = "jour";
                break;
            case "H":
                string = "heure";
                break;
            case "m":
                string = "minute";
                break;
            case "S":
                string = "seconde";
                break;
            default:
                string = "unitÈ introuvable.";
        }
        if (!string.toLowerCase().startsWith("unit") && !string.endsWith("s")) {
            if (getMultiplier(time) != 1) { // si 0 ou 2 et plus; ajout de S
                string += "s";
            }
        }
        return string;
    }

    public static boolean isCorrectFormat(String time) {
        return Pattern.compile("(\\d+)([YMWDHmS])").matcher(time).matches();
    }

    public static TimeManager get() {
        return instance;
    }

    public String getTimeHelp() {
        return ChatColor.YELLOW + "Utilisation:" + "\n" +
                ChatColor.GRAY + "MultiplicateurUnite " + ChatColor.GREEN + "Ex: 5D = 5 Jours " + "\n" +
                ChatColor.YELLOW + "UnitÈs: " + "\n" +
                ChatColor.RED + "DEF = " + ChatColor.GRAY + "DÈfinitivement" + "\n" +
                ChatColor.RED + "Y = " + ChatColor.GRAY + "AnnÈe(s)" + "\n" +
                ChatColor.RED + "M = " + ChatColor.GRAY + "Mois" + "\n" +
                ChatColor.RED + "W = " + ChatColor.GRAY + "Semaine(s)" + "\n" +
                ChatColor.RED + "D = " + ChatColor.GRAY + "Jour(s)" + "\n" +
                ChatColor.RED + "H = " + ChatColor.GRAY + "Heure(s)" + "\n" +
                ChatColor.RED + "m = " + ChatColor.GRAY + "Minute(s)" + "\n" +
                ChatColor.RED + "S = " + ChatColor.GRAY + "Seconde(s)";
    }
}
