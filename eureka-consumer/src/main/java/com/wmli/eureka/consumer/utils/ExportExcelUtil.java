package com.wmli.eureka.consumer.utils;

import com.wmli.eureka.consumer.model.ModelDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @description:
 * @version: 5.0
 * @date: Created in 9:23 2018/6/22
 */
@Slf4j
public class ExportExcelUtil {
    /**
     * 读取指定Sheet也的内容
     */
    public static String readExcel()
            throws Exception {
        // 创建 Excel 文件的输入流对象
        FileInputStream excelFileInputStream = new FileInputStream("D:/资料/数据/aaa.xlsx");
        // 创建其对象，就打开这个 Excel 文件
        XSSFWorkbook workbook = new XSSFWorkbook(excelFileInputStream);
        excelFileInputStream.close();
        // 注意表格索引从 0 开始！
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<ModelDO> list = new ArrayList<>();
        for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            ModelDO modelDO = new ModelDO();
            // XSSFRow 代表一行数据
            XSSFRow row = sheet.getRow(rowIndex);
            if (row == null) {
                continue;
            }
            XSSFCell nameCell = row.getCell(0); // token
            XSSFCell genderCell = row.getCell(1); // 运营商类型
            modelDO.setToken(nameCell.getStringCellValue());
            modelDO.setOperate(genderCell.getStringCellValue());

            StringBuilder employeeInfoBuilder = new StringBuilder();
            employeeInfoBuilder.append("第 "+(rowIndex+1)+" 次 token -> xyid --> ")
                    .append("token : ").append(nameCell.getStringCellValue())
                    .append(" , 运营商类型 : ").append(genderCell.getStringCellValue());
            //匹配设备ID
            readTxt("D:/资料/数据/token和xyid.txt",modelDO);
            /**
             * 根据设备ID匹配类型和系统
             *
             */
            //匹配安卓
            common(modelDO,"D:/资料/数据/bbb.xlsx");
            //匹配IOs
            common(modelDO,"D:/资料/数据/ccc.xlsx");

            list.add(modelDO);
//            System.out.println(employeeInfoBuilder.toString());
        }
        log.info("list result {}",list.toString());

        //第一步创建workbook
        XSSFWorkbook wb = new XSSFWorkbook();

        //第二步创建sheet
        XSSFSheet newSheet = wb.createSheet("一键登录统计信息");
        //第三步创建行row:添加表头0行
        XSSFRow row = newSheet.createRow(0);
        XSSFCellStyle style = wb.createCellStyle();
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //居中

        //第四步创建单元格
        XSSFCell cell = row.createCell(0);//第一个单元格
        cell.setCellValue("设备指纹TOKEN");
        cell.setCellStyle(style);

        cell = row.createCell(1);         //第二个单元格
        cell.setCellValue("设备指纹ID");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("设备类型");
        cell.setCellStyle(style);

        cell = row.createCell(3);         //第二个单元格
        cell.setCellValue("系统版本");
        cell.setCellStyle(style);

        cell = row.createCell(4);         //第二个单元格
        cell.setCellValue("运营商类型");
        cell.setCellStyle(style);


        //第五步插入数据
        for (int k = 0; k < list.size(); k++) {
            //创建行
            XSSFRow row1 = newSheet.createRow(k + 1);
            //创建单元格并且添加数据
            row1.createCell(0).setCellValue(list.get(k).getToken());
            row1.createCell(1).setCellValue(list.get(k).getXyid());
            row1.createCell(2).setCellValue(list.get(k).getUnitType());
            row1.createCell(3).setCellValue(list.get(k).getSysVer());
            row1.createCell(4).setCellValue(list.get(k).getOperate());
        }
        // 首先要创建一个原始Excel文件的输出流对象！
        FileOutputStream excelFileOutPutStream = new FileOutputStream("D:/资料/数据/一键登录统计信息.xlsx");
        // 将最新的 Excel 文件写入到文件输出流中，更新文件信息！
        wb.write(excelFileOutPutStream);
        // 执行 flush 操作， 将缓存区内的信息更新到文件上
        excelFileOutPutStream.flush();
        // 使用后，及时关闭这个输出流对象， 好习惯，再强调一遍！
        excelFileOutPutStream.close();
        System.out.println("文件生成成功");
        return "";
    }
    private static void common(ModelDO modelDO, String filePath) throws Exception{
        // 创建 Excel 文件的输入流对象
        FileInputStream ios = new FileInputStream(filePath);
        // 创建其对象，就打开这个 Excel 文件
        XSSFWorkbook book2 = new XSSFWorkbook(ios);
        ios.close();
        // 注意表格索引从 0 开始！
        XSSFSheet sheet2 = book2.getSheetAt(0);
        for (int j = 0; j<=sheet2.getLastRowNum(); j++) {
            XSSFRow row1 = sheet2.getRow(j);
            System.out.println("======================>"+row1.getCell(0).getStringCellValue());
            if (row1.getCell(0).getStringCellValue().equals(modelDO.getXyid())) {
                modelDO.setSysVer(row1.getCell(2).getStringCellValue());
                modelDO.setUnitType(row1.getCell(1).getStringCellValue());
                return;
            }
        }
    }

    public static void readTxt(String filePath, ModelDO modelDO)throws Exception{
        File file = new File(filePath);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String lineTxt;
        int i = 0;
        while ((lineTxt = br.readLine()) != null) {
            i++;
//            System.out.println("第"+i+"次="+lineTxt);
            String[] split = StringUtils.split(lineTxt, ",");
            if (modelDO.getToken().equals(split[1])) {
                modelDO.setXyid(split[0]);
                return;
            }
        }
        br.close();
    }

    public static void main(String[] args) {
        try {
            readExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
