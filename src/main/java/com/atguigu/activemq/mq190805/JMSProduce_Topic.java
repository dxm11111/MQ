package com.atguigu.activemq.mq190805;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSProduce_Topic {
    public static final String MQ_URL = "tcp://192.168.49.129:61616";
    public static final String TOPIC_NAME = "topic0805";
    public static void main(String[] args) throws JMSException {
        //1获得连接工厂ActiveMQConnectionFactory
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);
        //2由ActiveMQConnectionFactory获取ActiveMQConnection
        Connection connection= activeMQConnectionFactory.createConnection();
        //3 启动连接准备建立会话
        connection.start();
        //4 获得Session,两个参数先用默认
        //4.1是否开启事务
        //4.2签收模式(自动签收）
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5获得目的地，此例是队列
        Topic topic = session.createTopic(TOPIC_NAME);
        //6获得消息生产者，生产什么内容？生产出来放在那里？
        MessageProducer messageProducer = session.createProducer(topic);
        //7 生产message内容
        for(int i=1;i<=6;i++){
            TextMessage textMessage = session.createTextMessage("msg-----" + i);
            messageProducer.send(textMessage);
        }
        //8 释放各种连接和资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("*******msg send ok,");

    }
}
