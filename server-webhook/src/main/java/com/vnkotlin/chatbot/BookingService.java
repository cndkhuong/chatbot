package com.vnkotlin.chatbot;

import ai.api.model.Result;
import com.sun.javafx.binding.StringFormatter;

public class BookingService {
    public String process(Result result){
        if("order".equals(result.getAction()))
            return order(result.getStringParameter("beverage"));
        if("number".equals(result.getAction())) {
            String order = result.getContext("order-ctx").getParameters().get("beverage").getAsString();
            String number =  result.getStringParameter("number");
            long price = price(order)*Integer.parseInt(number) ;
            return num(order,number, price);
        }
        if("confirm".equals(result.getAction())) {
            return confirm(result.getStringParameter("confirm"));
        }
        else
            return null;
    }

    public String order(String data){
        long price = price(data);
        return StringFormatter.format("%s price is %d$. How many %s do you want ?", data, price, data).getValue();
    }

    private long price(String data){
        long price = 0;

        if("coffee".equalsIgnoreCase(data)) price = 5;
        if("tea".equalsIgnoreCase(data)) price = 4;
        if("beer".equalsIgnoreCase(data)) price = 6;

        return price;
    }

    public String num(String order, String number, long price){
        return StringFormatter.format("Your order : %d x %s = %d$? Corrected?", Integer.parseInt(number), order, price).getValue();
    }

    public String confirm(String confirm){
        if("y".equalsIgnoreCase(confirm))
            return "Order confirmed! Thank you";
        else if("n".equalsIgnoreCase(confirm))
            return "Oh no! Bye bye";
        else return "Okay! Bye bye";
    }
}
