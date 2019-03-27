package com.linjunfeng.demo.mqtt;

import com.linjunfeng.demo.mqtt.utils.MqttUtil;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {
    public static void main(String[] args) {
        try {
            MqttUtil.publish("test message!", "001", "test/laofeijunfeng/001/test");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
