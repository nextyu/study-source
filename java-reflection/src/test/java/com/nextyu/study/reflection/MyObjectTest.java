package com.nextyu.study.reflection;

import com.sun.org.apache.bcel.internal.classfile.SourceFile;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Source;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;

/**
 * 2016-12-25 下午2:00
 *
 * @author nextyu
 */
public class MyObjectTest {

    private static final Logger logger = LoggerFactory.getLogger(MyObjectTest.class);

    @Test
    public void test1() {
        Class<MyObject> aClass = MyObject.class;
        // 全限定类名（包含包名）
        String name = aClass.getName();
        // 类的名字(不包含包名)
        String simpleName = aClass.getSimpleName();

        logger.debug("全限定类名 {}", name);
        logger.debug("类的名字(不包含包名) {}", simpleName);

        int modifiers = aClass.getModifiers();
        logger.debug("修饰符 {}", modifiers);

        logger.debug("{}", Modifier.isAbstract(1));

        Package aPackage = aClass.getPackage();
        logger.debug("包名 {}", aPackage.getName());

        Class<? super MyObject> superclass = aClass.getSuperclass();

        /*
        注意：getInterfaces()方法仅仅只返回当前类所实现的接口。当前类的父类如果实现了接口，这些接口是不会在返回的Class集合中的，尽管实际上当前类其实已经实现了父类接口。
         */
        Class<?>[] interfaces = aClass.getInterfaces();


        Constructor<?>[] constructors = aClass.getConstructors();

        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            logger.debug("方法名称 {}", method.getName());
        }

        Field[] fields = aClass.getFields();

        Annotation[] annotations = aClass.getAnnotations();

    }

    @Test
    public void testConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<MyObject> aClass = MyObject.class;

        // 全部的构造器
        Constructor<?>[] constructors = aClass.getConstructors();

        // 指定参数的构造器
        Constructor<MyObject> constructor = aClass.getConstructor(String.class);

        // 构造方法参数
        Class<?>[] parameterTypes = constructor.getParameterTypes();

        // 利用Constructor对象实例化一个类
        MyObject myObject = constructor.newInstance("小明");
        logger.debug("{}", myObject);

    }

    @Test
    public void testField() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class<MyObject> aClass = MyObject.class;
        // 声明为公有的(public)的所有变量集合
        Field[] fields = aClass.getFields();

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            logger.debug("成员变量名称 {}", declaredField.getName());
        }

        MyObject myObject = aClass.newInstance();
        Field name = aClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(myObject, "小黑");

        logger.debug("{}", myObject);

    }

    @Test
    public void testMethod() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<MyObject> aClass = MyObject.class;
        // 所有public方法
        Method[] methods = aClass.getMethods();

        Method setNameMethod = aClass.getMethod("setName", String.class);

        // 方法参数
        Class<?>[] parameterTypes = setNameMethod.getParameterTypes();

        // 返回类型
        Class<?> returnType = setNameMethod.getReturnType();

        MyObject myObject = aClass.newInstance();

        Object result = setNameMethod.invoke(myObject, "小花");

        logger.debug("{}", myObject);
        logger.debug("{}", result);

    }

    @Test
    public void testGettersAndSetters() {
        Class<MyObject> aClass = MyObject.class;
        Method[] methods = aClass.getMethods();

        for (Method method : methods) {
            if (isGetter(method)) {
                System.out.println("getter: " + method);
            }
            if (isSetter(method)) {
                System.out.println("setter: " + method);
            }
        }

    }

    @Test
    public void testPrivateFieldsAndMethods() throws IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class<MyObject> aClass = MyObject.class;
        MyObject myObject = aClass.newInstance();
        Field name = aClass.getDeclaredField("name");
        /*
        通过调用setAccessible()方法会关闭指定类Field实例的反射访问检查，这行代码执行之后不论是私有的、受保护的以及包访问的作用域，你都可以在任何地方访问，即使你不在他的访问权限作用域之内。
         */
        name.setAccessible(true);
        name.set(myObject, "小黑");

        Method privateMethod1 = aClass.getDeclaredMethod("privateMethod1");
        /*
        通过调用setAccessible()方法会关闭指定类的Method实例的反射访问检查，这行代码执行之后不论是私有的、受保护的以及包访问的作用域，你都可以在任何地方访问，即使你不在他的访问权限作用域之内。
         */
        privateMethod1.setAccessible(true);
        Object result = privateMethod1.invoke(myObject);
        System.out.println(result);

    }


    @Test
    public void testAnnotation() {
        Class<MyObject> aClass = MyObject.class;
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        MyAnnotation myAnnotation = aClass.getAnnotation(MyAnnotation.class);
        String name = myAnnotation.name();
        String value = myAnnotation.value();
        System.out.println(name);
        System.out.println(value);
    }

    @Test
    public void testGenerics() throws NoSuchMethodException, NoSuchFieldException {
        Class<MyObjectWithGenerics> myObjectWithGenericsClass = MyObjectWithGenerics.class;

        // 泛型方法返回类型
        Method method = myObjectWithGenericsClass.getMethod("getStringList", null);

        Type returnType = method.getGenericReturnType();

        if (returnType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments = type.getActualTypeArguments();
            for (Type typeArgument : typeArguments) {
                Class typeArgClass = (Class) typeArgument;
                System.out.println("typeArgClass = " + typeArgClass);
            }
        }

        // 泛型方法参数类型
        method = myObjectWithGenericsClass.getMethod("setStringList", List.class);

        Type[] genericParameterTypes = method.getGenericParameterTypes();

        for (Type genericParameterType : genericParameterTypes) {
            if (genericParameterType instanceof ParameterizedType) {
                ParameterizedType aType = (ParameterizedType) genericParameterType;
                Type[] parameterArgTypes = aType.getActualTypeArguments();
                for (Type parameterArgType : parameterArgTypes) {
                    Class parameterArgClass = (Class) parameterArgType;
                    System.out.println("parameterArgClass = " + parameterArgClass);
                }
            }
        }

        // 泛型变量类型
        Field field = myObjectWithGenericsClass.getField("stringList");

        Type genericFieldType = field.getGenericType();

        if (genericFieldType instanceof ParameterizedType) {
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            for (Type fieldArgType : fieldArgTypes) {
                Class fieldArgClass = (Class) fieldArgType;
                System.out.println("fieldArgClass = " + fieldArgClass);
            }
        }
    }

    @Test
    public void testArray() throws ClassNotFoundException {
        // 创建一个数组
        int[] intArray = (int[]) Array.newInstance(int.class, 3);

        // 访问一个数组
        Array.set(intArray, 0, 123);
        Array.set(intArray, 1, 456);
        Array.set(intArray, 2, 789);

        System.out.println("intArray[0] = " + Array.get(intArray, 0));
        System.out.println("intArray[1] = " + Array.get(intArray, 1));
        System.out.println("intArray[2] = " + Array.get(intArray, 2));

        // 获取数组的Class对象
        Class stringArrayClass = String[].class;

        /*
        获得一个原生数据类型（primitive）int数组的Class对象,在JVM中字母I代表int类型，左边的‘[’代表我想要的是一个int类型的数组，这个规则同样适用于其他的原生数据类型
         */
        Class intArrayClass = Class.forName("[I");

        /*
         普通对象类型的数组,注意‘[L’的右边是类名，类名的右边是一个‘;’符号。这个的含义是一个指定类型的数组。
          */
        Class stringArrayClass2 = Class.forName("[Ljava.lang.String;");


        /*
        一旦你获取了类型的Class对象，你就有办法轻松的获取到它的数组的Class对象，你可以通过指定的类型创建一个空的数组，然后通过这个空的数组来获取数组的Class对象。这样做有点讨巧，不过很有效。如下例
         */
        Class stringArrayClass3 = Array.newInstance(String.class, 0).getClass();
        System.out.println("is array: " + stringArrayClass.isArray());


        // 获取数组的成员类型
        String[] strings = new String[3];
        Class stringArrayClass4 = strings.getClass();
        Class stringArrayComponentType = stringArrayClass4.getComponentType();
        System.out.println(stringArrayComponentType);

    }

    @Test
    public void testDynamicClassLoadingAndReloading() {
        String packageName = "com.nextyu.study.reflection";
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(".", "/"));
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                String protocol = url.getProtocol();
                System.out.println(protocol);
                System.out.println(url.getPath());
                String packagePath = url.getPath().replaceAll("%20", " ");
                System.out.println(packagePath);
                File[] files = new File(packagePath).listFiles();
                for (File file : files) {
                    System.out.println(file.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) {
            return false;
        }
        if (method.getParameterTypes().length != 0) {
            return false;
        }
        if (void.class.equals(method.getReturnType())) {
            return false;
        }
        return true;
    }

    private boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) {
            return false;
        }
        if (method.getParameterTypes().length != 1) {
            return false;
        }
        return true;
    }

}