/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.model;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;
import java.util.Random;

/**
 *
 * @author Asus
 */
public class ForgotPassword {

    private static final String SENDER_EMAIL = "strklighting@gmail.com";
//          private static final String SENDER_EMAIL = "";

    private static final String SENDER_PASSWORD = "hphq tkbz atwm ffxj";

    public static void sendOTPEmail(String toEmail, String otp) {
        String subject = "Password Reset OTP";
        String message = "Your OTP for password reset is: " + otp;

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.ssl.trust", "*");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
//                properties.setProperty("mail.smtp.Oauth", "true");

        properties.setProperty("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(SENDER_EMAIL));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);
            System.out.println("OTP sent successfully to " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static String generateOTP() {
        int otpLength = 6;
        Random random = new Random();
        StringBuilder otp = new StringBuilder(otpLength);
        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    public static void main(String[] args) {
        String userEmail = "kienlhpc05751@fpt.edu.vn";
        String generatedOTP = generateOTP();

        // Gửi mã OTP qua email
        sendOTPEmail(userEmail, generatedOTP);
    }

}
