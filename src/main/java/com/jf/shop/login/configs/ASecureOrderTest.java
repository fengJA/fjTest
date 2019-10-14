package com.jf.shop.login.configs;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Arrays;

public class ASecureOrderTest {
    public static int port = 7778;
    public static String algo = "SSL";
    public static void main(String[] args) {
        try {
            SSLContext context = SSLContext.getInstance(algo);
            //参考只实现X.509秘钥
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("X509");

            //Oracle默认秘钥库类型
            KeyStore ks = KeyStore.getInstance("JKS");

            //处于安全考虑每个秘钥库都需要口令加密，用char[]数组保存，容易擦除
            char[] password = System.console().readPassword();
            ks.load(new FileInputStream("jnp4e.keys"),password);
            kmf.init(ks, password);
            context.init(kmf.getKeyManagers(), null, null);
            //擦除口令
            Arrays.fill(password, '0');

            SSLServerSocketFactory factory = context.getServerSocketFactory();
            SSLServerSocket socket = (SSLServerSocket)factory.createServerSocket(port);

            //增加匿名密码组（未认证）
            String[] support = socket.getSupportedCipherSuites();
            String[] needCipherSuites = new String[support.length];
            int numNeedCipherSuites = 0;
            for (int i = 0; i < support.length; i++) {
                if (support[i].indexOf("_anan_") > 0){
                    needCipherSuites[numNeedCipherSuites++] = support[i];
                }
            }

            String[] olderEnable = socket.getEnabledCipherSuites();
            String[] newEnable = new String[olderEnable.length + numNeedCipherSuites];
            System.arraycopy(olderEnable, 0, newEnable, 0, olderEnable.length);
            System.arraycopy(needCipherSuites, 0, newEnable, olderEnable.length, numNeedCipherSuites);
            socket.setEnabledCipherSuites(newEnable);

            //配置完毕，开始通信
            while (true){
                try (Socket conn = socket.accept();){
                    InputStream in = conn.getInputStream();
                    int c;
                    while ((c = in.read()) != -1){
                        System.out.println(c);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
