package is.hail.annotations;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public final class Memory {
    private static final Unsafe unsafe;

    public static void storeByte(byte[] mem, long off, byte b) {
        unsafe.putByte(mem, Unsafe.ARRAY_BYTE_BASE_OFFSET + off, b);
    }

    public static void storeFloat(byte[] mem, long off, float f) {
        unsafe.putFloat(mem, Unsafe.ARRAY_BYTE_BASE_OFFSET + off, f);
    }

    public static void storeDouble(byte[] mem, long off, double d) {
        unsafe.putDouble(mem, Unsafe.ARRAY_BYTE_BASE_OFFSET + off, d);
    }

    public static byte loadByte(byte[] mem, long off) {
        return unsafe.getByte(mem, Unsafe.ARRAY_BYTE_BASE_OFFSET + off);
    }

    public static float loadFloat(byte[] mem, long off) {
        return unsafe.getFloat(mem, Unsafe.ARRAY_BYTE_BASE_OFFSET + off);
    }

    public static double loadDouble(byte[] mem, long off) {
        return unsafe.getDouble(mem, Unsafe.ARRAY_BYTE_BASE_OFFSET + off);
    }

    public static void memcpy(byte[] dst, long dstOff, byte[] src, long srcOff, long n) {
        unsafe.copyMemory(src, Unsafe.ARRAY_BYTE_BASE_OFFSET + srcOff, dst, Unsafe.ARRAY_BYTE_BASE_OFFSET + dstOff, n);
    }

    public static void memcpy(long dst, byte[] src, long srcOff, long n) {
        copyFromArray(dst, src, srcOff, n);
    }

    public static void memcpy(byte[] dst, long dstOff, long src, long n) {
        copyToArray(dst, dstOff, src, n);
    }

    public static boolean loadBoolean(long addr) {
        return unsafe.getByte(addr) != 0;
    }

    public static byte loadByte(long addr) {
        return unsafe.getByte(addr);
    }

    public static short loadShort(long addr) {
        return unsafe.getShort(addr);
    }

    public static int loadInt(long addr) {
        return unsafe.getInt(addr);
    }

    public static long loadLong(long addr) {
        return unsafe.getLong(addr);
    }

    public static float loadFloat(long addr) {
        return unsafe.getFloat(addr);
    }

    public static double loadDouble(long addr) {
        return unsafe.getDouble(addr);
    }

    public static long loadAddress(long addr) {
        return unsafe.getAddress(addr);
    }

    public static void storeBoolean(long addr, boolean b) {
        unsafe.putByte(addr, (byte)(b ? 1 : 0));
    }

    public static void storeByte(long addr, byte b) {
        unsafe.putByte(addr, b);
    }

    public static void storeShort(long addr, short s) {
        unsafe.putShort(addr, s);
    }

    public static void storeInt(long addr, int i) {
        unsafe.putInt(addr, i);
    }

    public static void storeLong(long addr, long l) {
        unsafe.putLong(addr, l);
    }

    public static void storeFloat(long addr, float f) {
        unsafe.putFloat(addr, f);
    }

    public static void storeDouble(long addr, double d) {
        unsafe.putDouble(addr, d);
    }

    public static void storeAddress(long addr, long a) {
        unsafe.putAddress(addr, a);
    }

    public static long malloc(long size) {
        return unsafe.allocateMemory(size);
    }

    public static void free(long a) {
        unsafe.freeMemory(a);
    }

    public static long realloc(long a, long newSize) {
        return unsafe.reallocateMemory(a, newSize);
    }


    public static void memcpy(long dst, long src, long n) {
        unsafe.copyMemory(src, dst, n);
    }

    public static void copyToArray(byte[] dst, long dstOff, long src, long n) {
        unsafe.copyMemory(null, src, dst, Unsafe.ARRAY_BYTE_BASE_OFFSET + dstOff, n);
    }

    public static void copyFromArray(long dst, byte[] src, long srcOff, long n) {
        unsafe.copyMemory(src, Unsafe.ARRAY_BYTE_BASE_OFFSET + srcOff, null, dst, n);
    }

    static {
        Unsafe t;
        try {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            t = (sun.misc.Unsafe) unsafeField.get(null);
        } catch (Throwable cause) {
            t = null;
        }
        unsafe = t;
    }
}
