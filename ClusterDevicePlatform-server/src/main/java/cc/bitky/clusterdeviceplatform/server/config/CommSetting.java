package cc.bitky.clusterdeviceplatform.server.config;

/**
 * 与设备的通信策略
 */
public class CommSetting {
    /**
     * 服务器发送消息对象后，是否需要设置检错重发任务「要求客户端回复确认」
     */
    public static boolean DEPLOY_MSG_NEED_REPLY = true;
    /**
     * 已连接通道未响应监测模式
     */
    public static boolean NO_RESPONSE_MONITOR = true;
    /**
     * 已连接通道未响应持续的时间间隔「s」
     */
    public static int NO_RESPONSE_INTERVAL = 60 * 2;
    /**
     * 待发送缓冲队列中，帧发送间隔「单位/ms」
     */
    public static int FRAME_SEND_INTERVAL = 300;
    /**
     * 已发送帧后，在该间隔时间后，检测接收回复帧的状态，用于检错重发功能「单位/s」
     */
    public static int FRAME_SENT_TO_DETECT_INTERVAL = 5;
    /**
     * 收到一个已激活的 Channel，直到校验结束所需要的时间「单位/ms」
     */
    public static int ACCESSIBLE_CHANNEL_REPLY_INTERVAL = 1000;
    /**
     * 检错重发最大次数，服务器向 TCP 通道发送 CAN 帧，最大重复发送次数
     */
    public static int AUTO_REPEAT_REQUEST_TIMES = 5;
//    /**
//     * 单个设备的初始充电次数
//     */
//    public static int DEVICE_INIT_CHARGE_TIMES = 500;
    /**
     * 当设备中记录的剩余充电次数小于该值时，则向设备发送剩余充电次数
     */
    public static int DEPLOY_REMAIN_CHARGE_TIMES = 20;
    /**
     * 下发该次数时，设备自行清空剩余充电次数的显示
     */
    public static int REMAIN_CHARGE_TIMES_CLEAR = 254;
}
