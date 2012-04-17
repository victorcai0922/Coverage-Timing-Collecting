package com.emmatask;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * Hello world!
 * 
 */
public class EmmaTask {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private String coverageEcDirectory;
	private String reportOutputDir;
	private String remoteIp;
	private String coverageEm;
	private String reportType;
	private String projectDirectory;
	private String taskTime;
	private String coverageFilename;
	private String coverageEcDirectoryFilename;
	private String path;
	private String instrFormat;
	private String emFileOutput;

	public EmmaTask() {
	}

	/**
	 * collect coverage info
	 * @throws IOException 
	 */
	public void collectTheCoverageInfo() throws IOException {
		logger.info("start collecting the coverage info!");
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		String dirName = dateFormat.format(new Date()).toString();
		File file = new File(coverageEcDirectory + "\\" + dirName);
		file.mkdirs();
		path = file.getAbsolutePath();
		System.out.println(path);
		String cmd = String.format(
				"java emma ctl -connect %s:47653 -command coverage.get,%s\\%s",
				remoteIp, path, coverageEcDirectoryFilename);
		execute(cmd);
	}

	/**
	 * generator coverage report
	 */
	public void generatorReport() {
		logger.info("start generator coverage report!");
		String cmd = String
				.format("java emma report -r %s -in %s,%s -Dreport.html.out.file=%s\\%s",
						reportType, coverageEm, path + "\\"
								+ coverageEcDirectoryFilename, path,
						coverageFilename+"."+reportType);
		execute(cmd);
	}

	public void collectAndGeneratorCoverageReport() {
		try {
			collectTheCoverageInfo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		generatorReport();
	}
	
	/**
	 * reset the coverage info
	 */
	public void resetCoverageInfo(){
		logger.info("start reset coverage info!");
		String cmd = String.format("java emma ctl -connect %s:47653 -command coverage.reset", remoteIp);
		execute(cmd);
	}
	
	/**
	 * instr the 
	 */
	public void instrJarFile(){
		logger.info("start instr coverage info!");
		String cmd = String.format("java emma instr -m overwrite %s -Dmetadata.out.file=%s", instrFormat,emFileOutput);
		execute(cmd);
	}

	private void execute(String cmd) {
		try {
			logger.info("start execute command: " + cmd);
			Process p = Runtime.getRuntime().exec(cmd);
			InputStream out = p.getInputStream();
			StringBuffer stringBuffer = new StringBuffer();
			readOutput(out, stringBuffer);
			logger.info("The output of command: " + stringBuffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readOutput(InputStream input, StringBuffer buffer) {
		try {
			int c;
			while ((c = input.read()) != -1)
				buffer.append((char) c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
	}

	public String getCoverageEcDirectory() {
		return coverageEcDirectory;
	}

	public void setCoverageEcDirectory(String coverageEcDirectory) {
		this.coverageEcDirectory = coverageEcDirectory;
	}

	public String getReportOutputDir() {
		return reportOutputDir;
	}

	public void setReportOutputDir(String reportOutputDir) {
		this.reportOutputDir = reportOutputDir;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getCoverageEm() {
		return coverageEm;
	}

	public void setCoverageEm(String coverageEm) {
		this.coverageEm = coverageEm;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getProjectDirectory() {
		return projectDirectory;
	}

	public void setProjectDirectory(String projectDirectory) {
		this.projectDirectory = projectDirectory;
	}

	public String getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(String taskTime) {
		this.taskTime = taskTime;
	}

	public String getCoverageFilename() {
		return coverageFilename;
	}

	public void setCoverageFilename(String coverageFilename) {
		this.coverageFilename = coverageFilename;
	}

	public String getCoverageEcDirectoryFilename() {
		return coverageEcDirectoryFilename;
	}

	public void setCoverageEcDirectoryFilename(
			String coverageEcDirectoryFilename) {
		this.coverageEcDirectoryFilename = coverageEcDirectoryFilename;
	}
}
