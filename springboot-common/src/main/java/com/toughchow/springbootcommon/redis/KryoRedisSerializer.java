package com.toughchow.springbootcommon.redis;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.ByteArrayOutputStream;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: redisTemplate 默认的序列化方式为 jdkSerializeable，我们也可以使用其他序列化方式来达到不同的需求。
 * 比如我们希望缓存的数据具有可读性就可以将其序列化为json格式，json序列化可以使用Jackson2JsonRedisSerialize或FastJsonRedisSerializer。
 * 如果我们希望拥有更快的速度和占用更小的存储空间推荐使用KryoRedisSerializer进行序列化。
 * 由于redis缓存对可读性没什么要求，而存储空间和速度是比较重要的，所以这里使用KryoRedisSerializer进行对象序列化。
 * @date:2019/5/20
 */
public class KryoRedisSerializer<T> implements RedisSerializer<T> {

    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    private static final ThreadLocal<Kryo> kryos = ThreadLocal.withInitial(Kryo::new);

    private Class<T> clazz;

    public KryoRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }
    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return EMPTY_BYTE_ARRAY;
        }

        Kryo kryo = kryos.get();
        kryo.setReferences(false);
        kryo.register(clazz);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             Output output = new Output(baos)) {
            kryo.writeClassAndObject(output, t);
            output.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return EMPTY_BYTE_ARRAY;
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }

        Kryo kryo = kryos.get();
        kryo.setReferences(false);
        kryo.register(clazz);

        try (Input input = new Input(bytes)) {
            return (T) kryo.readClassAndObject(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
