package dev.graumann.slideannotationenhancer.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * backup.model_old.PDFMaker
 *
 * @author Chris on 31.07.2018
 */
public class PDFMixer {

    public static final String PATH_SLIDE_LINED = "/dev/graumann/slideannotationenhancer/slides/lined.pdf";
    public static final String PATH_SLIDE_SQUARED = "/dev/graumann/slideannotationenhancer/slides/squared.pdf";

    private List<Slide> slides;

    public PDFMixer() {
        this.slides = new ArrayList<>();
        Slide lined = new Slide("lined", PATH_SLIDE_LINED);
        Slide squared = new Slide("sqaured", PATH_SLIDE_SQUARED);
        slides.add(lined);
        slides.add(squared);
    }

    public boolean makePDF(Slide anotationSlideType, ArrayList<File> files) {
        if(files.isEmpty()) return false;

        try {

            

            for (File file : files) {
                PDDocument target = new PDDocument();
                PDDocument source = PDDocument.load(file);

                for (int i = 0; i < source.getNumberOfPages(); i++) {
                    PDPage anotationPage = PDDocument.load(this.getClass().getResourceAsStream(anotationSlideType.getPath())).getPage(0);
                    
                    target.addPage(source.getPage(i));
                    target.addPage(anotationPage);
                }

                target.save(new File(
                    file.getParent() + System.getProperty("file.separator") + file.getName().substring(0, file.getName().indexOf(".pdf")) + "_" + anotationSlideType.getName() + ".pdf"));
                source.close();
                target.close();
            }


        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public List<Slide> getSlides() {
        return slides;
    }
}
