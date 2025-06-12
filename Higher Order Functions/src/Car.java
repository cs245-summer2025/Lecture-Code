import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Car implements Comparable<Car>{

    private int price;
    private String model;
    private int mpg;

    public static class MilesDrivenComparator implements Comparator<Car> {
        public static double milesToRecoverDelta(Car c1) {
            /*
                Logic: Suppose we have a car c1 and comparing it to a baseline
                car (baseline)

                Let's assume c1 gets 50 miles/gallon, the baseline car gets 28 mpg
                and gas costs $4/gallon. Let's assume the price of c1 is P1
                and the price of the baseline car is B1 where P1 > B1.

                Now, to drive 50 miles in c1 we require 1 gallon and spend $4.
                To drive 50 miles in the baseline car, we spend an additional $((50-28)/28) * 4 = $3.14.
                That means for every 50 miles we drive, we save $3.14 if we choose c1. In other words,
                we need to drive 50/3.14 = 15.9 miles to save $1 in gas cost if we choose c1 over the baseline car.
                The total number of miles we need to drive to recover the delta in car prices in the form of
                gas savings is then (P1 - B1) * 50/3.14.
             */

            int priceDelta = c1.getCost() - Constants.BASE_CAR_PRICE;
            double costOfExtraGas = ((c1.getMileage() - Constants.BASE_CAR_MPG)/Constants.BASE_CAR_MPG) * Constants.GAS_PRICE;
            double milesToRecoverDollar = c1.getMileage() / costOfExtraGas;
            return priceDelta * milesToRecoverDollar;
        }

        public int compare(Car c1, Car c2) {
            double milesRequiredC1 = milesToRecoverDelta(c1);
            double milesRequiredC2 = milesToRecoverDelta(c2);
            if(milesRequiredC1 < milesRequiredC2) {
                return -1;
            } else if(milesRequiredC1 > milesRequiredC2) {
                return 1;
            }
            return 0;
        }
    }

    public Car(String model, int price, int efficiency) {
        this.model = model;
        this.price = price;
        this.mpg = efficiency;
    }

    public int compareTo(Car other) {
        if(this.price < other.price) {
            return -1;
        } else if(this.price == other.price) {
            return 0;
        }
        return 1;
    }

    public int getMileage(){
        return mpg;
    }

    public int getCost() {
        return price;
    }

    public String toString() {
        return model;
    }

    public static void main(String[] args) {
        Car prius = new Hybrid("prius", 30000, 52);
        Car accord = new Hybrid("accord", 29000, 46);
        Car crv = new Hybrid("crv", 36000, 43);
        Car audi = new Hybrid("audi q5", 68000, 29);

        Car[] cars = {audi, prius, crv, accord};
        Arrays.sort(cars);
        System.out.println(Arrays.toString(cars));

        Arrays.sort(cars, new Car.MilesDrivenComparator());
        System.out.println(Arrays.toString(cars));
    }
}
