package lsdi.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ContextData {
    private String hostUuid;
    Performace performace;
    Location location;
}
