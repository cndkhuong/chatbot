package com.vnkotlin.chatbot;

import ai.api.model.Fulfillment;
import ai.api.web.AIWebhookServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet("/webhook")
public class Webhook extends AIWebhookServlet {
    BookingService booking = new BookingService();
    protected void doWebhook(AIWebhookRequest input, Fulfillment output) {
        output.setSpeech(booking.process(input.getResult()));
    }
}
