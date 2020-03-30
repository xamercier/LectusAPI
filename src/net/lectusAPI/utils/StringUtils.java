package net.lectusAPI.utils;

import org.bukkit.ChatColor;

public class StringUtils {
	
	/**
	 * Formatter un String selon l'exemple suivant:
	 * 
	 * <pre>
	 * 
	 * String nom = "Bonvin";
	 * Stirng prenom = "Aurele";
	 * String s = "Bonjour {0}{1}";
	 * 
	 * String format = StringUtils.format(s, nom, prenom);
	 * </pre>
	 * 
	 * @param base
	 *            Le string de base
	 * @param args
	 *            Les arguments (commencant a 0, 1, ...)
	 * @return String formaté
	 */
	public static String format(String base, Object... args) {
		String tmp = base;
		for (int i = 0; i < args.length; i++) {
			tmp = tmp.replace("{" + i + "}", args[i].toString());
		}
		return tmp;
	}
	
	/**
	 * Transformer les
	 * 
	 * <pre>
	 * & en $
	 * </pre>
	 * 
	 * pour les couleurs
	 * 
	 * @param string
	 *            String a formatter
	 * @return Message avec couleur
	 */
	public static String formatColor(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
}
