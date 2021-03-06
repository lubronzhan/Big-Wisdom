import java.util.ArrayList;
import java.util.List;

import Utils.*;

public class PeriodTimeSeriesForecasting {

    public List<Float> threePeriodTimesSeriesForecasting(List<Price> pricelist) {
	float f = 0f;
	float avg = 0f;
	List<Float> list = new ArrayList<Float>();
	List<Float> listforecast = new ArrayList<Float>();
	List<Float> avglistforecast = new ArrayList<Float>();
	List<Float> avglist = new ArrayList<Float>();
	float hislow = 0f;
	float hishigh = 0f;
	// easy way
	for (int i = 0; i < pricelist.size(); i++) {
	    avg = 0f;
	    avg = (pricelist.get(i).getEnd_price() + pricelist.get(i)
		    .getStart_price()) / 2;
	    avglist.add(avg);
	    hislow = (hislow > avg) ? avg: hislow;
	    hishigh = (hishigh < avg) ? avg: hishigh;
	}
	
	System.out.println("Size:"+avglist.size());
	int avglistSize = avglist.size(); 
	for (int j = avglist.size() - 1; j < avglistSize + 2; j++) {
	    avg = 0f;
	    for (int k = 1; k < 4; k++) {
		avg += (avglist.get(avglist.size() - k));
	    }
	    
	    avg = avg / 3;
	    avglist.add(avg);
	}
	
	// for (int p = 0; )

	// hard way
	for (int i = 2; i < pricelist.size()-1; i++) {
	    for (int j = i; j > i - 3; j--) {
		f += (float) (pricelist.get(j).getStart_price() + pricelist
			.get(j).getEnd_price()) / 2;
	    }
	    f = f / 3;
	    list.add(f);
	    f = 0f;
	}
	System.out.println(avglist.get(avglist.size()-1));
	return list;
    }

    public List<Float> fivePeriodTimesSeriesForecasting(List<Price> pricelist) {
	float f = 0f;
	float avg = 0f;
	List<Float> list = new ArrayList<Float>();
	List<Float> listforecast = new ArrayList<Float>();
	List<Float> avglistforecast = new ArrayList<Float>();
	List<Float> avglist = new ArrayList<Float>();
	float hislow = 0f;
	float hishigh = 0f;
	// easy way
	for (int i = 0; i < pricelist.size(); i++) {
	    avg = 0f;
	    avg = (pricelist.get(i).getEnd_price() + pricelist.get(i)
		    .getStart_price()) / 2;
	    avglist.add(avg);
	    hislow = (hislow > avg) ? avg: hislow;
	    hishigh = (hishigh < avg) ? avg: hishigh;
	}

	for (int j = avglist.size() - 1; j < avglist.size() + 5; j++) {
	    avg = 0f;
	    for (int k = 1; k < 6; k++) {
		avg += (avglist.get(avglist.size() - k));
	    }
	    avg = avg / 5;
	    avglist.add(avg);
	}
	// for (int p = 0; )

	// hard way
	for (int i = 4; i < pricelist.size(); i++) {

	    // float f = (pricelist.get(i-3).getStart_price() +
	    // pricelist.get(i-2) + pricelist.get(i-1))/3;
	    for (int j = i; j > i - 5; j--) {
		f += (float) (pricelist.get(j).getStart_price() + pricelist
			.get(j).getEnd_price()) / 2;
	    }
	    f = f / 5;
	    list.add(f);
	    f = 0f;
	}
	// list is 30 days average

	return list;
    }

    public List<Float> TenPeriodTimesSeriesForecasting(List<Price> pricelist) {
	float f = 0f;
	float avg = 0f;
	float hislow = 0f;
	float hishigh = 0f;
	List<Float> list = new ArrayList<Float>();
	List<Float> listforecast = new ArrayList<Float>();
	List<Float> avglistforecast = new ArrayList<Float>();
	List<Float> avglist = new ArrayList<Float>();
	// easy way
	for (int i = 0; i < pricelist.size(); i++) {
	    avg = 0f;
	    avg = (pricelist.get(i).getEnd_price() + pricelist.get(i)
		    .getStart_price()) / 2;
	    avglist.add(avg);
	    hislow = (hislow > avg) ? avg: hislow;
	    hishigh = (hishigh < avg) ? avg: hishigh;
	}

	for (int j = avglist.size() - 1; j < avglist.size() + 10; j++) {
	    avg = 0f;
	    for (int k = 1; k < 11; k++) {
		avg += (avglist.get(avglist.size() - k));
	    }
	    avg = avg / 10;
	    avglist.add(avg);
	}
	// for (int p = 0; )

	// hard way
	for (int i = 9; i < pricelist.size(); i++) {

	    // float f = (pricelist.get(i-3).getStart_price() +
	    // pricelist.get(i-2) + pricelist.get(i-1))/3;
	    for (int j = i; j > i - 10; j--) {
		f += (float) (pricelist.get(j).getStart_price() + pricelist
			.get(j).getEnd_price()) / 2;
	    }
	    f = f / 10;
	    list.add(f);
	    f = 0f;
	}
	// list is 30 days average
	return list;
    }
}
