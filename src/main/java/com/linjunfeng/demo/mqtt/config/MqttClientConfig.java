package com.linjunfeng.demo.mqtt.config;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

@Component
public class MqttClientConfig {

    public static int qos = 1;
    private static String broker = "tcp://iot.eclipse.org";
    private static String userName = "admin";
    private static String password = "admin";
    private static MqttConnectOptions connectOptions;
    private static MqttClient mqttClient;

    MqttClientConfig() {
        connectOptions = new MqttConnectOptions();
        connectOptions.setCleanSession(true);
        connectOptions.setUserName(userName);
        connectOptions.setPassword(password.toCharArray());
        connectOptions.setConnectionTimeout(0);
        connectOptions.setKeepAliveInterval(20);
        connectOptions.setAutomaticReconnect(true);
    }

    public static MqttClient connect(String clientId) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        mqttClient = new MqttClient(broker, clientId, persistence);
        mqttClient.setCallback(new PushCallback(mqttClient));
        mqttClient.connect(connectOptions);
        return mqttClient;
    }

    private static class PushCallback implements MqttCallback {

        private MqttClient mqttClient;

        PushCallback(MqttClient mqttClient) {
            this.mqttClient = mqttClient;
        }

        @Override
        public void connectionLost(Throwable throwable) {
            // 连接丢失后，一般在这里面进行重连
            System.out.println("掉线啦=========================" + mqttClient.getClientId());
            try {
                System.out.println("重连中=========================");
                mqttClient.reconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
            // subscribe后得到的消息会执行到这里面
            System.out.println("接收消息主题 : " + topic);
            System.out.println("接收消息Qos : " + mqttMessage.getQos());
            System.out.println("接收消息内容 : " + new String(mqttMessage.getPayload()));
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            System.out.println("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
        }
    }
}
