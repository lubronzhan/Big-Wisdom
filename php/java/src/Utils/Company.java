package Utils;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private String name;

    private int day;

    private List<Price> stock_price;

    private List<Float> MA3;

    private List<Float> MA5;

    private List<Float> MA10;

    private boolean upwards;

    private int totalscore;

    private int score1;

    private int score2;

    private float lastdayprice;

    private float lastdayMA5;

    private float lastdayMA10;
    public Company(){
	stock_price = new ArrayList<Price>();
}

    public float getLastdayprice() {

	return lastdayprice;

    }

    public void setLastdayprice(float lastdayprice) {

	this.lastdayprice = lastdayprice;

    }

    public float getLastdayMA5() {

	return lastdayMA5;

    }

    public void setLastdayMA5(float lastdayMA5) {

	this.lastdayMA5 = lastdayMA5;

    }

    public float getLastdayMA10() {

	return lastdayMA10;

    }

    public void setLastdayMA10(float lastdayMA10) {

	this.lastdayMA10 = lastdayMA10;

    }

    public boolean isUpwards() {

	return upwards;

    }

    public void setUpwards(boolean upwards) {

	this.upwards = upwards;

    }

    public List<Float> getMA3() {

	return MA3;

    }

    public void setMA3(List<Float> mA3) {

	MA3 = mA3;

    }

    public List<Float> getMA5() {

	return MA5;

    }

    public void setMA5(List<Float> mA5) {

	MA5 = mA5;

    }

    public List<Float> getMA10() {

	return MA10;

    }

    public void setMA10(List<Float> mA10) {

	MA10 = mA10;

    }

    public int getTotalscore() {

	return totalscore;

    }

    public void setTotalscore(int totalscore) {

	this.totalscore = totalscore;

    }

    public int getScore1() {

	return score1;

    }

    public void setScore1(int score1) {

	this.score1 = score1;

    }

    public int getScore2() {

	return score2;

    }

    public void setScore2(int score2) {

	this.score2 = score2;

    }

    public String getName() {

	return name;

    }

    public void setName(String name) {

	this.name = name;

    }

    public int getDay() {

	return day;

    }

    public void setDay(int day) {

	this.day = day;

    }

    public List<Price> getStock_price() {

	return stock_price;

    }

    public void setStock_price(List<Price> stock_price) {

	this.stock_price = stock_price;

    }

    public void add(Price p) {
	stock_price.add(p);
    }

}
