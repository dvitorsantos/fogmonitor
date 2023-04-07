package lsdi.Publishers;

import lsdi.Models.ContextData;
import lsdi.Services.MqttService;

public class ContextDataPublisher {
    MqttService mqttService = MqttService.getInstance();
    public void publishContextData(ContextData contextData) {
        byte[] payload = contextData.toString().getBytes();
        mqttService.publish("context-data", payload);
    }
}
