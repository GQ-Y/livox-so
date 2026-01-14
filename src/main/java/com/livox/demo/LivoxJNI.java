package com.livox.demo;

/**
 * Livox SDK JNI 封装类
 */
public class LivoxJNI {
    
    static {
        try {
            // 加载动态库
            System.loadLibrary("livoxjni");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("无法加载 livoxjni 动态库: " + e.getMessage());
            System.err.println("请确保 liblivoxjni.so 在 java.library.path 中");
            throw e;
        }
    }

    /**
     * 初始化 Livox SDK
     * @param configPath 配置文件路径
     * @return 是否成功
     */
    public static native boolean init(String configPath);

    /**
     * 启动 SDK
     * @return 是否成功
     */
    public static native boolean start();

    /**
     * 停止 SDK
     */
    public static native void stop();

    /**
     * 设置点云数据回调
     * @param callback 回调接口
     */
    public static native void setPointCloudCallback(PointCloudCallback callback);
}
