package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * این کلاس اتصال به پایگاه داده را مدیریت می‌کند.
 * از Singleton برای ایجاد تنها یک اتصال در کل برنامه استفاده شده است.
 */
public final class DatabaseManager {

    private static Connection connection = null;

    // سازنده خصوصی: جلوگیری از ایجاد شیء جدید از این کلاس
    private DatabaseManager() {}

    /**
     * این متد اتصال فعلی را برمی‌گرداند یا در صورت نیاز، اتصال جدید ایجاد می‌کند.
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // بارگذاری تنظیمات از فایل db.properties
                Properties props = new Properties();
                InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream("db.properties");

                if (input == null) {
                    throw new RuntimeException("فایل db.properties پیدا نشد!");
                }
                props.load(input);

                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");

                // ایجاد اتصال
                connection = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();
                throw new SQLException("اتصال به پایگاه داده با خطا مواجه شد.");
            }
        }
        return connection;
    }
}
