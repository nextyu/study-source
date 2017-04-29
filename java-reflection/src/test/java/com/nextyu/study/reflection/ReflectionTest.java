package com.nextyu.study.reflection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    /**
     * 获取类的基本信息
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void testClass() throws ClassNotFoundException {

        // 获取Class对象
        Class<MyObject> aClass = MyObject.class;

        // 或者通过全限定类名获取Class对象
        Class aClass1 = Class.forName("com.nextyu.study.reflection.MyObject");


        // 获取全限定类名（包含包名）
        String name = aClass.getName();
        logger.debug("全限定类名 {}", name);

        // 获取类的名字(不包含包名)
        String simpleName = aClass.getSimpleName();
        logger.debug("类的名字(不包含包名) {}", simpleName);


        // 获取当前类或者接口的全部修饰符比如public, protected, private, final, static, abstract and interface等等
        // ，但是返回的是一个编码之后的int整数
        int modifiers = aClass.getModifiers();
        logger.debug("修饰符 {}", modifiers);

        // 可以使用Modifier类，来判断当前修饰符是否包含指定的修饰符
        logger.debug("是否包含抽象修饰符 {}", Modifier.isAbstract(modifiers));
        logger.debug("是否包含私有修饰符 {}", Modifier.isPrivate(modifiers));

        // 获取包名
        Package aPackage = aClass.getPackage();
        logger.debug("包名 {}", aPackage.getName());

        // 获取父类
        Class<? super MyObject> superclass = aClass.getSuperclass();
        logger.debug("父类 {}", superclass);

        // 获取实现的接口
        // 注意：getInterfaces()方法仅仅只返回当前类所实现的接口。当前类的父类如果实现了接口，这些接口是不会在返回的Class集合中的，尽管实际上当前类其实已经实现了父类接口。
        Class<?>[] interfaces = aClass.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            logger.debug("接口 {}", anInterface);
        }

        // 获取构造方法
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            logger.debug("构造方法 {}", constructor);
        }

        // 获取方法，包括父类的（只能获取public的）
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            logger.debug("public方法 {}", method);
        }

        // 获取成员变量（只能获取public的）
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            logger.debug("public成员变量 {}", field);
        }

        // 获取注解
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            logger.debug("注解 {}", annotation);
        }
    }

    /**
     * 构造方法
     *
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    @Test
    public void testConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<MyObject> aClass = MyObject.class;

        // 全部的构造方法
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            logger.debug("构造方法 {}", constructor);
        }


        // 指定参数的构造方法
        // getConstructor方法传入的参数为Class类型的对象,代表的是参数类型
//      // 想获得上面的带一个String参数的构造方法,就需要传入String的Class对象
        Constructor<MyObject> constructor = aClass.getConstructor(String.class);
        logger.debug("指定参数的构造方法 {}", constructor);

        // 构造方法参数
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        for (Class<?> parameterType : parameterTypes) {
            logger.debug("构造方法参数 {}", parameterType);
        }

        // 利用Constructor对象实例化一个类
        // 刚才我们获取的是只带一个String参数的构造方法,所以实例化对象,只需传入一个String参数即可
        MyObject myObject = constructor.newInstance("小明");
        logger.debug("{}", myObject);

    }

    /**
     * 成员变量
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void testField() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class<MyObject> aClass = MyObject.class;
        // 获取成员变量（只能获取public的）,包括继承来的
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            logger.debug("public成员变量 {}", field);
        }

        // 获取所有的成员变量（包括public, protected, default (package) access, 以及 private 的）,但是不包括继承来的
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            logger.debug("所有的成员变量 {}", declaredField);
        }

        // 成员变量赋值
        // 实例化一个对象
        MyObject myObject = aClass.newInstance();
        // 根据名称获取某个成员变量
        Field name = aClass.getDeclaredField("name");

        // 因为name成员变量为private，所以必须设置可访问为true，不然直接赋值会报IllegalAccessException
        name.setAccessible(true);

        // set方法需要两个参数，第一个为某个对象，第二个为需要设置的值
        // 下面的方法可理解为：将上面实例化的对象myObject-->的成员变量name-->赋值为小黑
        name.set(myObject, "小黑");
        logger.debug("{}", myObject);

    }

    /**
     * 方法
     *
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    @Test
    public void testMethod() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<MyObject> aClass = MyObject.class;
        // 获取方法（只能获取public的），包括继承来的
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            logger.debug("public方法 {}", method);
        }

        logger.debug("------------------------------------------------------------------------------------");

        // 获取所有的方法（包括public, protected, default (package) access, 以及 private 的）,但是不包括继承来的
        methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            logger.debug("所有的方法 {}", method);
        }


        // 获取指定的public方法，第一个参数为方法名称，第二个参数为该方法的参数的Class对象
        // 获取setName(String name)方法
        Method setNameMethod = aClass.getMethod("setName", String.class);
        logger.debug("setNameMethod {}", setNameMethod);

        // 获取方法参数
        Class<?>[] parameterTypes = setNameMethod.getParameterTypes();
        for (Class<?> parameterType : parameterTypes) {
            logger.debug("setNameMethod方法参数 {}", parameterType);
        }

        // 获取返回类型
        Class<?> returnType = setNameMethod.getReturnType();
        logger.debug("setNameMethod返回类型 {}", returnType);

        // 实例化一个对象
        MyObject myObject = aClass.newInstance();

        // 调用方法，第一个参数为某个对象，第二个参数为需要设置的值，返回值为当前方法的返回值
        // 下面的方法可理解为：调用上面实例化的对象myObject-->的setName方法-->传入的参数为“小花”
        Object setNameMethodResult = setNameMethod.invoke(myObject, "小花");

        logger.debug("myObject {}", myObject);

        // 因为setName方法没有返回值，所以setNameMethodResult为null
        logger.debug("setNameMethodResult {}", setNameMethodResult);


        Method getNameMethod = aClass.getMethod("getName");
        Object getNameMethodResult = getNameMethod.invoke(myObject);
        // getName的方法的返回值为刚才通过setName方法设置的“小花”
        logger.debug("getNameMethodResult {}", getNameMethodResult);

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


    /**
     * 获取类上的注解
     */
    @Test
    public void testClassAnnotation() {
        Class<MyObject> aClass = MyObject.class;
        // 获取所有的注解
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        // 获取指定的注解
        MyAnnotation myAnnotation = aClass.getAnnotation(MyAnnotation.class);
        String name = myAnnotation.name();
        String value = myAnnotation.value();
        System.out.println(name);
        System.out.println(value);
    }

    /**
     * 泛型
     *
     * @throws NoSuchMethodException
     * @throws NoSuchFieldException
     */
    @Test
    public void testGenerics() throws NoSuchMethodException, NoSuchFieldException {
        Class<MyObjectWithGenerics> myObjectWithGenericsClass = MyObjectWithGenerics.class;

        // 泛型方法返回类型
        Method method = myObjectWithGenericsClass.getMethod("getStringList");

        Type genericReturnType = method.getGenericReturnType();

        if (genericReturnType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) genericReturnType;
            Type[] actualTypeArguments = type.getActualTypeArguments();
            for (Type typeArgument : actualTypeArguments) {
                Class typeArgClass = (Class) typeArgument;
                System.out.println("泛型方法返回类型 = " + typeArgClass);
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
                    System.out.println("泛型方法参数类型 = " + parameterArgClass);
                }
            }
        }

        // 泛型成员变量类型
        Field field = myObjectWithGenericsClass.getField("stringList");

        Type genericFieldType = field.getGenericType();

        if (genericFieldType instanceof ParameterizedType) {
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            for (Type fieldArgType : fieldArgTypes) {
                Class fieldArgClass = (Class) fieldArgType;
                System.out.println("泛型变量类型 = " + fieldArgClass);
            }
        }
    }

    @Test
    public void testArray() throws ClassNotFoundException {
        // 创建一个数组
        int[] intArray = (int[]) Array.newInstance(int.class, 3);

        // 为数组赋值
        Array.set(intArray, 0, 123);
        Array.set(intArray, 1, 456);
        Array.set(intArray, 2, 789);

        // 访问一个数组
        System.out.println("intArray[0] = " + Array.get(intArray, 0));
        System.out.println("intArray[1] = " + Array.get(intArray, 1));
        System.out.println("intArray[2] = " + Array.get(intArray, 2));

        // 获取数组的Class对象
        Class stringArrayClass = String[].class;
        System.out.println("stringArrayClass = " + stringArrayClass);


        // 获得一个原生数据类型（primitive）int数组的Class对象
        // 在JVM中字母I代表int类型，左边的‘[’代表我想要的是一个int类型的数组，这个规则同样适用于其他的原生数据类型
        Class intArrayClass = Class.forName("[I");
        System.out.println("intArrayClass = " + intArrayClass);


        // 普通对象类型的数组,注意‘[L’的右边是类名
        // 类名的右边是一个‘;’符号
        Class stringArrayClass2 = Class.forName("[Ljava.lang.String;");
        System.out.println("stringArrayClass2 = " + stringArrayClass2);


        // 一旦你获取了类型的Class对象，你就有办法轻松的获取到它的数组的Class对象，
        // 首先你可以通过指定的类型创建一个空的数组，
        // 然后通过这个空的数组来获取数组的Class对象。
        Class stringArrayClass3 = Array.newInstance(String.class, 0).getClass();
        System.out.println("stringArrayClass3 is array: " + stringArrayClass.isArray());


        // 获取数组的成员类型
        String[] strings = new String[3];
        Class stringArrayClass4 = strings.getClass();
        Class stringArrayComponentType = stringArrayClass4.getComponentType();
        System.out.println("stringArrayComponentType = " + stringArrayComponentType);

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