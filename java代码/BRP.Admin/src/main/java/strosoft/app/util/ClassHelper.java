package strosoft.app.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;

public class ClassHelper {
	/*
	 * 创建实例
	 */
	public static Object newInstance(String typeName)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		@SuppressWarnings("rawtypes")
		Class theClass = Class.forName(typeName);
		try {

			Object instance = theClass.newInstance();
			return instance;
		} catch (Exception e) {
		}
		// 如果没有public的构造函数，则调用getInstance方法创建实例
		@SuppressWarnings("unchecked")
		Method theMethod = theClass.getMethod("getInstance");
		Object instance = theMethod.invoke(null);
		return instance;
	}

	/*
	 * 字段是否存在
	 */
	public static Boolean existsField(Class theClass, String fieldName) {
		Field[] arrField = theClass.getFields();
		for (int i = 0; i < arrField.length; i++) {
			if (arrField[i].getName().equals(fieldName)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * 方法是否存在
	 */
	public static Boolean existsMethod(Class theClass, String methodName) {
		Method[] arrMethod = theClass.getMethods();
		for (int i = 0; i < arrMethod.length; i++) {
			if (arrMethod[i].getName().equals(methodName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 复制对象
	 * 
	 * @param sourceClass
	 * @param targetClass
	 * @param objSource
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static Object copyObject(Class targetClass, Object objSource)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException,
			SecurityException, NoSuchMethodException {
		Class sourceClass = objSource.getClass();
		Object objTarget = targetClass.newInstance();
		Method[] arrTargetMethod = getAllMethods(targetClass);
		for (Method currentMethod : arrTargetMethod) {
			String methodName = currentMethod.getName();
			if (!methodName.startsWith("set")) {
				// 跳过非set方法
				continue;
			}
			String getMethodName = "get" + methodName.substring(3);
			Method[] sourceMethods = sourceClass.getMethods();
			for (Method currentSourceMethod : sourceMethods) {
				if (currentSourceMethod.getName().equals(getMethodName)) {
					Object objValue = currentSourceMethod.invoke(objSource);
					currentMethod.invoke(objTarget, objValue);
					break;
				}
			}
		}
		return objTarget;
	}

	/**
	 * 取得指定类及其所有父类的方法数组
	 * 
	 * @param cls
	 * @return
	 */
	public static Method[] getAllMethods(Class cls) {
		List<Method> lstMethod = getAllMethodList(cls);
		return lstMethod.toArray(new Method[lstMethod.size()]);
	}

	/**
	 * 取得指定类及其所有父类的方法列表
	 * 
	 * @param cls
	 * @return
	 */
	public static List<Method> getAllMethodList(Class cls) {
		Validate.isTrue(cls != null, "The class must not be null");
		final List<Method> lstMethod = new ArrayList<Method>();
		Class currentClass = cls;
		while (currentClass != null) {
			final Method[] declaredMethods = currentClass.getDeclaredMethods();
			for (final Method field : declaredMethods) {
				lstMethod.add(field);
			}
			currentClass = currentClass.getSuperclass();
		}
		return lstMethod;

	}

	public static Field[] getAllFields(final Class cls) {
		final List<Field> allFieldsList = getAllFieldsList(cls);

		return allFieldsList.toArray(new Field[allFieldsList.size()]);

	}

	public static List<Field> getAllFieldsList(final Class cls) {
		Validate.isTrue(cls != null, "The class must not be null");
		final List<Field> allFields = new ArrayList<Field>();
		Class currentClass = cls;
		while (currentClass != null) {
			final Field[] declaredFields = currentClass.getDeclaredFields();
			for (final Field field : declaredFields) {
				allFields.add(field);
			}
			currentClass = currentClass.getSuperclass();
		}
		return allFields;

	}
}