package beans;

import java.io.Serializable;
import java.util.ArrayList;

import dao.LogDAO;
import dto.Log;

public class LogBean implements Serializable {

	private static final long serialVersionUID = -2555672636482367390L;

	public LogBean() {

	}

	public ArrayList<Log> getLogs() {
		ArrayList<Log> logs = LogDAO.getLogs();
		return logs;
	}
}