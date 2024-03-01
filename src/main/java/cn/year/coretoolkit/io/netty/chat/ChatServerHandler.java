package cn.year.coretoolkit.io.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端] " + channel.remoteAddress() + "上线了 " + LocalDateTime.now().toString());
        channelGroup.add(channel);
        System.out.println(ctx.channel().remoteAddress() + " 上线了 ");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端] " + channel.remoteAddress() + "下线了 " + LocalDateTime.now().toString());
        System.out.println(ctx.channel().remoteAddress() + " 下线了 ");
        System.out.println("channelGroup size == " + channelGroup.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if(ch != channel) {
                ch.writeAndFlush("[客户端] " + channel.remoteAddress() + "发送了消息：  " + s + "   " + LocalDateTime.now().toString());
            }else {
                ch.writeAndFlush("[自己] " + channel.remoteAddress() + "发送了消息：  " + s + "   " + LocalDateTime.now().toString());
            }
        });
    }
}
