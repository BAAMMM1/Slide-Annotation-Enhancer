package backup.model_old;

import java.io.File;
import java.io.IOException;

/**
 * @author Chris on 31.07.2018
 */
public class Main2 {


    private static final String SOURCE_DIRECTORY = "D:\\hawCloud\\HAW Unterlagen\\13 Semester\\Intelligente Systeme\\1. Folien\\IS-03 - Logik.pdf";
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
