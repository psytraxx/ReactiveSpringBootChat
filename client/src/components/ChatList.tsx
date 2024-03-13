import React, { useState, useEffect } from 'react';
import { ChatMessage } from '../lib/types';
import ChatRow from './ChatRow';


interface ChatListProps {
  channelId: number;
}

const url = process.env.API_BASE_URL || 'http://localhost:8080';

const ChatList: React.FC<ChatListProps> = ({ channelId }) => {
  const [messages, setMessages] = useState<ChatMessage[]>([]);

  useEffect(() => {
    const source = new EventSource(`${url}/chats/stream?channelId=${channelId}`);

    source.onmessage = (event) => {
      const newMessage = JSON.parse(event.data) as ChatMessage;
      setMessages((prevMessages) => [...prevMessages, newMessage]);
    };

    return () => {
      source.close();
    };
  }, [channelId]);

  const list = messages.map((row) => <ChatRow key={row.id} item={row} />);

  return <ul className="chatlist">{list}</ul>;
};

export default ChatList;