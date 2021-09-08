package eu.koboo.pdfsplitter;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class PDFSplitter {

    public static void main(String[] args) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        int ret = fileChooser.showOpenDialog(null);
        if(ret == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if(file.getName().endsWith(".pdf")) {
                PDDocument document = PDDocument.load(file);
                Splitter splitter = new Splitter();
                List<PDDocument> pageList = splitter.split(document);
                Iterator<PDDocument> pageIterator = pageList.listIterator();
                int i = 0;
                while(pageIterator.hasNext()) {
                    i++;
                    PDDocument seperateDocument = pageIterator.next();
                    Path path = Paths.get(file.getPath());
                    seperateDocument.save(path.getParent().toString() + "\\" + file.getName().split(".pdf")[0] + i + ".pdf");
                }
                document.close();
            }
        }

    }
}
