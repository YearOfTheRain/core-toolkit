package cn.year.coretoolkit.tesseract;

/**
 * @author YearOfTheRain
 * @create 2024-03-05  10:07
 */
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
// 安装 file下面的应用   window版本的 安装过程中还需要选择额外安装 中文解析包
// 安装完成后就可以直接使用了
public class OCRExtractor {
    public static void main(String[] args) {
        // 图片文件路径
        String imagePath = "D:\\pic\\img4.jpg";

        try {
            // 初始化Tesseract实例并设置tessdata路径
            ITesseract instance = new Tesseract();
            instance.setDatapath("D:\\Program Files\\Tesseract-OCR\\tessdata");

            // 设置识别语言
            instance.setLanguage("chi_sim");

            // 执行OCR识别
            String result = instance.doOCR(new File(imagePath));

            // 输出识别结果
            System.out.println(result);

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
