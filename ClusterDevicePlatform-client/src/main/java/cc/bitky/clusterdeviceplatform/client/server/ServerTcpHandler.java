package cc.bitky.clusterdeviceplatform.client.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import cc.bitky.clusterdeviceplatform.client.config.DeviceSetting;
import cc.bitky.clusterdeviceplatform.client.netty.TcpPresenter;
import cc.bitky.clusterdeviceplatform.client.ui.bean.Device;
import cc.bitky.clusterdeviceplatform.client.ui.bean.DeviceCellRepo;
import cc.bitky.clusterdeviceplatform.messageutils.define.base.BaseMsg;

@Service
public class ServerTcpHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ApplicationContext appContext;
    private TcpPresenter tcpPresenter;

    @Autowired
    public ServerTcpHandler(ApplicationContext appContext, TcpPresenter tcpPresenter) {
        this.appContext = appContext;
        this.tcpPresenter = tcpPresenter;
        tcpPresenter.setServer(this);
    }

    /**
     * 启动特定编号的客户端
     *
     * @param groupId 欲启动的客户端编号
     */
    public void startClient(int groupId) {
        tcpPresenter.startClient(groupId);
    }

    /**
     * 主动断开特定 Channel 的连接
     *
     * @param i Channel 的 ID
     */
    public void removeChannel(int i) {
        tcpPresenter.removeChannel(i);
    }

    /**
     * 将指定的消息对象发送至 TCP 通道
     *
     * @param message 指定的消息对象
     */
    public void sendMessage(BaseMsg message) {
        tcpPresenter.sendMessageToTcp(message);
    }

    /**
     * Netty 模块捕获到 Java 消息对象
     *
     * @param message 消息对象
     */
    public void huntMessage(BaseMsg message) {
        Device device = DeviceCellRepo.getDevice(message.getGroupId(), message.getDeviceId());
        device.handleMsg(message);
    }

    /**
     * Netty 服务器优雅关闭
     */
    public void shutdown() {
        for(int i = 1; i <= DeviceSetting.MAX_GROUP_ID; ++i) {
            this.tcpPresenter.removeChannel(i);
        }
        System.exit(SpringApplication.exit(appContext));
    }
}
