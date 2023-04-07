package lsdi.Services;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttService {
    private static MqttService instance;
    private MqttConnectOptions options;
    private MqttClient client;
    private String mosquittoUrl = System.getenv("mosquitto.url");
    private String clientUuid = System.getenv("mosquitto.clientid");

    private MqttService() {
        options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(30000);
        options.setKeepAliveInterval(30);

        try {
            client = new MqttClient("tcp://mosquitto:1883", "edgemonitor");
            client.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static MqttService getInstance() {
        if (instance == null) instance = new MqttService();
        return instance;
    }

    public void publish(String topic, byte[] payload) {
        try {
            client.publish(topic, payload, 0, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String topic, IMqttMessageListener handler) {
        try {
            client.subscribe(topic, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
