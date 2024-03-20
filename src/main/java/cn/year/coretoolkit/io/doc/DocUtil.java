package cn.year.coretoolkit.io.doc;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.util.List;
import java.util.Objects;

/**
 * @author YearOfTheRain
 * @create 2024-03-16  14:56
 */
public class DocUtil {

    public static void main(String[] args) throws IOException {
        String filePath = "D:\\Program Files (x86)\\nn\\WeChat Files\\wxid_ayb71xcacks422\\FileStorage\\File\\2024-03\\学位英语词汇短语汇总及基础练习.docx";

//        String filePath = "D:\\projects\\java\\com\\qq\\weixin\\mp\\aes\\new 6.html";
//        FileInputStream reader = new FileInputStream(filePath);
//        InputStreamReader inputStreamReader = new InputStreamReader(reader ,"UTF-8");
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        while (bufferedReader.read() != -1) {
//            String s = bufferedReader.readLine();
//            System.out.println(s);
//        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            XWPFDocument document = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphs = document.getParagraphs();

            for (XWPFParagraph paragraph : paragraphs) {
                List<XWPFRun> runs = paragraph.getRuns();
                if (runs != null) {
                    for (XWPFRun run : runs) {
                        if (!Objects.equals("", run.getText(0).trim())) {
                            System.out.println(run.getText(0));
                        }

                    }
                } else {
                    System.out.println(paragraph.getText());
                }
            }

            // 关闭文档
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
