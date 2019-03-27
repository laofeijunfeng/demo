package com.linjunfeng.demo.mqtt.utils;

import com.linjunfeng.demo.mqtt.config.MqttClientConfig;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

@Component
public class MqttUtil {

    public static void subscribe(String clientId, String topic) throws MqttException {
        MqttClient mqttClient = MqttClientConfig.connect(clientId);
        if (mqttClient.isConnected()) {
            mqttClient.subscribe(topic, MqttClientConfig.qos, (t, m) -> {
                String msg = new String(m.getPayload());
                System.out.println("收到来自 topic：" + topic + " 的消息：" + msg);
            });
        }
    }

    public static void publish(String msg, String clientId, String topic) throws MqttException {
        MqttClient mqttClient = MqttClientConfig.connect(clientId);
        if (mqttClient.isConnected()) {
            MqttMessage message = new MqttMessage(msg.getBytes());
            message.setQos(MqttClientConfig.qos);
            message.setRetained(false);
            mqttClient.publish(topic, message);
            System.out.println("向 topic：" + topic + " 推送消息：" + msg);
        }
        mqttClient.disconnect();
    }

    public static void main(String[] args) {
        try {
            subscribe("000", "test/laofeijunfeng/#");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
