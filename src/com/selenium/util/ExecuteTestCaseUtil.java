package com.selenium.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.selenium.controller.TestCaseAction;
import com.selenium.dao.SeleniumDAO;
import com.selenium.dao.SeleniumDAOImpl;
import com.selenium.model.Action;
import com.selenium.model.TestCase;
import com.selenium.model.TestCaseStep;

public class ExecuteTestCaseUtil {
	
	public static final String LOAD_APPLICATION = "loadApplication";
	
	public static final String CHROME_BROWSER = "Chrome";
	
	public void executeTestCase(TestCase testCase){
		//String resultFilePath = null;
		//String[] a = {"a","b"};
		try {
			
			String FRAMEWORK_PATH = getRepositoryPath();
			String runnableJarPath = "RunnableJar.jar ";
			
			//String batchPath = FRAMEWORK_PATH+"/batch";

			long timestamp = Calendar.getInstance().getTimeInMillis();
			
			//String dataFileName = writeExcel(testCase,FRAMEWORK_PATH, timestamp);
			//String dataFilePath = "Data/"+dataFileName;
			//System.out.println(dataFilePath);
			
			String fileName = "RunTest_"+timestamp+".bat";
			File file = new File(FRAMEWORK_PATH,fileName);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("cd "+FRAMEWORK_PATH);
			bw.newLine();
			
			bw.write("java -jar "+runnableJarPath+testCase.getTestCaseId());
			bw.close();

			System.out.println("Done");
			
			/*
			List<String> cmdAndArgs = Arrays.asList("cmd","/c",fileName);
			File dir = new File(FRAMEWORK_PATH);
			
			ProcessBuilder pb = new ProcessBuilder(cmdAndArgs);
			pb.directory(dir);
			
			Process p = pb.start();
			*/
			System.out.println(fileName);
			System.out.println(FRAMEWORK_PATH);
			Process p = Runtime.getRuntime().exec("cmd /c " + fileName, null, new File(FRAMEWORK_PATH));
			p.waitFor();
			System.out.println("Done");
			p.destroy();
			
			//resultFilePath = readExcel(FRAMEWORK_PATH + "/" + dataFilePath,"ResultPath");
			//System.out.println(resultFilePath);
			//a[0] = readExcel(FRAMEWORK_PATH + "/" + dataFilePath,"ResultPath");
			//a[1] = readExcel(FRAMEWORK_PATH + "/" + dataFilePath,"Status");
			//System.out.println(resultFilePath);
			file.delete();
			//File file1 = new File(FRAMEWORK_PATH + "/" + dataFilePath);
			//file1.delete();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		//return a;
	}
	
	private  String getRepositoryPath() throws Exception{
		 InputStream inputStream = ExecuteTestCaseUtil.class.getClassLoader().getResourceAsStream( "/selenium.properties" );
	        Properties properties = new Properties();
	        
	            properties.load( inputStream );
	            String repositoryPath = properties.getProperty( "repository_path" );
	            return repositoryPath;
	}
	
	private String writeExcel(TestCase testCase,String repositoryPath,long timestamp){
		 //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); 
         
        //Create Test Suite sheet
        XSSFSheet testSuiteSheet = workbook.createSheet("TestSuite");
        
       // String testCaseName = "GooglePageAction";
        String testCaseName = testCase.getTitle();
        
      //Header Row
        Row row = testSuiteSheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("TestCaseName");
        cell = row.createCell(1);
        cell.setCellValue("Browser");
        cell = row.createCell(2);
        cell.setCellValue("URL");
        cell = row.createCell(3);
        cell.setCellValue("TobeExecuted");
        cell = row.createCell(4);
        cell.setCellValue("Status");
        cell = row.createCell(5);
        cell.setCellValue("ResultPath");
        	String URL = "";
       
        	SeleniumDAO dao = new SeleniumDAOImpl();
        	List<Action> actionList = dao.getActionsList();
        	Map<Integer,String> actionMap = new HashMap<Integer,String>();
        	for(Action action:actionList){
        		actionMap.put(action.getActionId(), action.getValue());
        	}
        	
        	 List<TestCaseStep> stepsList = testCase.getStepList();
             if(stepsList !=null ){
            	 for(TestCaseStep step:stepsList){
            		String action = actionMap.get(step.getActionId());
            		if(action.equalsIgnoreCase(LOAD_APPLICATION)){
            			URL = step.getObjectProperty();
            		}
            	 }
             }
             
             String browser = CHROME_BROWSER;
             if(testCase.getBrowser()!=null && !testCase.getBrowser().isEmpty()){
            	 browser = testCase.getBrowser();
             }
        
        
        //Testcase Row
        row = testSuiteSheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(testCaseName);
        cell = row.createCell(1);
        cell.setCellValue(browser);
        cell = row.createCell(2);
        cell.setCellValue(URL);
        cell = row.createCell(3);
        cell.setCellValue("Y");
        
        //Create Test Case Sheet
        XSSFSheet testCaseSheet = workbook.createSheet(testCaseName);
        
        //Header Row
        row = testCaseSheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue("ActionToBePerformed");
        cell = row.createCell(1);
        cell.setCellValue("ObjectPropertyValue");
        cell = row.createCell(2);
        cell.setCellValue("ObjectName");
        cell = row.createCell(3);
        cell.setCellValue("Input");
        cell = row.createCell(4);
        cell.setCellValue("ToBeExecuted");
        
       
        if(stepsList !=null ){
        	int index = 0;
        	
        	
        	for(TestCaseStep step:stepsList){
        		index++;
        		//Action Row
                row = testCaseSheet.createRow(index);
                cell = row.createCell(0);
                cell.setCellValue(actionMap.get(step.getActionId()));
                cell = row.createCell(1);
                cell.setCellValue(step.getObjectProperty());
                cell = row.createCell(2);
                cell.setCellValue("");
                cell = row.createCell(3);
                cell.setCellValue(step.getObjectValue());
                cell = row.createCell(4);
                if(step.isToBeExecuted()){
                cell.setCellValue("Y");
                }
                else{
                	cell.setCellValue("N");
                }
        	}
        }
        

        
        String fileName = "Data_"+timestamp+".xlsx";
        
        try
        {
        	String filePath = repositoryPath+"/"+"Data";
        	File file = new File(filePath,fileName);
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return "";
        }
        
        return fileName;
        
	}
	
	private String readExcel(String ExcelFilePath,String ColName) throws IOException{
		
		String Celldata = null;
		int Colnum = 0;
		FileInputStream ExcelFile = new FileInputStream(ExcelFilePath);
		XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
		XSSFSheet ExcelWSheet = ExcelWBook.getSheet("TestSuite");
		int colcount = ExcelWSheet.getRow(0).getLastCellNum();

		for (int i = 0; i < colcount + 1; i++) {
			XSSFCell Cell = ExcelWSheet.getRow(0).getCell(i);
			if (Cell.getStringCellValue().equals(ColName)) {
				Colnum = i;
				break;
			}
		}
		XSSFCell Cell = ExcelWSheet.getRow(1).getCell(Colnum);

		try {
			int type = Cell.getCellType();
			if (type == XSSFCell.CELL_TYPE_STRING) {
				Celldata = Cell.getStringCellValue();

			} else if (type == XSSFCell.CELL_TYPE_NUMERIC) {
				Celldata = String.valueOf(Cell.getNumericCellValue());
			}
		} catch (Exception e) {
			Celldata = null;
		}
		//Celldata.replaceAll("\","/");
		return Celldata;
		
	}

}
