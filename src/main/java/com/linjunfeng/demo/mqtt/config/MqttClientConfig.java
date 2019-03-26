package com.linjunfeng.demo.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.stereotype.Component;

@Component
public class MqttClientConfig {
    public static int qos = 1;
    private static String broker = "tcp://47.94.195.156:1883";
    private static String userName = "links2all";
    private static String password = "a5I2bsRsfP1kpgaQ";
    private static MqttConnectOptions connectOptions;
    private static MqttClient mqttClient;
}
