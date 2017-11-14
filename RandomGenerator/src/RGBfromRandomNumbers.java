// created by Aashi Tiwari
//out of 3 requirement I have chosen first i.e Using random numbers create An RGB bitmap picture of 128x128 pixels.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class RGBfromRandomNumbers {
	// connecting from random.org to get truly random numbers and storing in list of integers.
	public static List<Integer> randomNumberGenerator() throws IOException{
		URL random_org = new URL("https://www.random.org/integers/?num=256&min=0&max=255&col=1&base=10&format=plain&rnd=new");
		HttpURLConnection myConnection =  (HttpURLConnection) random_org.openConnection();
		myConnection.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(random_org.openStream()));
		String inputline;
		List<Integer> random_no = new ArrayList<Integer>();
		
		while((inputline = in.readLine()) != null)
			random_no.add(Integer.parseInt(inputline));
		in.close();
		return random_no;
	}
	
	
	//Creating RGB bitmap of size 128*128 using java libraries and getting random numbers form list
	public static void ImageCreation(List<Integer> random_no,int height , int width) throws IOException{
		
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Random r = new Random();
		Color color;
		int count = 0;
		for(int i = 0; i < width; i++){
		    for(int j = 0; j < height; j++){
		    	 if(count == 255) {
		    		 count = 0;
		    	 }
		         int red = random_no.get(r.nextInt(random_no.size()));
		         int green = random_no.get(r.nextInt(random_no.size()));
		         int blue = random_no.get(r.nextInt(random_no.size()));
		        
		         color = new Color(red, green, blue);
		         img.setRGB(i, j, color.getRGB());
			 }
		}
		File f = new File("Output.png");
		 try{
		       ImageIO.write(img, "png", f);
		     }catch(IOException e){
		       System.out.println("Error: " + e);
		     }
		
		
	}
	public static void main(String[] args) throws IOException {
		
		int width = 128;
		int height = 128;
		List<Integer> random_no = randomNumberGenerator();
		ImageCreation(random_no, height, width);
		
	}

}
