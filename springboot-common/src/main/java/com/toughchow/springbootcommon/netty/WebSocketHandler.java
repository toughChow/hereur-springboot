package com.toughchow.springbootcommon.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: com.toughchow.springbootcommon.netty
 * @date:2019/5/23
 */
@Slf4j
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("与客户端建立连接");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("与客户端关闭连接");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        System.out.println("客户端收到服务器数据:" + textWebSocketFrame.text());
        Scanner s = new Scanner(System.in);
        System.out.println("服务器推送：");
        while(true) {
            String line = s.nextLine();
            if(line.equals("exit")) {
                channelHandlerContext.channel().close();
                break;
            }
            String resp= "(" +channelHandlerContext.channel().remoteAddress() + ") ：" + line;
            channelHandlerContext.writeAndFlush(new TextWebSocketFrame(resp));
        }
    }
}
