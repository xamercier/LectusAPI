package net.lectusAPI.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import net.lectusAPI.MainLectusApi;

public class ConfigurationManager {

	
    private JSONObject sqlConfig;
    private JSONObject srvModeConfig;
    private JSONObject srvConfig;
    
    /**
     * Creating new instance of the ConfigurationManager
     *
     * @throws IOException if config parser don't finhish with success
     */
    public ConfigurationManager() throws IOException {
        loadConfigurationFile();
    }

    /**
     * Loading the config.json file and parse it
     *
     * @throws IOException if config parser don't finish with success
     */
    private void loadConfigurationFile() throws IOException {
        URL defaultFile = getClass().getClassLoader().getResource("config.default.json");
        File configFile = new File(MainLectusApi.getInstance().getDataFolder() + File.separator, "config.json");
        if (!configFile.exists()) {
            if (defaultFile == null) {
                throw new FileNotFoundException("Unable to create default config file");
            }
            FileUtils.copyURLToFile(defaultFile, configFile);
        }

        BufferedReader reader = new BufferedReader(new FileReader(configFile));

        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line.trim());
        }
        reader.close();
        String content = builder.toString();

        JSONObject globalConfiguration = new JSONObject(content);

        sqlConfig = globalConfiguration.getJSONObject("SQL");
        srvModeConfig = globalConfiguration.getJSONObject("SERVERMODE");
        srvConfig = globalConfiguration.getJSONObject("serverConfig");
    }
    
    public String getSrvPrefix() {
    	return this.srvConfig.getString("srvPrefix");
    }
    
    public String getPlayerState() {
    	return this.srvConfig.getString("playerState");
    }
    
    public String getServerMode() {
    	if (srvModeConfig.getString("game").equalsIgnoreCase("no")) {
    		String mode = "other";
    		return mode;
    	} else {
    		String mode = "game";
    		return mode;
    	}
     }
    /**
     * Get host of the sql database
     *
     * @return the channel name
     */
    public String getSQLhost() {
        return this.sqlConfig.getString("host");
    }

    /**
     * Get database of the sql database
     *
     * @return the channel name
     */
    public String getSQLdatabase() {
        return this.sqlConfig.getString("database");
    }
    
    /**
     * Get user of the sql database
     *
     * @return the channel name
     */
    public String getSQLuser() {
        return this.sqlConfig.getString("user");
    }
    
    /**
     * Get password of the sql database
     *
     * @return the channel name
     */
    public String getSQLpassword() {
        return this.sqlConfig.getString("password");
    }
	
}
