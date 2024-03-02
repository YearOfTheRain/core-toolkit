package cn.year.coretoolkit.io.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ChatNettyReconnectClient {
    static Bootstrap bootstrap;
    static EventLoopGroup group;
    public static void main(String[] args) throws Exception {
        //客户端需要一个事件循环组
        group = new NioEventLoopGroup();
        try {
            //创建客户端启动对象
            //注意客户端使用的不是 ServerBootstrap 而是 Bootstrap
            bootstrap = new Bootstrap();
            //设置相关参数
            bootstrap.group(group) //设置线程组
                    .channel(NioSocketChannel.class) // 使用 NioSocketChannel 作为客户端的通道实现
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            //加入处理器
                            ChannelPipeline pipeline = channel.pipeline();
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new ChatClientHandler());
                        }
                    });
            System.out.println("netty client start");
            connect();

        } finally {
//            group.shutdownGracefully();
        }
    }

    private static void connect() throws InterruptedException {
        //启动客户端去连接服务器端
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000);

        channelFuture.addListener(new ChannelFutureListener(){

            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(!channelFuture.isSuccess()) {
                    channelFuture.channel().eventLoop().schedule(()->{
                        System.out.println("----执行服务端重连-----");
                        try {
                            connect();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    },3000, TimeUnit.MILLISECONDS);
                } else {
                    Channel channel = channelFuture.channel();
                    System.out.println("================="+ channel.localAddress() + "==============");
                    Scanner scanner = new Scanner(System.in);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        channel.writeAndFlush(line);
                    }
                }
            }
            //对关闭通道进行监听

        });

        channelFuture.channel().closeFuture().sync();
    }
}