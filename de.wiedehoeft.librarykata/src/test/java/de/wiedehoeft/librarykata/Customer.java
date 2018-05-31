package de.wiedehoeft.librarykata;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Customer {

    private static final double BASE_PRICE = 3.00;
    private static final int DAYS_DISCOUNTED = 3;
    private static final double ADDITIIONAL_PRICE = 1.50;
    private double totalAmount;
    private List<Movie> rentals;

    public Customer() {
        this.rentals = new ArrayList<Movie>();
    }

    public double rentMovie(int days) {
        double movieAmount = BASE_PRICE;

        if (days > DAYS_DISCOUNTED) {
            movieAmount += ADDITIIONAL_PRICE * (days - DAYS_DISCOUNTED);
        }

        totalAmount += movieAmount;
        return movieAmount;
    }

    public String printRental() {
        StringBuilder rental = new StringBuilder();
        this.rentals.forEach(movie -> {
            rental.append(movie.getTitle());
            rental.append(":");
            rental.append(prettyPrint(movie.getAmount()));
            rental.append("\n");
        });
        return rental.toString() +
                "Total Charge:" + prettyPrint(totalAmount);
    }

    public String prettyPrint(double amount) {
        final NumberFormat numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
        numberFormat.setMinimumFractionDigits(2);
        return " EUR " + numberFormat.format(amount);
    }

    public void rentMovie(Movie movie, int days) {
        this.rentals.add(movie);
        final double amount = rentMovie(days);
        movie.setAmount(amount);
    }
}
