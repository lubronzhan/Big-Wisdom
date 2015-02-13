import java.io.*;
import java.util.ArrayList;

import Utils.*;

/**
 * Created by kanghuang on 2/7/15.
 */
public class HacksonMain {
     static ArrayList<Company> cpList;

    public HacksonMain() {
	cpList = new ArrayList<Company>();
    }

    public void readFile() throws IOException {
	File directory = new File("./");
	FilenameFilter fileNameFilter = new FilenameFilter() {
	    @Override
	    public boolean accept(File dir, String name) {

		return name.matches(".*.dat");
	    }
	};
	File[] files = directory.listFiles(fileNameFilter);
	System.out.println(files.length);
	for (File f : files) {
	    BufferedReader br = new BufferedReader(new FileReader(f));
	    Company cp = new Company();
	    cp.setName(f.getName());
	    br.readLine();
	    while (br.ready()) {
		Price p = new Price();
		String line = br.readLine();
		String[] tokens = line.split(",");
		p.setStart_price(Float.parseFloat(tokens[1]));
		p.setEnd_price(Float.parseFloat(tokens[2]));
		cp.add(p);
	    }
	    cpList.add(cp);
	    br.close();
	}

    }

    public void print() {
	// System.out.println(cpList.size());
	for (Company cp : this.cpList) {
	    System.out.println(cp.getName() + " ");
	    ArrayList<Price> pl = (ArrayList<Price>) cp.getStock_price();
	    for (Price p : pl) {
		System.out.println(p.getStart_price() + " " + p.getEnd_price());
	    }
	    System.out.println("********");
	}
    }

    public static void main(String[] args) throws IOException {
	HacksonMain hm = new HacksonMain();
	hm.readFile();
	hm.print();
	
	PeriodTimeSeriesForecasting pf = new PeriodTimeSeriesForecasting();
	for (Company p : cpList){
	 
	pf.threePeriodTimesSeriesForecasting(  p.getStock_price());
	
	}
    }
}
