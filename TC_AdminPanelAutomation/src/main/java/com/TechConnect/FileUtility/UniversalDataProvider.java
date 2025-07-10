package com.TechConnect.FileUtility;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

public class UniversalDataProvider {
	@DataProvider(name = "dynamicData")
    public Object[][] provideData(Method method) {
        List<Object[]> data = new ArrayList<>();
        SheetName sheetName = method.getAnnotation(SheetName.class);

        if (sheetName == null) {
            throw new RuntimeException("Missing @SheetName annotation on test method: " + method.getName());
        }

        String keyPrefix = sheetName.value(); // e.g., "AddSponsor", "Workshop"
        for (int i = 1; ; i++) {
            String key = keyPrefix + i;
            try {
                String value = GetPropertyData.propData(key);
                if (value == null || value.trim().isEmpty()) break;
                String[] values = value.split("~", -1);
                data.add(values);
            } catch (Exception e) {
                break; // Stop when key doesn't exist
            }
        }

        return data.toArray(new Object[0][]);
    }
}
