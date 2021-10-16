package io.github.wslxm.springbootplus2.core.utils.excel;import com.alibaba.fastjson.JSON;import io.github.wslxm.springbootplus2.core.utils.json.JsonUtil;import io.swagger.annotations.ApiModel;import io.swagger.annotations.ApiModelProperty;import lombok.extern.slf4j.Slf4j;import org.apache.poi.hssf.usermodel.*;import org.apache.poi.ss.usermodel.*;import org.apache.poi.ss.util.CellRangeAddress;import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;import org.apache.poi.xssf.usermodel.XSSFWorkbook;import org.springframework.web.multipart.MultipartFile;import javax.servlet.ServletOutputStream;import javax.servlet.http.HttpServletResponse;import java.io.IOException;import java.io.InputStream;import java.lang.reflect.Field;import java.text.DecimalFormat;import java.text.SimpleDateFormat;import java.time.LocalDateTime;import java.time.format.DateTimeFormatter;import java.util.ArrayList;import java.util.LinkedHashMap;import java.util.List;import java.util.Map;@Slf4jpublic class ExcelUtil {    /**     * 解析上传的 Excel文件file数据，并返回     * <p>     *    返回数据：     *    List<Map<String, String>>     *    get(0)  =字段名     *    get(>0) =每一行的列表数据，可根据字段名 key读取每一行的数据     *  </P>     * @param file     * @return java.util.List<java.util.Map < java.lang.String, java.lang.String>>     * @author ws     * @mail 1720696548@qq.com     * @date 2020/5/5 0005 11:56     */    public static List<Map<String, String>> readExcel(MultipartFile file) {        List<Map<String, String>> rows = new ArrayList<>();        boolean isExcel2003 = true;        Workbook wb = null;        try {            // 上传路径            String uploadFilePath = file.getOriginalFilename();            // 文件后缀 -文件名 String uploadFileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));            String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.indexOf('.') + 1);            if (uploadFileSuffix.equals("xlsx")) {                isExcel2003 = false;            }            InputStream inputStream = file.getInputStream();            wb = isExcel2003 ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);            FormulaEvaluator evaluator = isExcel2003 ? new HSSFFormulaEvaluator((HSSFWorkbook) wb)                    : new XSSFFormulaEvaluator((XSSFWorkbook) wb);            // 获取表            Sheet sheet = wb.getSheetAt(0);            int rownum = sheet.getLastRowNum();            int cellNum = sheet.getRow(1).getLastCellNum();// 列数            Map<String, String> title = new LinkedHashMap<>();            for (int rowIndex = 1; rowIndex <= rownum; rowIndex++) {                Row row = sheet.getRow(rowIndex);                if (row == null || row.getCell(0) == null) {                    break;                }                Cell cell = row.getCell(0);                String value = "0";                if (cell != null) {                    if (cell.getCellTypeEnum() == CellType.NUMERIC) {                        if (HSSFDateUtil.isCellDateFormatted(cell)) {                            SimpleDateFormat sdf = null;                            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {                                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");                            } else {// 日期                                sdf = new SimpleDateFormat("yyyy-MM-dd");                            }                            value = sdf.format(cell.getDateCellValue());                        } else {                            DecimalFormat df = new DecimalFormat("#.##");                            value = df.format(cell.getNumericCellValue());                        }                    } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {                        value = cell.getBooleanCellValue() + "";                    } else if (cell.getCellTypeEnum() == CellType.FORMULA) {                        CellValue cellValue = evaluator.evaluate(cell);                        if (cellValue.getCellTypeEnum() == CellType.NUMERIC) {                            DecimalFormat df = new DecimalFormat("#.##");                            value = df.format(cellValue.getNumberValue());                        } else {                            value = cellValue.getStringValue();                        }                    } else if (cell.getCellTypeEnum() == CellType.BLANK) {                        value = "0";                    } else if (cell.getCellTypeEnum() == CellType.STRING) {                        value = cell.getStringCellValue();                    } else if (cell.getCellTypeEnum() == CellType._NONE) {                        value = "0";                    }                }                if (value.equals("") || value.equals("0")) {                    break;                }                Map<String, String> rowValue = new LinkedHashMap<>();                // 读取数据                for (int cellIndex = 0; cellIndex < cellNum; cellIndex++) {                    cell = row.getCell(cellIndex);                    value = "0";                    if (cell != null) {                        if (cell.getCellTypeEnum() == CellType.NUMERIC) {                            if (HSSFDateUtil.isCellDateFormatted(cell)) {                                SimpleDateFormat sdf = null;                                if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {                                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");                                } else {// 日期                                    sdf = new SimpleDateFormat("yyyy-MM-dd");                                }                                value = sdf.format(cell.getDateCellValue());                            } else {                                DecimalFormat df = new DecimalFormat("#.##");                                value = df.format(cell.getNumericCellValue());                            }                        } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {                            value = cell.getBooleanCellValue() + "";                        } else if (cell.getCellTypeEnum() == CellType.FORMULA) {                            CellValue cellValue = evaluator.evaluate(cell);                            if (cellValue.getCellTypeEnum() == CellType.NUMERIC) {                                DecimalFormat df = new DecimalFormat("#.##");                                value = df.format(cellValue.getNumberValue());                            } else {                                value = cellValue.getStringValue();                            }                        } else if (cell.getCellTypeEnum() == CellType.BLANK) {                            value = "0";                        } else if (cell.getCellTypeEnum() == CellType.STRING) {                            value = cell.getStringCellValue();                        } else if (cell.getCellTypeEnum() == CellType._NONE) {                            value = "0";                        }                    }                    if (rowIndex == 1) {                        // 所有字段名保存为小写 value.toLowerCase()                        rowValue.put(cellIndex + "", value.toLowerCase());                    } else {                        if (title.containsKey(cellIndex + "")) {                            rowValue.put(title.get(cellIndex + ""), value.replaceAll(",", "&prime;"));                        }                    }                }                if (rowIndex == 1) {                    title = rowValue;                }                rows.add(rowValue);            }        } catch (IOException e) {            log.debug(e.toString());        }        return rows;    }    /**     * excel 主方法(最少配置)     */    public static <T> boolean exportExcelDownload(List<T> ts, HttpServletResponse response) {        return exportExcelDownload(ts, 18, 20, true, true, true, response);    }    /**     * excel 主方法(配置-宽)     */    public static <T> boolean exportExcelDownload(List<T> ts, int width, HttpServletResponse response) {        return exportExcelDownload(ts, width, 20, true, true, true, response);    }    /**     * excel 主方法(配置-宽高)     */    public static <T> boolean exportExcelDownload(List<T> ts, int width, int height, HttpServletResponse response) {        return exportExcelDownload(ts, width, height, true, true, true, response);    }    /**     * excel 主方法(配置-是否需要标题/属性名/字段描叙行)     */    public static <T> boolean exportExcelDownload(List<T> ts, boolean isTitle, boolean isFieldName, boolean isFieldDesc, HttpServletResponse response) {        return exportExcelDownload(ts, 18, 20, isTitle, isFieldName, isFieldDesc, response);    }    /**     * excel 主方法(完整字段) -->  导出excel表格并下载(原始方法)     * <P>     *   Excel 导出，传入任意 VO/DTO/Entity 对象, 对象的字段需添加 @ApiModelProperty 和注解的 notes参数信息或value参数信息     *   1、自动获取 @ApiModelProperty 的 notes 或 value 为 Excel的第一行数据, (优先读取notes，没有notes读取value, 指定字段没有该数据标题处将为空)     *   2、自动获取属性名为 Excel的第二行数据     *   3、Excel的字段顺序 按 实体类的字段顺序来依次排序     *   4、如果某个字段没有@ApiModelProperty, 将不导出该字段     * </P>     * @param ts         * 下载保存到excel数据     * @param width        内容列宽     * @param height       内容行高     * @param isTitle      是否需要标题行 (默认true)     * @param isFieldName  是否需要字段属性名行 (默认true-实体类字段属性名)     * @param isFieldDesc  是否需要字段描叙行   (默认true-字段swagger上的中文备注)     * @param response   * java输出流 --> 保存到具体位置的文件     * @param <T>     * <P>     *   sheetName   工作表的名称, 导出后的文件名称（(x 弃用字段, 改用类上 @ ApiModel 注解的description 属性作为表名称)）     * </P>     * @return     */    public static <T> boolean exportExcelDownload(List<T> ts,                                                  int width,                                                  int height,                                                  boolean isTitle,                                                  boolean isFieldName,                                                  boolean isFieldDesc,                                                  HttpServletResponse response) {        // 字段        List<String> fieldNameList = new ArrayList();        // 描叙        List<String> fieldDescList = new ArrayList();        // 生成字段+描叙        Class<?> classIs = ts.get(0).getClass();        Field[] fields = classIs.getDeclaredFields();        for (Field field : fields) {            ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);            if (annotation != null) {                fieldDescList.add(!annotation.notes().equals("") ? annotation.notes() : annotation.value());                fieldNameList.add(field.getName());            }        }        // 获取导出表名称        ApiModel classAnnotation = classIs.getAnnotation(ApiModel.class);        String sheetName = "";        if (classAnnotation != null) {            sheetName = classAnnotation.description();        } else {            sheetName = classIs.getSimpleName();        }        // Excel大小        int sheetSize = 65536;        List<Map<String, Object>> list = (List<Map<String, Object>>) JSON.parseObject(JsonUtil.toJSONString(ts), List.class);        ExcelUtil.generateExcelDownload(list, fieldNameList, fieldDescList, sheetName, sheetSize, width, height, isTitle, isFieldName, isFieldDesc, response);        return true;    }    /**     * excel 主方法 -->  导出excel表格并下载(原始方法)     *     * @param dataList       * 导出数据 --> 从第三行开始录入     * @param fieldNameList  * [] 导出表头字段描叙 (每一列的数据的描叙, 相当于数据库字段描叙/实体类字段属性描叙)     * @param fieldDescList  * [] 导出表头字段属性名  (相当于数据库字段名/实体类字段属性名)     * @param sheetName      * 工作表的名称,导出后的文件名称     * @param sheetSize      * 每个sheet中数据的行数,此数值必须小于65536     * @param width          默认列宽（默认18）     * @param height         内容行高（默认20）     * @param isTitle,       是否需要标题行 (默认true)     * @param isFieldName,   是否需要字段属性名行 (默认true-实体类字段属性名)     * @param isFieldDesc,   是否需要字段描叙行   (默认true-字段swagger上的中文备注)     * @param response       * java输出流 --> 保存到具体位置的文件     */    private static boolean generateExcelDownload(List<Map<String, Object>> dataList,                                                 List<String> fieldNameList,                                                 List<String> fieldDescList,                                                 String sheetName,                                                 int sheetSize,                                                 int width,                                                 int height,                                                 boolean isTitle,                                                 boolean isFieldName,                                                 boolean isFieldDesc,                                                 HttpServletResponse response) {        // 产生工作薄对象        HSSFWorkbook workbook = new HSSFWorkbook();        if (sheetSize >= 65536) {            sheetSize = 65536;        }        double sheetNo = Math.ceil(Double.parseDouble(dataList.size() + "") / Double.parseDouble(sheetSize + ""));        for (int index = 0; index < sheetNo; index++) {            // 产生工作表对象            HSSFSheet sheet = workbook.createSheet();            // 设置工作表的名称            workbook.setSheetName(index, sheetName + "-" + index + 1);            // 开始行,excel默认0 开始, 判断是否需要生成标题,字段，属性行,需要开始行号自动往下移            int startRow = 0;            // 开始列,excel默认0 开始            int startCell = 0;            // 生成标题（1行）            if (isTitle) {                ExcelUtil.setTitle(workbook, sheet, new int[]{startRow, startRow, 0, fieldDescList.size() - 1}, sheetName, true, true, 30, 16,                        IndexedColors.BLACK.index, IndexedColors.WHITE.index);                startRow++;            }            // 生成字段描叙列            if (isFieldDesc) {                ExcelUtil.setRow(workbook, sheet, startRow, fieldDescList, true, true, 22, width, 12,                        IndexedColors.BLACK.index, IndexedColors.WHITE.index);                startRow++;            }            // 生成字段属性列            if (isFieldName) {                ExcelUtil.setRow(workbook, sheet, startRow, fieldNameList, true, false, 22, width, 10,                        IndexedColors.BLACK.index, IndexedColors.WHITE.index);                startRow++;            }            if (startRow != 0) {            }            ExcelUtil.setData(sheet, fieldNameList, dataList, startRow, startCell, height);        }        // 下载        ExcelUtil.download(workbook, response, sheetName);        return true;    }    /**     * excel 子方法 -->  合并在Excel 单元格并生成数据 (主用于标题生成)     * @author wangsong     * @param workbook     * @param sheet     * @param cellRangeAddress  起始行，截至行，起始列， 截至列  (索引)     * @param content 标题内容     * @param height            行高     * @param heightInPoints    字体大小  (默认12)     * @param bold              字体是否加粗  (默认false)     * @param color             字体颜色 ( 默认 IndexedColors.BLACK.index(黑))     * @param foregroundColor   背景颜色 ( 默认 IIndexedColors.WHITE.index(白))     * @param foregroundColor   字是否居中（默认不居中）     * @date 2020/9/27 0027 19:28     * @return void     * @version 1.0.1     */    public static void setTitle(HSSFWorkbook workbook,                                HSSFSheet sheet,                                int[] cellRangeAddress,  // 起始行，截至行，起始列， 截至列  (索引)                                String content,          // 内容                                boolean center,          // 是否居中(默认false)                                boolean bold,            // 是否加组（默认false）                                int height,              // 行高（默认20）                                int heightInPoints,      // 字体大小(默认12)                                short color,             // 字体大小(默认黑)                                short foregroundColor    // 背景颜色(默认白)    ) {        // =============样式start=============        HSSFCellStyle cellStyle = workbook.createCellStyle();        // 居中        if (center) {            // 水平居中            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);            // 垂直居中            cellStyle.setAlignment(HorizontalAlignment.CENTER);        }        // 加边框        // 下边框        cellStyle.setBorderBottom(BorderStyle.THIN);        //左边框        cellStyle.setBorderLeft(BorderStyle.THIN);        //右边框        cellStyle.setBorderRight(BorderStyle.THIN);        //上边框        cellStyle.setBorderTop(BorderStyle.THIN);        // 边框填充色        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);        // 背景色        cellStyle.setFillForegroundColor(foregroundColor == 0 ? IndexedColors.WHITE.index : foregroundColor);        // 字体        HSSFFont font = workbook.createFont();        // 字体大小-（默认12）        font.setFontHeightInPoints(heightInPoints == 0 ? 12 : (short) heightInPoints);        // 是否加粗-(默认false)        font.setBold(bold);        // 颜色-(默认黑色)        font.setColor(color == 0 ? IndexedColors.BLACK.index : color);        cellStyle.setFont(font);        // =============样式end=============        // 合并单元格 CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列        sheet.addMergedRegion(new CellRangeAddress(cellRangeAddress[0], cellRangeAddress[1], cellRangeAddress[2], cellRangeAddress[3]));        // 获取第一行（合并后的单元格）        HSSFRow rows = sheet.getRow(cellRangeAddress[0]);        if (rows == null) {            rows = sheet.createRow(cellRangeAddress[0]);        }        // 获取第几列的单元格(合并后的单元格)        HSSFCell cell = rows.createCell(cellRangeAddress[2]);        // 设置高（默认20）        rows.setHeight(height == 0 ? (short) (20 * 20) : (short) (height * 20));        // 设置单元格内容        cell.setCellValue(content);        // 设置样式        cell.setCellStyle(cellStyle);    }    /**     *  excel 子方法 -->  在Excel 中生成一行数据( 数据库或实体类对应的字段列, 或字段对应的描叙列)     *  @param height            行高     *  @param heightInPoints    字体大小  (默认12)     *  @param bold              字体是否加粗  (默认false)     *  @param color             字体颜色 ( 默认 IndexedColors.BLACK.index(黑))     *  @param foregroundColor   背景颜色 ( 默认 IIndexedColors.WHITE.index(白))     *  @param foregroundColor   字是否居中（默认不居中）     */    public static void setRow(HSSFWorkbook workbook,   // * 工作空间                              HSSFSheet sheet,         // * 页                              int rowNum,              // * 生成到那一行                              List<String> rowList,    // * 需要生成的内容                              boolean center,          // 是否居中(默认false)                              boolean bold,            // 是否加组（默认false）                              int height,              // 行高（默认20）                              int width,               // 列宽（默认18）                              int heightInPoints,      // 字体大小(默认12)                              short color,             // 字体颜色(默认黑)                              short foregroundColor    // 背景颜色(默认白)    ) {        // =============样式start=============        //居中        HSSFCellStyle cellStyle = workbook.createCellStyle();        if (center) {            // 水平居中            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);            // 垂直居中            cellStyle.setAlignment(HorizontalAlignment.CENTER);        }        // 加边框        //下边框        cellStyle.setBorderBottom(BorderStyle.THIN);        //左边框        cellStyle.setBorderLeft(BorderStyle.THIN);        //右边框        cellStyle.setBorderRight(BorderStyle.THIN);        //上边框        cellStyle.setBorderTop(BorderStyle.THIN);        //边框填充色        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);        // 背景色        cellStyle.setFillForegroundColor(foregroundColor == 0 ? IndexedColors.WHITE.index : foregroundColor);        // 字体        HSSFFont font = workbook.createFont();        // 字体大小-（默认12）        font.setFontHeightInPoints(heightInPoints == 0 ? 12 : (short) heightInPoints);        // 是否加粗-(默认false)        font.setBold(bold);        // 颜色-(默认黑色)        font.setColor(color == 0 ? IndexedColors.BLACK.index : color);        cellStyle.setFont(font);        // =============样式end=============        // 获取第二行        HSSFRow row = sheet.createRow(rowNum);        // 行高        row.setHeight(height == 0 ? (short) (20 * 20) : (short) (height * 20));        // 产生单元格        for (int i = 0; i < rowList.size(); i++) {            // 创建第一行各个字段名称的单元格            HSSFCell cell = row.createCell(i);            // 设置样式            cell.setCellStyle(cellStyle);            // 给单元格内容赋值,设置单元格内容为字符串型            cell.setCellValue(rowList.get(i));            // 列宽            width = width == 0 ? 18 : width;            sheet.setColumnWidth(i, 256 * width + 184);        }    }    /**     * excel 子方法 -->  在Excel 批量生成数据( key + values)     * @param sheet         * 页     * @param fieldNameList * 字段名, data数据的map的key,对应实体类属性名     * @param dataList      * 所有数据(list=每一行)     * @param startRow      * 从n行开始录入数据 (索引 0 开始)     * @param startCell     * 从n列开始录入数据 (索引 0 开始)     * @param height        行高（默认20）     */    public static void setData(HSSFSheet sheet, List<String> fieldNameList, List<Map<String, Object>> dataList, int startRow, int startCell, int height) {        for (int i = 0; i < dataList.size(); i++) {            // 写入行            int row = i + startRow;            // 当前行数据            Map<String, Object> map = dataList.get(i);            // 遍历字段            for (int j = 0; j < fieldNameList.size(); j++) {                // 写入列                int cell = j + startCell;                // 获取数据                Object value = map.get(fieldNameList.get(j + startCell));                // 写入                ExcelUtil.setValue(sheet, row, cell, value, height);            }        }    }    /**     * excel 子方法 -->  在Excel 指定位置生成数据(value)     * @param sheet    * 页     * @param row      * 行     * @param cell     * 列(单元格)     * @param value    * 填入内容     * @param height   行高（默认20）     */    public static void setValue(HSSFSheet sheet, int row, int cell, Object value, int height) {        // 获取行        HSSFRow rows = sheet.getRow(row );        if (rows == null) {            rows = sheet.createRow(row );        }        // 行高        rows.setHeight(height == 0 ? (short) (20 * 20) : (short) (height * 20));        // 获取列（单元格）        HSSFCell cells = rows.createCell(cell);        // 写入数据, 数据为空录入空字符串        if (value != null) {            cells.setCellValue(value + "");        } else {            cells.setCellValue("");        }    }    /**     * excel 子方法 -->  下载生成后的 Excel     * @author wangsong     * @param workbook     * @param response     * @param sheetName 文件名     * @date 2020/9/27 0027 20:52     * @return void     * @version 1.0.1     */    public static void download(HSSFWorkbook workbook, HttpServletResponse response, String sheetName) {        // 文件名带上当前时间        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");        sheetName += sheetName + "-" + df.format(LocalDateTime.now());        try {            // 信息头: 会告诉浏览器这个文件的名字和类型（必须设置）            response.setHeader("content-type", "application/octet-stream");            response.setContentType("application/octet-stream");            // 指定文件下载后的名称 --> 下载名中文乱码解决 --> java.net.URLEncoder.encode(fileXsl, "UTF-8")            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(sheetName + ".xls", "UTF-8"));            // 放入响应体            ServletOutputStream output = response.getOutputStream();            output.flush();            workbook.write(output);            output.close();        } catch (IOException e) {            log.debug(e.toString());        }    }    /**     * excel 子方法 -->  列几种常用颜色获取     * @return     */    public static short getColor(int i) {        short[] arr = {                IndexedColors.YELLOW1.index,                IndexedColors.DARK_YELLOW.index,                // IndexedColors.LIGHT_YELLOW.index        };        int index = i % (arr.length);        return arr[index];    }}