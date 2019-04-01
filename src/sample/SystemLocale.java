package sample;

import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by Yuri Loskin on 07.05.2015.
 */
public class SystemLocale {
    public static Locale locale;
    public static ResourceBundle bundle;


    public static void setLocale(Locale loc) {
        locale = loc == null ? new Locale("ru", "RU") : loc;
    }

    public static ResourceBundle getUtf8PropertyResourceBundle(ResourceBundle bundle) {
        return Utf8ResourceBundle.createUtf8PropertyResourceBundle(bundle);
    }

    public static void setBundle(ResourceBundle bundle) {
        SystemLocale.bundle = getUtf8PropertyResourceBundle(bundle);
    }


    public abstract static class Utf8ResourceBundle {
        /**
         * Creates unicode friendly {@link PropertyResourceBundle} if possible.
         *
         * @param bundle
         * @return Unicode friendly property resource bundle
         */
        public static ResourceBundle createUtf8PropertyResourceBundle(
                final ResourceBundle bundle) {
            if (!(bundle instanceof PropertyResourceBundle)) {
                return bundle;
            }
            return new Utf8PropertyResourceBundle((PropertyResourceBundle) bundle);
        }

        private static class Utf8PropertyResourceBundle extends ResourceBundle {

            /**
             * Bundle with unicode data
             */
            private final PropertyResourceBundle bundle;

            /**
             * Initializing constructor
             *
             * @param bundle
             */
            private Utf8PropertyResourceBundle(final PropertyResourceBundle bundle) {
                this.bundle = bundle;
            }

            @Override
            @SuppressWarnings("unchecked")
            public Enumeration getKeys() {
                return bundle.getKeys();
            }

            @Override
            protected Object handleGetObject(final String key) {
                final String value = bundle.getString(key);
                if (value == null) {
                    return null;
                }
                return new String(value.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
            }
        }
    }
}
