package com.toughchow.springbootcommon.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: com.toughchow.springbootcommon.netty
 * @date:2019/5/23
 */
public class MyChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast("http-codec", new HttpServerCodec()); // Http消息编码解码
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536)); // Http消息组装
        pipeline.addLast("http-chunked", new ChunkedWriteHandler()); // WebSocket通信支持
//        pipeline.addLast("handler", new MyHandler());//指定房间
        //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
        channel.pipeline().addLast(new HttpServerCodec());
        //以块的方式来写的处理器
        channel.pipeline().addLast(new ChunkedWriteHandler());
        channel.pipeline().addLast(new HttpObjectAggregator(8192));
        channel.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
        channel.pipeline().addLast(new WebSocketHandler());
    }
}
