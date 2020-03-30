package net.lectusAPI.thread;

import net.lectusAPI.MainLectusApi;
import net.lectusAPI.sql.SQLMain;

public class SQLThread extends Thread {

	public void run() {
		while (true) {
			try {
				Thread.sleep(300000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			MainLectusApi.getInstance().getSql().deconnexion();
			MainLectusApi.getInstance().sql = null;
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			MainLectusApi.getInstance().sql = new SQLMain("jdbc:mysql://", MainLectusApi.getInstance().getConfigurationManager().getSQLhost(), MainLectusApi.getInstance().getConfigurationManager().getSQLdatabase(),
					MainLectusApi.getInstance().getConfigurationManager().getSQLuser(), MainLectusApi.getInstance().getConfigurationManager().getSQLpassword());;
			MainLectusApi.getInstance().getSql().connexion();
		}
	}

}