import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.multipdf.LayerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;

import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Chris on 31.07.2018
 */
public class Main {


    private static final String SOURCE_DIRECTORY = "test.pdf";
    private static final String TYPE = "se"; // se or gka

    public static void main(String[] args) throws IOException {

        PDFMaker maker = new PDFMaker();

        File fileOrFolder = new File(SOURCE_DIRECTORY);

        if(!fileOrFolder.isFile()){
            File[] listOfFiles = fileOrFolder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {

                if (listOfFiles[i].isFile() && getFileExtension(listOfFiles[i]).equals("pdf")) {
                    System.out.println("File " + listOfFiles[i].getName());
                    maker.makePDF(listOfFiles[i], TYPE);
                }
            }
        } else {

            if (getFileExtension(fileOrFolder).equals("pdf")) {
                System.out.println("File " + fileOrFolder.getName());
                maker.makePDF(fileOrFolder, TYPE);
            }

        }


    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }




}
