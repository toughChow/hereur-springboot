package com.toughchow.springbootcommon.utils;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;

public class PDF2Utils {

    private static void pdf2docx() throws IOException {
        XWPFDocument doc = new XWPFDocument();
        String filename = "D:\\tough.pdf";
        String pdf = filename;
        PdfReader reader = new PdfReader(pdf);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            TextExtractionStrategy strategy =
                    parser.processContent(i, new SimpleTextExtractionStrategy());
            String text = strategy.getResultantText();
            XWPFParagraph p = doc.createParagraph();
            XWPFRun run = p.createRun();
            run.setText(text);
            run.addBreak(BreakType.PAGE);
        }
        FileOutputStream out = new FileOutputStream("D://pdf.docx");
        doc.write(out);
// Close all open files
    }

    public static void main(String[] args) throws IOException {
        pdf2docx();
    }
}
