package org.example.notificationservice;

import org.springframework.stereotype.Component;

@Component
public class WhatsappService implements NotificationService {
    @Override
    public void SendMsg(String msg) {
        System.out.println("WhatsApp Service"+msg);
    }
}
