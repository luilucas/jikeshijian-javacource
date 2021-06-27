package com.lucas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CustomClassLoader extends ClassLoader{
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        CustomClassLoader ccl = new CustomClassLoader();
        Class clazz = ccl.loadClass("com.lucas.Hello");
        Hello obj = (Hello) clazz.newInstance();
        obj.hello();
    }

   @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            byte[] bytes = GetClassBytes("./Hello.xlass");
            Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
            return c;
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }

    private byte[] GetClassBytes(String name) throws IOException {
        File file = new File(name);
        FileInputStream fileInputStream;
        byte[] bytes = new byte[(int) file.length()];
        fileInputStream = new FileInputStream(file);
        fileInputStream.read(bytes);
        fileInputStream.close();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return bytes;
    }
}
