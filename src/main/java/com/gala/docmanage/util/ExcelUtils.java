package com.gala.docmanage.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @description:
 * @author: YBW
 * @date: 2020.10.16
 */
public class ExcelUtils {


    public static void main(String[] args) {
        String filePath = "C:\\Users\\62646\\Desktop\\docManage\\DocManage\\upload\\20201016\\测试表格.xlsx";

        System.out.println(getExcelData(filePath));
    }




    /**
     * 获取excel中的数据(前2行49列)
     * @param path
     * @author YBW
     * @date 2020.10.16
     */
    public static String getExcelData(String path) {
        //用来存放表中数据
        List<String> datas = new ArrayList<>();
        Workbook wb;
        Sheet sheet;
        Row row;
        String cellData;
        //取49列数据
        String columns[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9"
                , "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"
                , "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"
                , "30", "31", "32", "33", "34", "35", "36", "37", "38", "39"
                , "40", "41", "42", "43", "44", "45", "46", "47", "48", "49"};
        wb = readExcel(path);
        if (wb != null) {
            //获取第一个sheet
            sheet = wb.getSheetAt(0);

            //获取最大行数
//            int rownum = sheet.getPhysicalNumberOfRows();
            //取前2行数据
            int rownum = 2;

            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();

            for (int i = 0; i < rownum; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < colnum; j++) {
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        if(StringUtils.isNotEmpty(cellData)){
                            datas.add(cellData.trim());
                        }
                    }
                } else {
                    break;
                }
            }
        }
        return listToStr(datas);
    }


    /**
     * list转字符串
     * @param excelData
     * @author YBW
     * @date 2020.10.16
     */
    public static String listToStr(List<String> excelData) {
        StringBuffer sb = new StringBuffer();
        for (String excelDatum : excelData) {
            sb.append(excelDatum+",");
        }
        return sb.toString();
    }

    /**
     * 读取excel
     * @param filePath
     * @author YBW
     * @date 2020.10.16
     */
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }

}
