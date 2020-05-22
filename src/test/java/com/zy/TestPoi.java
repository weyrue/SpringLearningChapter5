package com.zy;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TestPoi {
    private final static Logger log = LoggerFactory.getLogger(TestPoi.class);

    @Test
    public void testXWPF() {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream("C:\\Users\\Yi\\Desktop\\软件著作权\\电子商务业务中台应用软件-操作手册（上）(1)(1).docx");
            XWPFDocument document = new XWPFDocument(fileInputStream);

            List<XWPFParagraph> paragraphs = document.getParagraphs();
            XWPFParagraph paragraph = paragraphs.get(3);
            for (XWPFRun run : paragraph.getRuns()) {
                String text = run.getText(0);

                int pos = text.indexOf("中台应用");
                if (pos > 0) {
                    run.setText(text.substring(0, pos) + "123" + text.substring(pos), 0);
                }
            }

            fileOutputStream = new FileOutputStream("C:\\Users\\Yi\\Desktop\\test.docx");
            document.write(fileOutputStream);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileOutputStream != null) fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testHWPF() {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream("C:\\Users\\Yi\\Desktop\\借款合同-LPR修改版（讨论版）-定稿.doc");
            HWPFDocument document = new HWPFDocument(fileInputStream);

            Range range = document.getRange();



            for (int i = 0; i < range.numParagraphs(); i++) {
                Paragraph paragraph = range.getParagraph(i);

                System.out.println(paragraph.text());
            }

            range.replaceText("${ERP}", "1234567");

//            List<XWPFParagraph> paragraphs = document.getParagraphs();
//            XWPFParagraph paragraph = paragraphs.get(3);
//            for (XWPFRun run : paragraph.getRuns()) {
//                String text = run.getText(0);
//
//                int pos = text.indexOf("中台应用");
//                if (pos > 0) {
//                    run.setText(text.substring(0, pos) + "123" + text.substring(pos), 0);
//                }
//            }

            fileOutputStream = new FileOutputStream("C:\\Users\\Yi\\Desktop\\test.doc");
            document.write(fileOutputStream);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileOutputStream != null) fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void testBigDecimal(){
        BigDecimal bigDecimal = new BigDecimal("147.0001");
        long longValue = bigDecimal.longValue();
        System.out.println(longValue);

    }
}
