package strosoft.app.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultDefaultValueProcessor;
import org.apache.poi.ss.formula.functions.T;

public class JsonHelper {
    /**
     * 取得String类型属性值（参数可多个)
     */
    public static String getString(JSONObject jData, String... keys) {
        for (String key : keys) {
            if (jData.containsKey(key)) {
                String strValue = jData.getString(key);
                return strValue;
            }
        }
        return null;
    }

    /**
     * 取得用在语句中的String类型属性值（参数可多个)
     */
    public static String getSqlString(JSONObject jData, String... keys) {
        for (String key : keys) {
            if (jData.containsKey(key)) {
                String strValue = jData.getString(key);
                if (strValue == null) {
                    return null;
                }
                if (strValue.contains("'")) {
                    strValue = strValue.replace("'", "''");
                }
                return strValue;
            }
        }
        return null;
    }

    /**
     * 取得Int类型属性值（参数可多个)
     */
    public static Integer getInt(JSONObject jData, String... keys) {
        for (String key : keys) {
            if (jData.containsKey(key)) {
                try {
                    return jData.getInt(key);
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * 取得Long类型属性值（参数可多个)
     */
    public static Long getLong(JSONObject jData, String... keys) {
        for (String key : keys) {
            if (jData.containsKey(key)) {
                try {
                    return jData.getLong(key);
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * 取得Decimal类型属性值（参数可多个)
     */
    public static BigDecimal getDecimal(JSONObject jData, String... keys) {
        for (String key : keys) {
            if (jData.containsKey(key)) {
                try {
                    String strValue = jData.getString(key);
                    BigDecimal decValue = new BigDecimal(strValue);
                    return decValue;
                } catch (Exception e) {
                    return BigDecimal.ZERO;
                }
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * 取得BigInteger类型属性值（参数可多个)
     */
    public static BigInteger getBigInteger(JSONObject jData, String... keys) {
        for (String key : keys) {
            if (jData.containsKey(key)) {
                try {
                    String srtValue = jData.getString(key);
                    BigInteger biValue = new BigInteger(srtValue);
                    return biValue;
                } catch (Exception e) {
                    return BigInteger.ZERO;
                }
            }
        }
        return BigInteger.ZERO;
    }

    /**
     * 取得Date类型属性值（可多个)
     */
    public static Date getDateValue(JSONObject jData, String... keys) {
        for (String key : keys) {
            if (jData.containsKey(key)) {
                String strValue = jData.getString(key);
                return DateHelper.toDate(strValue);
            }
        }
        return null;
    }

    /**
     * 取得Date类型属性值（可多个)
     */
    public static Date getDate(JSONObject jData, String... keys) {
        for (String key : keys) {
            if (jData.containsKey(key)) {
                String strValue = jData.getString(key);
                return DateHelper.toDate(strValue);
            }
        }
        return null;
    }

    /**
     * 取得Timestamp类型属性值（可多个)
     */
    public static Timestamp getTimestampValue(JSONObject jData, String... keys) {
        for (String key : keys) {
            if (jData.containsKey(key)) {
                String strValue = jData.getString(key);
                return DateHelper.toTimestamp(strValue);
            }
        }
        return null;
    }

    /**
     * 取得Timestamp类型属性值（可多个)
     */
    public static Timestamp getTimestamp(JSONObject jData, String... keys) {
        for (String key : keys) {
            if (jData.containsKey(key)) {
                String strValue = jData.getString(key);
                return DateHelper.toTimestamp(strValue);
            }
        }
        return null;
    }

    /**
     * 取得整数列表类型属性值（可多个）
     */
    @SuppressWarnings("unchecked")
    public static List<Integer> getIntArray(JSONObject jData, String... keys) {

        for (String key : keys) {
            if (jData.containsKey(key)) {
                JSONArray jarrValue = jData.getJSONArray(key);
                List<Integer> lstValue = new ArrayList<Integer>();
                for (int i = 0; i < jarrValue.size(); i++) {
                    Integer intValue = Integer.parseInt(jarrValue.getString(i));
                    lstValue.add(intValue);
                }
                return lstValue;
            }
        }
        return null;
    }

    /**
     * 取得字符串列表类型属性值（可多个）
     */
    @SuppressWarnings("unchecked")
    public static List<String> getStringArray(JSONObject jData, String... keys) {

        for (String key : keys) {
            if (jData.containsKey(key)) {
                Object value = jData.get(key);
                if (value instanceof JSONArray) {
                    JSONArray jarrValue = jData.getJSONArray(key);
                    List<String> lstValue = jarrValue;
                    return lstValue;
                }
            }
        }
        return null;
    }

    /**
     * 取得Object类型属性值（可多个)
     */
    public static Object getObject(JSONObject jData, String... keys) {
        for (String key : keys) {
            if (jData.containsKey(key)) {
                return jData.get(key);
            }
        }
        return null;
    }

    /**
     * 取得Boolean类型属性值（参数可多个)
     */
    public static Boolean getBoolean(JSONObject jData, String... keys) {
        for (String key : keys) {
            if (jData.containsKey(key)) {
                try {
                    return jData.getBoolean(key);
                } catch (Exception e) {
                    try {
                        String strValue = jData.getString(key).toLowerCase();
                        if (strValue.equals("1") || strValue.equals("yes") || strValue.equals("on")
                                || strValue.equals("true")) {
                            return true;
                        } else {
                            return false;
                        }
                    } catch (Exception exBoolean) {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 取得JSONArray对象
     *
     * @param jData
     * @param keys
     * @return
     */
    public static JSONArray getJSONArray(JSONObject jData, String... keys) {
        for (String key : keys) {
            if (jData.containsKey(key)) {
                return jData.getJSONArray(key);
            }
        }
        return null;
    }

    /**
     * 取得JSONArray对象
     *
     * @param jData
     * @param keys
     * @return
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> List<T> getJavaList(Class targetClass, JSONObject jData, String... keys)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        JSONArray jarrData = getJSONArray(jData, keys);
        return toJavaList(targetClass, jarrData);
    }

    /**
     * 取得JSON对象
     *
     * @param jData
     * @param keys
     * @return
     */
    public static JSONObject getJSONObject(JSONObject jData, String... keys) {
        for (String key : keys) {
            if (jData.containsKey(key)) {
                return jData.getJSONObject(key);
            }
        }
        return null;
    }

    /**
     * 取得Java对象
     *
     * @param targetClass
     * @param jData
     * @param keys
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static <T> T getJavaObject(Class targetClass, JSONObject jData, String... keys)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        JSONObject jObject = getJSONObject(jData, keys);
        return toJavaObject(targetClass, jObject);
    }

    public static JsonConfig getJsonConfig() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerDefaultValueProcessor(Double.class, new DefaultDefaultValueProcessor() {
            @Override
            public Object getDefaultValue(Class type) {
                return "";
            }
        });
        jsonConfig.registerDefaultValueProcessor(Integer.class, new DefaultDefaultValueProcessor() {
            @Override
            public Object getDefaultValue(Class type) {
                return "";
            }
        });
        jsonConfig.registerDefaultValueProcessor(BigDecimal.class, new DefaultDefaultValueProcessor() {
            @Override
            public Object getDefaultValue(Class type) {
                return "";
            }
        });
        jsonConfig.registerDefaultValueProcessor(Float.class, new DefaultDefaultValueProcessor() {
            @Override
            public Object getDefaultValue(Class type) {
                return "";
            }
        });
        jsonConfig.registerDefaultValueProcessor(Long.class, new DefaultDefaultValueProcessor() {
            @Override
            public Object getDefaultValue(Class type) {
                return "";
            }
        });
        jsonConfig.registerDefaultValueProcessor(BigInteger.class, new DefaultDefaultValueProcessor() {
            @Override
            public Object getDefaultValue(Class type) {
                return "";
            }
        });
        jsonConfig.registerDefaultValueProcessor(Boolean.class, new DefaultDefaultValueProcessor() {
            @Override
            public Object getDefaultValue(Class type) {
                return null;
            }
        });
        return jsonConfig;
    }

    public static JSONArray toJsonArray(Object object) {
        return JSONArray.fromObject(object, getJsonConfig());
    }

    public static JSONObject toJsonObject(Object object) {
        return JSONObject.fromObject(object, getJsonConfig());
    }

    /**
     * 从JSONObject转换
     *
     * @param targetClass
     * @param jData
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static <T> T toJavaObject(Class targetClass, JSONObject jData)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object objTarget = targetClass.newInstance();
        Method[] arrTargetMethod = ClassHelper.getAllMethods(targetClass);
        for (Method currentMethod : arrTargetMethod) {
            String methodName = currentMethod.getName();
            // 跳过非set方法
            if (!methodName.startsWith("set")) {
                continue;
            }
            Parameter[] parameters = currentMethod.getParameters();
            // 跳过不是一个参数的方法
            if (parameters.length != 1) {
                continue;
            }
            Parameter theParameter = parameters[0];
            Type parameterType = theParameter.getParameterizedType();

            String parameterName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);

            Iterator iterator = jData.keys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                if (!key.equals(parameterName)) {
                    continue;
                }
                Object objValue = jData.get(key);
                String parameterTypeName = parameterType.getTypeName();
                if (parameterTypeName.equals("java.math.BigDecimal")) {
                    objValue = JsonHelper.getDecimal(jData, key);
                } else if (parameterTypeName.equals("java.sql.Timestamp")) {
                    objValue = JsonHelper.getTimestamp(jData, key);
                } else if (parameterTypeName.equals("java.lang.Boolean")) {
                    objValue = JsonHelper.getBoolean(jData, key);
                } else if (parameterTypeName.equals("java.math.BigInteger")) {
                    objValue = JsonHelper.getBigInteger(jData, key);
                }
                try {
                    String valueClassName = objValue.getClass().getName();
                    if (!valueClassName.equals("net.sf.json.JSONNull")) {
                        currentMethod.invoke(objTarget, objValue);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return (T) objTarget;
    }

    /**
     * JSONArray转对象列表
     *
     * @param targetClass
     * @param jarrData
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static <T> List<T> toJavaList(Class targetClass, JSONArray jarrData)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        List<T> lstData = new ArrayList<T>();
        for (int i = 0; i < jarrData.size(); i++) {
            JSONObject jDataItem = jarrData.getJSONObject(i);
            T newProductReview = JsonHelper.toJavaObject(targetClass, jDataItem);
            lstData.add(newProductReview);
        }
        return lstData;
    }

    /**
     * LinkedHashMapl转JSONObject
     *
     * @param lhmData
     * @return
     */
    public static JSONObject toJSONObject(LinkedHashMap<String, Object> lhmData) {
        JSONObject jData = new JSONObject();
        for (String key : lhmData.keySet()) {
            String name = StringHelper.toCamelCase(key);
            Object objValue = lhmData.get(key);
            jData.put(name, objValue);
        }
        return jData;
    }

    /**
     * LinkedHashMapl转FastJSONObject
     *
     * @param lhmData
     * @return
     */
    public static com.alibaba.fastjson2.JSONObject toFastJSONObject(LinkedHashMap<String, Object> lhmData) {
        return toFastJSONObject(lhmData, null);
    }

    /**
     * LinkedHashMapl转FastJSONObject
     *
     * @param lhmData
     * @return
     */
    public static com.alibaba.fastjson2.JSONObject toFastJSONObject(LinkedHashMap<String, Object> lhmData,
                                                                    String getPropertyNameType) {
        com.alibaba.fastjson2.JSONObject jData = new com.alibaba.fastjson2.JSONObject();
        for (String key : lhmData.keySet()) {
            String name = key;
            String camelName = StringHelper.toCamelCase(key);
            Object objValue = lhmData.get(key);
            if (StringHelper.isNullOrEmpty(getPropertyNameType)) {
                getPropertyNameType = "camel";
            }
            if (getPropertyNameType.equals("camel")) {
                // 使用驼峰命名
                jData.put(camelName, objValue);
            } else if (getPropertyNameType.equals("raw")) {
                // 使用原始命名
                jData.put(name, objValue);
            } else {
                // 原始命名和驼峰命名都添加
                jData.put(name, objValue);
                if (!jData.containsKey(camelName)) {
                    jData.put(camelName, objValue);
                }
            }
        }
        return jData;
    }

    /**
     * 转成FastJSON数组
     *
     * @param alData
     * @return
     */
    public static com.alibaba.fastjson2.JSONArray toFastJSONArray(ArrayList<LinkedHashMap<String, Object>> alData) {
        return toFastJSONArray(alData, null);
    }

    /**
     * 对象转fastJson对象
     *
     * @param objSource
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static com.alibaba.fastjson2.JSONObject toFastJSONObject(Object objSource)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (objSource == null) {
            return null;
        }
        com.alibaba.fastjson2.JSONObject jResult = new com.alibaba.fastjson2.JSONObject();
        Class sourceClass = objSource.getClass();
        Field[] arrField = sourceClass.getDeclaredFields();

        Method[] arrMethod = ClassHelper.getAllMethods(sourceClass);
        for (Field currentField : arrField) {
            String fieldName = currentField.getName();
            String getMethodName = "get" + StringHelper.toPascal(fieldName);
            Method getMethod = getMethod(arrMethod, getMethodName);
            if (getMethod == null) {
                continue;
            }
            try {
                Object objValue = getMethod.invoke(objSource);
                jResult.put(fieldName, objValue);
                String typeName = currentField.getType().getName();
                System.out.println(typeName);
                if (typeName.equals("java.sql.Timestamp")) {
                    jResult.put(fieldName + "Text", DateHelper.format((Timestamp) objValue, "yyyy-MM-dd HH:mm:ss"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jResult;
    }

    /**
     * 根据方法名称取得方法
     *
     * @param arrMethod
     * @param getMethodName
     * @return
     */
    public static Method getMethod(Method[] arrMethod, String getMethodName) {
        for (Method currentMethod : arrMethod) {
            if (currentMethod.getName().equals(getMethodName)) {
                return currentMethod;
            }
        }
        return null;
    }

    /**
     * 转成FastJSON数组
     *
     * @param alData
     * @return
     */
    public static com.alibaba.fastjson2.JSONArray toFastJSONArray(ArrayList<LinkedHashMap<String, Object>> alData,
                                                                  String getPropertyNameType) {
        com.alibaba.fastjson2.JSONArray jarrData = new com.alibaba.fastjson2.JSONArray();
        for (int i = 0; i < alData.size(); i++) {
            LinkedHashMap<String, Object> lhmDataItem = alData.get(i);
            com.alibaba.fastjson2.JSONObject jDataItem = toFastJSONObject(lhmDataItem, getPropertyNameType);
            jarrData.add(jDataItem);
        }
        return jarrData;
    }

    public static JSONArray toJSONArray(ArrayList<LinkedHashMap<String, Object>> alData) {
        JSONArray jarrData = new JSONArray();
        for (int i = 0; i < alData.size(); i++) {
            LinkedHashMap<String, Object> lhmDataItem = alData.get(i);
            JSONObject jDataItem = toJSONObject(lhmDataItem);
            jarrData.add(jDataItem);
        }
        return jarrData;
    }

    /**
     * LinkedHashMap转Java对象
     *
     * @param targetClass
     * @param lhmDataItem
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static <T> T toJavaObject(Class targetClass, LinkedHashMap<String, Object> lhmDataItem)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        JSONObject jDataItem = toJSONObject(lhmDataItem);
        T newDataItem = JsonHelper.toJavaObject(targetClass, jDataItem);
        return newDataItem;

    }

    /**
     * ArrayList<LinkedHashMap<>>转Java列表
     *
     * @param targetClass
     * @param alData
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static <T> List<T> toJavaList(Class targetClass, ArrayList<LinkedHashMap<String, Object>> alData)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<T> lstData = new ArrayList<T>();
        for (int i = 0; i < alData.size(); i++) {
            LinkedHashMap<String, Object> lhmDataItem = alData.get(i);
            T newDataItem = JsonHelper.toJavaObject(targetClass, lhmDataItem);
            lstData.add(newDataItem);
        }
        return lstData;
    }


    /**
     * jsonarray转maplist
     *
     * @param array
     * @return
     */
    public static List<Map<String, Object>> jsonToMapList(JSONArray array) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            Map<String, Object> map = new HashMap<>();

            try {
                JSONObject obj = array.getJSONObject(i);

                Iterator<?> keys = obj.keys();
                while (keys.hasNext()) {
                    String key = (String) keys.next();

                    if (!obj.isEmpty()) {
                        Object value = obj.get(key);

                        if (value instanceof JSONObject || value instanceof JSONArray) {
                            // 递归处理子对象或数组
                            value = jsonToMapList((JSONArray) value);
                        } else {
                            // 其他类型直接存放到 map 中
                            value = obj.opt(key);
                        }

                        map.put(key, value);
                    }
                }

                list.add(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }

}
