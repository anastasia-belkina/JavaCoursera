import edu.duke.*;
import java.io.*;

public class NagativeScaleConverter {
    public static void main (String[] args) {
		NagativeScaleConverter p = new NagativeScaleConverter();
		p.selectAndConvert();
	}

	public void selectAndConvert () {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource neg = makeNeg(inImage);
			String fname = inImage.getFileName();
			String newName = "inverted-" + fname;
			neg.setFileName(newName);
			neg.draw();
			neg.save();
		}
	}

	//I started with the image I wanted (inImage)
	public ImageResource makeNeg(ImageResource inImage) {
		//I made a blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//for each pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//set pixel's red to 255 - Red
			pixel.setRed(255-inPixel.getRed());
			//set pixel's green to 255 - Green
			pixel.setGreen(255-inPixel.getGreen());
			//set pixel's blue to 255 - Blue
			pixel.setBlue(255-inPixel.getBlue());
		}
		//outImage is your answer
		return outImage;
	}
}