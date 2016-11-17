package de.xappo.presenterinjection.utils;

 public final class ComponentRegisterCheckerUtil {
    private ComponentRegisterCheckerUtil() {
    }

    public static void checkRegistered(final boolean condition, final String message, Object... args) {
        if (!condition) {
            throw new IllegalStateException(String.format(message, args));
        }
    }
}
