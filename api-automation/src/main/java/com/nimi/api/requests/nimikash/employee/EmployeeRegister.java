package com.nimi.api.requests.nimikash.employee;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EmployeeRegister {
    private List<Map<String, Object>> bulkFields;
}
