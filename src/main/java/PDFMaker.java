import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

/**
 * PDFMaker
 *
 * @author Chris on 31.07.2018
 */
public class PDFMaker {

    public void makePDF(File file, String type) throws IOException {

        PDDocument target = new PDDocument();

        PDDocument source = PDDocument.load(file);
        PDDocument toAdd;

        if(type.equals("se")){
            toAdd = PDDocument.load(new File("se.pdf"));
        } else if (type.equals("gka")){
            toAdd = PDDocument.load(new File("gka.pdf"));
        } else {
            toAdd = PDDocument.load(new File("se.pdf"));
        }

        for(int i = 0; i < source.getNumberOfPages(); i++){
            target.addPage(source.getPage(i));
            target.addPage( toAdd.getPage(0));
        }


        target.save(file);
        toAdd.close();
        source.close();
        target.close();

    }
    
}
